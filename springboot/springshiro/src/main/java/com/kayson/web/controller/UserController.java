package com.kayson.web.controller;

import com.kayson.web.Utils.DateUtil;
import com.kayson.web.model.Service.UserPermissionService;
import com.kayson.web.model.Service.UserRoleService;
import com.kayson.web.model.Service.UserService;
import com.kayson.web.model.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author by kayson
 * @data 2018/7/16 16:48
 * @description
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private static Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private UserPermissionService userPermissionService;

    @RequiresPermissions("user:index")//权限管理;
    @RequestMapping("index")
    @ResponseBody
    public String userIndex(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) {
        return "index";
    }

    @RequiresPermissions("user:add")//权限管理;
    @RequestMapping("add")
    @ResponseBody
    public String userAdd(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) {

        String hashAlgorithmName = "MD5";//加密方式
        Object crdentials = "123456";//密码原值
        String slat = "kaysonzhang";

        SecureRandomNumberGenerator secureRandom = new SecureRandomNumberGenerator();
        String hex = secureRandom.nextBytes(8).toHex();

        ByteSource salt = ByteSource.Util.bytes(hex);//以账号作为盐值
        int hashIterations = 1024;//加密1024次
        SimpleHash hash = new SimpleHash(hashAlgorithmName,crdentials,salt,hashIterations);
        logger.info(hash.toString());

        User user = new User();
        user.setAccount("kaysonzhang");
        user.setNickname("dong");
        user.setSalt(hex);
        user.setPassword(hash.toString());
        user.setStatus(1);
        user.setCreateTime(DateUtil.getUnixTime());
        user.setLastLoginTime(DateUtil.getUnixTime());

        userService.add(user);

        return "welcome index ";
    }
}
