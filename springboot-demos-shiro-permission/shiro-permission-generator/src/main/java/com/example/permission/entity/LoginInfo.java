package com.example.permission.entity;

public class LoginInfo {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_login_info.id
     *
     * @mbg.generated Wed Aug 01 17:03:43 CST 2018
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_login_info.username
     *
     * @mbg.generated Wed Aug 01 17:03:43 CST 2018
     */
    private String username;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_login_info.password
     *
     * @mbg.generated Wed Aug 01 17:03:43 CST 2018
     */
    private String password;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_login_info.user_type
     *
     * @mbg.generated Wed Aug 01 17:03:43 CST 2018
     */
    private Long userType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_login_info.state
     *
     * @mbg.generated Wed Aug 01 17:03:43 CST 2018
     */
    private Integer state;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_login_info.email
     *
     * @mbg.generated Wed Aug 01 17:03:43 CST 2018
     */
    private String email;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_login_info.url
     *
     * @mbg.generated Wed Aug 01 17:03:43 CST 2018
     */
    private String url;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_login_info.id
     *
     * @return the value of t_login_info.id
     *
     * @mbg.generated Wed Aug 01 17:03:43 CST 2018
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_login_info.id
     *
     * @param id the value for t_login_info.id
     *
     * @mbg.generated Wed Aug 01 17:03:43 CST 2018
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_login_info.username
     *
     * @return the value of t_login_info.username
     *
     * @mbg.generated Wed Aug 01 17:03:43 CST 2018
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_login_info.username
     *
     * @param username the value for t_login_info.username
     *
     * @mbg.generated Wed Aug 01 17:03:43 CST 2018
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_login_info.password
     *
     * @return the value of t_login_info.password
     *
     * @mbg.generated Wed Aug 01 17:03:43 CST 2018
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_login_info.password
     *
     * @param password the value for t_login_info.password
     *
     * @mbg.generated Wed Aug 01 17:03:43 CST 2018
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_login_info.user_type
     *
     * @return the value of t_login_info.user_type
     *
     * @mbg.generated Wed Aug 01 17:03:43 CST 2018
     */
    public Long getUserType() {
        return userType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_login_info.user_type
     *
     * @param userType the value for t_login_info.user_type
     *
     * @mbg.generated Wed Aug 01 17:03:43 CST 2018
     */
    public void setUserType(Long userType) {
        this.userType = userType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_login_info.state
     *
     * @return the value of t_login_info.state
     *
     * @mbg.generated Wed Aug 01 17:03:43 CST 2018
     */
    public Integer getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_login_info.state
     *
     * @param state the value for t_login_info.state
     *
     * @mbg.generated Wed Aug 01 17:03:43 CST 2018
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_login_info.email
     *
     * @return the value of t_login_info.email
     *
     * @mbg.generated Wed Aug 01 17:03:43 CST 2018
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_login_info.email
     *
     * @param email the value for t_login_info.email
     *
     * @mbg.generated Wed Aug 01 17:03:43 CST 2018
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_login_info.url
     *
     * @return the value of t_login_info.url
     *
     * @mbg.generated Wed Aug 01 17:03:43 CST 2018
     */
    public String getUrl() {
        return url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_login_info.url
     *
     * @param url the value for t_login_info.url
     *
     * @mbg.generated Wed Aug 01 17:03:43 CST 2018
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }
}