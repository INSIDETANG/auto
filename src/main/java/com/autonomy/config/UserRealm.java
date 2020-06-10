package com.autonomy.config;

import com.autonomy.pojo.Sys_Users;
import com.autonomy.service.Sys_UsersService;
import com.autonomy.service.impl.Sys_UsersServiceImpl;
import org.apache.shiro.SecurityUtils;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 自定义Realm，实现授权与认证
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    Sys_UsersServiceImpl sys_usersService;

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return "userRealm";
    }

    /**
     * 用户授权
     **/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        System.out.println("===执行授权===");

        Subject subject = SecurityUtils.getSubject();
        return null;
    }

    /**
     * 用户认证
     **/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authenticationToken) throws AuthenticationException {

        System.out.println("===执行认证===");

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        System.out.println(token.getCredentials().toString());
        String password = new String((char[])token.getCredentials());
        Sys_Users user=sys_usersService.selectByAccountPwd(token.getUsername());
        if (null != user && user.getLocked() == false){
            throw new LockedAccountException("msg.lockedAccountException");
        }else if (null == user) {
            throw new AuthenticationException("msg.authenticationException");
        }/*else if (!user.getPassword().equals(password)) {
            throw new IncorrectCredentialsException("密码不正确！");
        }*/
        ByteSource credentialsSalt = ByteSource.Util.bytes(user.getSalt());
        return new SimpleAuthenticationInfo(user, user.getPassword(), credentialsSalt, getName());
    }

    public static void main(String[] args) {
        Md5Hash md5 = new Md5Hash("admin", null, 2);
        System.out.println(md5.toString());
        SimpleHash simpleHash = new SimpleHash("MD5", "admin", "salt", 2);
        System.out.println(simpleHash.toString());
    }
}
