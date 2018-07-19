package com.kayson.api.config.shiro;

import java.util.ArrayList;
import java.util.List;

import com.kayson.api.Utils.JWTUtil;
import com.kayson.api.model.Service.UserPermissionService;
import com.kayson.api.model.Service.UserRoleService;
import com.kayson.api.model.Service.UserService;
import com.kayson.api.model.domain.User;
import com.kayson.api.model.domain.UserPermission;
import com.kayson.api.model.domain.UserRole;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 获取用户的角色和权限信息
 */
@Service
public class ShiroRealm extends AuthorizingRealm {

    private Logger logger = LoggerFactory.getLogger(ShiroRealm.class);

    //一般这里都写的是servic，我省略了service的接口和实现方法直接调用的dao
    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private UserPermissionService userPermissionService;

    /**
     * 大坑！，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String jwttoken = (String) token.getCredentials();
        logger.info("验证当前Subject时获取到token为：" + token.toString());

        // 解密获得username，用于和数据库进行对比
        String username = JWTUtil.getUsername(jwttoken);
        if (username == null) {
            throw new AuthenticationException("token invalid");
        }

        User user = userService.findByAccount(username);
        if (user == null) {
            throw new AuthenticationException("User didn't existed!");
        }

        if (! JWTUtil.verify(jwttoken, username, user.getPassword())) {
            throw new AuthenticationException("Username or password error");
        }

        if(user.getStatus() == 0){
            throw new AuthenticationException("The account is locked!");
        }

        return new SimpleAuthenticationInfo(jwttoken, jwttoken, getName());

    }

    /**
     * 权限认证
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("##################执行Shiro权限认证##################");

        String username = JWTUtil.getUsername(principalCollection.toString());
        User user = userService.findByAccount(username);
        if (user != null) {
            //权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

            List<UserRole> roleList = userRoleService.findByUid(user.getId());
            List<UserPermission> permissionList = userPermissionService.findUserPermissionByUid(user.getId());

            List<String> roleStrlist=new ArrayList<String>();////用户的角色集合
            List<String> perminsStrlist=new ArrayList<String>();//用户的权限集合

            for (UserRole role : roleList) {
                roleStrlist.add(role.getRoleFlag());
            }
            for (UserPermission uPermission : permissionList) {
                //logger.info("权限>>>>>>>>>"+uPermission.getpUrl());
                perminsStrlist.add(uPermission.getpUrl());
            }

            //用户的角色集合
            info.addRoles(roleStrlist);
            //用户的权限集合
            info.addStringPermissions(perminsStrlist);

            return info;
        }
        // 返回null的话，就会导致任何用户访问被拦截的请求时，都会自动跳转到unauthorizedUrl指定的地址
        return null;
    }


}
