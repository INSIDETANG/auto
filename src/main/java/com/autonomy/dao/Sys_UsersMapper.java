package com.autonomy.dao;

import com.autonomy.pojo.Sys_Users;

public interface Sys_UsersMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_users
     *
     * @mbg.generated Mon Jun 08 12:19:15 CST 2020
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_users
     *
     * @mbg.generated Mon Jun 08 12:19:15 CST 2020
     */
    int insert(Sys_Users record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_users
     *
     * @mbg.generated Mon Jun 08 12:19:15 CST 2020
     */
    int insertSelective(Sys_Users record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_users
     *
     * @mbg.generated Mon Jun 08 12:19:15 CST 2020
     */
    Sys_Users selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_users
     *
     * @mbg.generated Mon Jun 08 12:19:15 CST 2020
     */
    int updateByPrimaryKeySelective(Sys_Users record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_users
     *
     * @mbg.generated Mon Jun 08 12:19:15 CST 2020
     */
    int updateByPrimaryKey(Sys_Users record);
}