package com.autonomy.controller.users;

import com.autonomy.common.HttpResult;
import com.autonomy.common.ResultCode;
import com.autonomy.controller.BaseController;
import com.autonomy.pojo.Sys_Users;
import com.autonomy.service.impl.Sys_UsersServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/auto/user/")
public class LoginCtroller extends BaseController {

    @Autowired
    Sys_UsersServiceImpl sys_usersService;

    @RequestMapping("login")
    @ResponseBody
    public HttpResult login(@RequestBody Sys_Users user){
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(user.getAccount(), user.getPassword());
        // 执行认证登陆
        try {
            token.setRememberMe(user.getRememberMe());
            subject.login(token);
        } catch (UnknownAccountException uae) {
            return error(-1, ResultCode.UNKNOWNACCOUNTEXCEPTION.getResultMsg());
        } catch (IncorrectCredentialsException ice) {
            return error(-2,ResultCode.INCORRECTCREDENTIALSEXCEPTION.getResultMsg());
        } catch (LockedAccountException lae) {
            return error(-3,ResultCode.LOCKEDACCOUNTEXCEPTION.getResultMsg());
        } catch (ExcessiveAttemptsException eae) {
            return error(-4,ResultCode.EXCESSIVEATTEMPTSEXCEPTION.getResultMsg());
        } catch (AuthenticationException ae) {
            return error(-5,ResultCode.AUTHENTICATIONEXCEPTION.getResultMsg());
        }
        if (subject.isAuthenticated()) {
            //把当前用户放入session
            Session session = subject.getSession();
            Sys_Users suser = sys_usersService.selectByAccountPwd(user.getAccount());
            session.setAttribute("currentUser",suser);
            return responseLoginSucc(null);
        } else {
            token.clear();
            return responseLoginErr(null);
            //return "redirect:/login";
        }
    }
}
