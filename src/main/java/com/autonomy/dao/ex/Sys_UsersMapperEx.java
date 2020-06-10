package com.autonomy.dao.ex;

import com.autonomy.pojo.Sys_Users;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

//@Repository
public interface Sys_UsersMapperEx {

    /**
     * 根据用户查询
     * @param user
     * @return
     */
    Sys_Users selectByAccountPwd(String account);
}
