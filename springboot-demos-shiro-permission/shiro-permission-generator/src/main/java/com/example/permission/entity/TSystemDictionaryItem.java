package com.example.permission.entity;

public class TSystemDictionaryItem {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_system_dictionary_item.id
     *
     * @mbg.generated Wed Aug 01 17:03:43 CST 2018
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_system_dictionary_item.parent_id
     *
     * @mbg.generated Wed Aug 01 17:03:43 CST 2018
     */
    private String parentId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_system_dictionary_item.system_dictionary_item_name
     *
     * @mbg.generated Wed Aug 01 17:03:43 CST 2018
     */
    private String systemDictionaryItemName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_system_dictionary_item.requence
     *
     * @mbg.generated Wed Aug 01 17:03:43 CST 2018
     */
    private Integer requence;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_system_dictionary_item.intro
     *
     * @mbg.generated Wed Aug 01 17:03:43 CST 2018
     */
    private String intro;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_system_dictionary_item.id
     *
     * @return the value of t_system_dictionary_item.id
     *
     * @mbg.generated Wed Aug 01 17:03:43 CST 2018
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_system_dictionary_item.id
     *
     * @param id the value for t_system_dictionary_item.id
     *
     * @mbg.generated Wed Aug 01 17:03:43 CST 2018
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_system_dictionary_item.parent_id
     *
     * @return the value of t_system_dictionary_item.parent_id
     *
     * @mbg.generated Wed Aug 01 17:03:43 CST 2018
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_system_dictionary_item.parent_id
     *
     * @param parentId the value for t_system_dictionary_item.parent_id
     *
     * @mbg.generated Wed Aug 01 17:03:43 CST 2018
     */
    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_system_dictionary_item.system_dictionary_item_name
     *
     * @return the value of t_system_dictionary_item.system_dictionary_item_name
     *
     * @mbg.generated Wed Aug 01 17:03:43 CST 2018
     */
    public String getSystemDictionaryItemName() {
        return systemDictionaryItemName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_system_dictionary_item.system_dictionary_item_name
     *
     * @param systemDictionaryItemName the value for t_system_dictionary_item.system_dictionary_item_name
     *
     * @mbg.generated Wed Aug 01 17:03:43 CST 2018
     */
    public void setSystemDictionaryItemName(String systemDictionaryItemName) {
        this.systemDictionaryItemName = systemDictionaryItemName == null ? null : systemDictionaryItemName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_system_dictionary_item.requence
     *
     * @return the value of t_system_dictionary_item.requence
     *
     * @mbg.generated Wed Aug 01 17:03:43 CST 2018
     */
    public Integer getRequence() {
        return requence;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_system_dictionary_item.requence
     *
     * @param requence the value for t_system_dictionary_item.requence
     *
     * @mbg.generated Wed Aug 01 17:03:43 CST 2018
     */
    public void setRequence(Integer requence) {
        this.requence = requence;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_system_dictionary_item.intro
     *
     * @return the value of t_system_dictionary_item.intro
     *
     * @mbg.generated Wed Aug 01 17:03:43 CST 2018
     */
    public String getIntro() {
        return intro;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_system_dictionary_item.intro
     *
     * @param intro the value for t_system_dictionary_item.intro
     *
     * @mbg.generated Wed Aug 01 17:03:43 CST 2018
     */
    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
    }
}