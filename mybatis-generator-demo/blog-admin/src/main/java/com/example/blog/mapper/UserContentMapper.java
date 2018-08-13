package com.example.blog.mapper;

import com.example.blog.entity.UserContent;
import org.springframework.stereotype.Component;

@Component
public interface UserContentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_content
     *
     * @mbg.generated Tue Jul 17 16:26:37 CST 2018
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_content
     *
     * @mbg.generated Tue Jul 17 16:26:37 CST 2018
     */
    int insert(UserContent record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_content
     *
     * @mbg.generated Tue Jul 17 16:26:37 CST 2018
     */
    int insertSelective(UserContent record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_content
     *
     * @mbg.generated Tue Jul 17 16:26:37 CST 2018
     */
    UserContent selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_content
     *
     * @mbg.generated Tue Jul 17 16:26:37 CST 2018
     */
    int updateByPrimaryKeySelective(UserContent record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_content
     *
     * @mbg.generated Tue Jul 17 16:26:37 CST 2018
     */
    int updateByPrimaryKey(UserContent record);
}