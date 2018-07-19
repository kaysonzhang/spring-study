package com.kayson.web.config.shiro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kayson.web.model.Service.UserPermissionService;
import com.kayson.web.model.Service.UserRoleService;
import com.kayson.web.model.Service.UserService;
import com.kayson.web.model.domain.User;
import com.kayson.web.model.domain.UserPermission;
import com.kayson.web.model.domain.UserRole;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 获取用户的角色和权限信息
 */
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
     * 登录认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        logger.info("验证当前Subject时获取到token为：" + token.toString());

        //查出是否有此用户
        User hasUser = userService.findByAccount(token.getUsername());
        if (hasUser == null) {
            throw new UnknownAccountException("用户不存在！");
        }

        List<UserRole> roleList = userRoleService.findByUid(hasUser.getId());
        List<UserPermission> permissionList = userPermissionService.findUserPermissionByUid(hasUser.getId());

        List<String> roleStrlist=new ArrayList<String>();////用户的角色集合
        List<String> perminsStrlist=new ArrayList<String>();//用户的权限集合

        for (UserRole role : roleList) {
            roleStrlist.add(role.getRoleFlag());
        }
        for (UserPermission uPermission : permissionList) {
            //logger.info("权限>>>>>>>>>"+uPermission.getpUrl());
            perminsStrlist.add(uPermission.getpUrl());
        }

        hasUser.setRoleList(roleStrlist);
        hasUser.setPermissionList(perminsStrlist);

        // 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验
        ByteSource slat = ByteSource.Util.bytes(hasUser.getSalt());
        return new SimpleAuthenticationInfo(hasUser, hasUser.getPassword(), slat, getName());


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

        User user = (User) principalCollection.getPrimaryPrincipal();
        if (user != null) {
            //权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            //用户的角色集合
            info.addRoles(user.getRoleList());
            //用户的权限集合
            info.addStringPermissions(user.getPermissionList());

            return info;
        }
        // 返回null的话，就会导致任何用户访问被拦截的请求时，都会自动跳转到unauthorizedUrl指定的地址
        return null;
    }


}
