package com.autonomy.controller.users;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auto/user/")
public class LoginCtroller {

    @RequestMapping("login")
    public String login(){
        return "login";
    }
}
