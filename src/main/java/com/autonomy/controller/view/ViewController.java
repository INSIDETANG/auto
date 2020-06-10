package com.autonomy.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import static org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME;

@Controller
public class ViewController {

    @Autowired
    LocaleResolver localeResolver;

    @RequestMapping(value = {"","/","login"})
    public String login(HttpServletRequest request, HttpServletResponse response){
        HttpSession session=request.getSession();
        String l=request.getParameter("l");
        if (l!=null&&!"".equals(l)){
            String[] s = l.split("_");
            Locale locale = new Locale(s[0],s[1]);
            localeResolver.setLocale(request,response,locale);
        }
        return "login";
    }

    @RequestMapping(value = {"index"})
    public String index(){
        return "index";
    }


}
