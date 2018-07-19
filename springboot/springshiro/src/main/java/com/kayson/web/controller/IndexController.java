package com.kayson.web.controller;

import com.kayson.web.model.Service.UserPermissionService;
import com.kayson.web.model.Service.UserRoleService;
import com.kayson.web.model.Service.UserService;
import com.kayson.web.model.domain.User;
import com.sun.deploy.net.HttpResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/")
public class IndexController {

    private static Logger logger = LogManager.getLogger(IndexController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private UserPermissionService userPermissionService;

    @RequestMapping("index")
    @ResponseBody
    public String index() {
        return "welcome index ";
    }

    @RequestMapping("login")
    @ResponseBody
    public String login(HttpSession session){
        String username = "kaysonzhang";
        String password = "123456";

        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(usernamePasswordToken);   //完成登录
            User user=(User) subject.getPrincipal();
            session.setAttribute("user", user);
            return "login success";

        } catch (UnknownAccountException ex) {
            return "用户名没有找到";

        } catch (IncorrectCredentialsException ex) {
            System.out.print(ex);
            return "用户名密码不匹配";

        }catch (AuthenticationException e) {
            return "其他的登录错误";
        }

     }

    @RequestMapping("logout")
    @ResponseBody
    public String logout(HttpServletResponse response, HttpSession session) {
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.logout();
            session.removeAttribute("user");
            response.sendRedirect("/index");
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @RequestMapping("403")
    //@ResponseBody
    public String np(ModelMap map) {
        map.addAttribute("host", "http://kaysonzhang.com");
        return "403";
    }

}


/*
@RestController
@RequestMapping(value="/users")     // 通过这里配置使下面的映射都在/users下，可去除
public class UserController {

    static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long, User>());

    @RequestMapping(value="/", method=RequestMethod.GET)
    public List<User> getUserList() {
        // 处理"/users/"的GET请求，用来获取用户列表
        // 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递
        List<User> r = new ArrayList<User>(users.values());
        return r;
    }

    @RequestMapping(value="/", method=RequestMethod.POST)
    public String postUser(@ModelAttribute User user) {
        // 处理"/users/"的POST请求，用来创建User
        // 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
        users.put(user.getId(), user);
        return "success";
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public User getUser(@PathVariable Long id) {
        // 处理"/users/{id}"的GET请求，用来获取url中id值的User信息
        // url中的id可通过@PathVariable绑定到函数的参数中
        return users.get(id);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public String putUser(@PathVariable Long id, @ModelAttribute User user) {
        // 处理"/users/{id}"的PUT请求，用来更新User信息
        User u = users.get(id);
        u.setName(user.getName());
        u.setAge(user.getAge());
        users.put(id, u);
        return "success";
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long id) {
        // 处理"/users/{id}"的DELETE请求，用来删除User
        users.remove(id);
        return "success";
    }

}*/
