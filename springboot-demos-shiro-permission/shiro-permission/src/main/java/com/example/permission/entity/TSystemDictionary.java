package com.example.permission.entity;

public class TSystemDictionary {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_system_dictionary.id
     *
     * @mbg.generated Wed Aug 01 17:03:43 CST 2018
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_system_dictionary.sn
     *
     * @mbg.generated Wed Aug 01 17:03:43 CST 2018
     */
    private String sn;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_system_dictionary.system_dictionary_name
     *
     * @mbg.generated Wed Aug 01 17:03:43 CST 2018
     */
    private String systemDictionaryName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_system_dictionary.intro
     *
     * @mbg.generated Wed Aug 01 17:03:43 CST 2018
     */
    private String intro;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_system_dictionary.state
     *
     * @mbg.generated Wed Aug 01 17:03:43 CST 2018
     */
    private Integer state;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_system_dictionary.id
     *
     * @return the value of t_system_dictionary.id
     *
     * @mbg.generated Wed Aug 01 17:03:43 CST 2018
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_system_dictionary.id
     *
     * @param id the value for t_system_dictionary.id
     *
     * @mbg.generated Wed Aug 01 17:03:43 CST 2018
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_system_dictionary.sn
     *
     * @return the value of t_system_dictionary.sn
     *
     * @mbg.generated Wed Aug 01 17:03:43 CST 2018
     */
    public String getSn() {
        return sn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_system_dictionary.sn
     *
     * @param sn the value for t_system_dictionary.sn
     *
     * @mbg.generated Wed Aug 01 17:03:43 CST 2018
     */
    public void setSn(String sn) {
        this.sn = sn == null ? null : sn.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_system_dictionary.system_dictionary_name
     *
     * @return the value of t_system_dictionary.system_dictionary_name
     *
     * @mbg.generated Wed Aug 01 17:03:43 CST 2018
     */
    public String getSystemDictionaryName() {
        return systemDictionaryName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_system_dictionary.system_dictionary_name
     *
     * @param systemDictionaryName the value for t_system_dictionary.system_dictionary_name
     *
     * @mbg.generated Wed Aug 01 17:03:43 CST 2018
     */
    public void setSystemDictionaryName(String systemDictionaryName) {
        this.systemDictionaryName = systemDictionaryName == null ? null : systemDictionaryName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_system_dictionary.intro
     *
     * @return the value of t_system_dictionary.intro
     *
     * @mbg.generated Wed Aug 01 17:03:43 CST 2018
     */
    public String getIntro() {
        return intro;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_system_dictionary.intro
     *
     * @param intro the value for t_system_dictionary.intro
     *
     * @mbg.generated Wed Aug 01 17:03:43 CST 2018
     */
    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_system_dictionary.state
     *
     * @return the value of t_system_dictionary.state
     *
     * @mbg.generated Wed Aug 01 17:03:43 CST 2018
     */
    public Integer getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_system_dictionary.state
     *
     * @param state the value for t_system_dictionary.state
     *
     * @mbg.generated Wed Aug 01 17:03:43 CST 2018
     */
    public void setState(Integer state) {
        this.state = state;
    }
}