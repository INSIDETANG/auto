package com.autonomy.service;

import com.autonomy.pojo.Sys_Users;
import org.springframework.stereotype.Service;

public interface Sys_UsersService {

    /**
     * 根据用户查询
     * @param user
     * @return
     */
    Sys_Users selectByAccountPwd(String account);
}
