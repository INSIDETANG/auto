package com.autonomy.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import java.util.Locale;

@Component
public class I18nUtil implements MessageSourceAware {

    private static MessageSource messageSource;

    @Override
    public void setMessageSource(@Nullable MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * @param message 消息所对应的变量名
     * @return 返回不包含变量的对应语言的消息
     * @author qbanxiaoli
     * @description 从配置文件中找到message对应语言的消息
     */
    public static String getMessage(String message) {
        //获取当前请求的语言类型
        Locale locale = LocaleContextHolder.getLocale();
        //返回配置文件中找到message对应语言的消息
        //如果在指定的locale中没有找到消息，则使用默认的消息。
        return messageSource.getMessage(message, null, message, locale);
    }
}
