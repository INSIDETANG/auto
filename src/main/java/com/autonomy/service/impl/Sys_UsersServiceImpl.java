package com.autonomy.service.impl;

import com.autonomy.dao.ex.Sys_UsersMapperEx;
import com.autonomy.pojo.Sys_Users;
import com.autonomy.service.Sys_UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户service
 */
@Service
public class Sys_UsersServiceImpl implements Sys_UsersService {

    @Autowired
    Sys_UsersMapperEx sys_usersMapperEx;

    @Override
    public Sys_Users selectByAccountPwd(String account) {
        return sys_usersMapperEx.selectByAccountPwd(account);
    }
}
