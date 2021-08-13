package com.cuisf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {


    /**
     * 主界面
     *
     * @return
     */
    @GetMapping("/home")
    public String home() {
        return "home";
    }

    /**
     * 用户
     *
     * @return
     */
    @GetMapping("/users")
    public String users() {
        return "users";
    }


    /**
     * 角色
     * @return
     */
    @GetMapping("/roles")
    public String roles() {
        return "roles";
    }


    /**
     * 菜单
     *
     * @return
     */
    @GetMapping("/menus")
    public String menus() {
        return "menus";
    }


    /**
     * 其他
     *
     * @return
     */
    @GetMapping("/others")
    public String others() {
        return "others";
    }


    /**
     * 错误界面
     *
     * @return
     */
    @GetMapping("/error")
    public String error() {
        return "error";
    }
}