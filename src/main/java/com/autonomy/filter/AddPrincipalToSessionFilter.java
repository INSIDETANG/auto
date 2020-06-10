package com.autonomy.filter;

import com.autonomy.pojo.Sys_Users;
import com.autonomy.service.impl.Sys_UsersServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.servlet.OncePerRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddPrincipalToSessionFilter extends OncePerRequestFilter {

    @Autowired
    Sys_UsersServiceImpl sys_usersService;

    @Override
    protected void doFilterInternal(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        //查询当前用户的信息
        Subject subject = SecurityUtils.getSubject();
        //判断用户是不是通过自动登录进来的
        if (subject.isRemembered()) {
            Sys_Users user2 = (Sys_Users) subject.getPrincipal();
            String account = user2.getAccount();
            System.out.println(account + "..........");
            if (account == null) {
                return;
            }
            //根据用户名查询该用户的信息
            Sys_Users user = sys_usersService.selectByAccountPwd(account);
            if (user == null) return;
            //由于是继承的OncePerRequestFilter，没办法设置session
            //这里发现可以将servletReques强转为子类，所以使用request.getsiion())
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpSession session = request.getSession();
            //当session为空的时候
            if (session.getAttribute("currentUser") == null) {
                //把查询到的用户信息设置为session，时效为3600秒
                session.setAttribute("currentUser", user);
                session.setMaxInactiveInterval(3600);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
