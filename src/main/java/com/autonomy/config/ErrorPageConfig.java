package com.autonomy.config;

import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Configuration
public class ErrorPageConfig implements ErrorViewResolver {

    @Override
    public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> model) {
        ModelAndView mv = new ModelAndView();
        switch (status) {
            case NOT_FOUND:
                //mv.setViewName("error/404");
                mv.setViewName("redirect:/static/error/404.html");
                break;
            default:
                mv.setViewName("redirect:/static/error/error.html");
        }
        return mv;
    }
}
