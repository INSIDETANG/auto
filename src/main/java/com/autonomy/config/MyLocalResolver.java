package com.autonomy.config;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class MyLocalResolver  {

    public LocaleResolver resolveLocale(HttpServletRequest request) {
        /*String l = request.getParameter("l");
        Locale locale = Locale.getDefault();//根据浏览器默认   new Locale("en","US");
        if(!"".equals(l)&&l!=null){
            String[] s = l.split("_");
            locale = new Locale(s[0],s[1]);
        }*/
        return new SessionLocaleResolver();
    }
}
