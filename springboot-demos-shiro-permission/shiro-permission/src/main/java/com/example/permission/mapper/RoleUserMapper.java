package com.example.permission.mapper;

import com.example.permission.entity.RoleUser;

public interface RoleUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_menu
     *
     * @mbg.generated Wed Aug 01 17:03:43 CST 2018
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_menu
     *
     * @mbg.generated Wed Aug 01 17:03:43 CST 2018
     */
    int insert(RoleUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_menu
     *
     * @mbg.generated Wed Aug 01 17:03:43 CST 2018
     */
    int insertSelective(RoleUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_menu
     *
     * @mbg.generated Wed Aug 01 17:03:43 CST 2018
     */
    RoleUser selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_menu
     *
     * @mbg.generated Wed Aug 01 17:03:43 CST 2018
     */
    int updateByPrimaryKeySelective(RoleUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_menu
     *
     * @mbg.generated Wed Aug 01 17:03:43 CST 2018
     */
    int updateByPrimaryKey(RoleUser record);
}