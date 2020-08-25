package com.autonomy.config;

import java.util.HashMap;
import java.util.LinkedHashMap;

import java.util.Map;

import javax.servlet.Filter;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.autonomy.filter.AddPrincipalToSessionFilter;
import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.OncePerRequestFilter;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {

    @Bean("hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        // 指定加密方式为MD5
        credentialsMatcher.setHashAlgorithmName("MD5");
        // 加密次数
        credentialsMatcher.setHashIterations(2);
        //credentialsMatcher.setHashSalted(true);
        credentialsMatcher.setStoredCredentialsHexEncoded(true);
        return credentialsMatcher;
    }

    @Bean("userRealm")
    public UserRealm userRealm(@Qualifier("hashedCredentialsMatcher") HashedCredentialsMatcher matcher) {

        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return userRealm;
    }

    @Bean
    public ShiroFilterFactoryBean shirFilter(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {

        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        // 设置 SecurityManager
        bean.setSecurityManager(securityManager);

        Map<String, Filter> filters = new LinkedHashMap<>();

        /**
         * anon：匿名用户可访问 authc：认证用户可访问 user：使用rememberMe可访问 perms：对应权限可访问 role：对应角色权限可访问
         **/
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/static/**", "anon");
        filterMap.put("/favicon.ico", "anon");
        filterMap.put("/img/**", "anon");
        filterMap.put("/css/**", "anon");
        filterMap.put("/js/**", "anon");
        filterMap.put("/html/**", "user");
        filterMap.put("/auto/user/login", "anon");
        filterMap.put("/index", "user");
        filterMap.put("/logout", "logout");
        filterMap.put("/**", "user");

        // 设置登录成功跳转Url
        bean.setSuccessUrl("/index");
        // 设置登录跳转Url
        bean.setLoginUrl("/");
        // 设置未授权提示Url
        // bean.setUnauthorizedUrl("/error/unAuth");
        bean.setFilterChainDefinitionMap(filterMap);
        Map<String, Filter> fmap = new HashMap<>();
        fmap.put("addPrincipal", addPrincipalToSessionFilter());
        bean.setFilters(fmap);
        return bean;
    }

    /**
     * Shiro自定义过滤器（解决session丢失）
     * @return
     */
    @Bean
    public OncePerRequestFilter addPrincipalToSessionFilter() {
        return new AddPrincipalToSessionFilter();
    }

    /**
     * 注入 securityManager
     *
     * @throws Base64DecodingException
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(HashedCredentialsMatcher hashedCredentialsMatcher) throws Base64DecodingException {


        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 关联realm.
        securityManager.setRealm(userRealm(hashedCredentialsMatcher));

        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }

    /**
     * cookie对象
     */

    public SimpleCookie rememberMeCookie() {
        //对应前端cookie名称，checkbox的name=rememberMe
        SimpleCookie simpleCookie = new
                SimpleCookie("rememberMe"); //记住cookie生效时间三十天，单位秒
        simpleCookie.setMaxAge(2592000);
        return simpleCookie;
    }

    /**
     * cookie管理对象，记住我功能
     *
     * @param //securityManager
     * @return
     * @throws Base64DecodingException
     */
    public CookieRememberMeManager rememberMeManager() throws
            Base64DecodingException {
        CookieRememberMeManager cookieRememberMeManager = new
                CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie()); //rememberMe
        /* cookie加密的密钥 */
        cookieRememberMeManager.setCipherKey(Base64.decode("3AvhmFLUsOKTA3Kprsdag==")
        );
        return cookieRememberMeManager;
    }

    /**
     * AuthorizationAttributeSourceAdvisor
     * 作用：加入shiro注解的使用，不加入这个AOP注解不生效(shiro的注解 例如 @RequiresGuest)
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
            DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * DefaultAdvisorAutoProxyCreator
     * 作用: 用来扫描上下文寻找所有的Advistor(通知器), 将符合条件的Advisor应用到切入点的Bean中，需
     * 要在LifecycleBeanPostProcessor创建后才可以创建
     *
     * @return
     */
    @Bean
    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {

        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setUsePrefix(true);

        return defaultAdvisorAutoProxyCreator;
    }

    /**
     * 配置ShiroDialect，用于Thymeleaf和Shiro标签配合使用
     * @return
     */
    @Bean
    public ShiroDialect getShiroDialect() {
        return new ShiroDialect();
    }

}
