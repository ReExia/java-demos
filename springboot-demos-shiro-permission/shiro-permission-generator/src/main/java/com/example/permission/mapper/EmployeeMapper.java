package com.example.permission.mapper;

import com.example.permission.entity.Employee;

public interface EmployeeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_employee
     *
     * @mbg.generated Wed Aug 01 17:03:43 CST 2018
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_employee
     *
     * @mbg.generated Wed Aug 01 17:03:43 CST 2018
     */
    int insert(Employee record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_employee
     *
     * @mbg.generated Wed Aug 01 17:03:43 CST 2018
     */
    int insertSelective(Employee record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_employee
     *
     * @mbg.generated Wed Aug 01 17:03:43 CST 2018
     */
    Employee selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_employee
     *
     * @mbg.generated Wed Aug 01 17:03:43 CST 2018
     */
    int updateByPrimaryKeySelective(Employee record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_employee
     *
     * @mbg.generated Wed Aug 01 17:03:43 CST 2018
     */
    int updateByPrimaryKey(Employee record);
}