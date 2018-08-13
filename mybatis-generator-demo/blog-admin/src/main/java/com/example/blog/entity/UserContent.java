package com.example.blog.entity;

import java.util.Date;

public class UserContent {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_content.id
     *
     * @mbg.generated Tue Jul 17 16:26:37 CST 2018
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_content.user_id
     *
     * @mbg.generated Tue Jul 17 16:26:37 CST 2018
     */
    private String userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_content.title
     *
     * @mbg.generated Tue Jul 17 16:26:37 CST 2018
     */
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_content.category
     *
     * @mbg.generated Tue Jul 17 16:26:37 CST 2018
     */
    private String category;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_content.content
     *
     * @mbg.generated Tue Jul 17 16:26:37 CST 2018
     */
    private String content;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_content.personal
     *
     * @mbg.generated Tue Jul 17 16:26:37 CST 2018
     */
    private String personal;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_content.rpt_time
     *
     * @mbg.generated Tue Jul 17 16:26:37 CST 2018
     */
    private Date rptTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_content.img_url
     *
     * @mbg.generated Tue Jul 17 16:26:37 CST 2018
     */
    private String imgUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_content.nick_name
     *
     * @mbg.generated Tue Jul 17 16:26:37 CST 2018
     */
    private String nickName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_content.upvote
     *
     * @mbg.generated Tue Jul 17 16:26:37 CST 2018
     */
    private Integer upvote;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_content.downvote
     *
     * @mbg.generated Tue Jul 17 16:26:37 CST 2018
     */
    private Integer downvote;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_content.comment_num
     *
     * @mbg.generated Tue Jul 17 16:26:37 CST 2018
     */
    private String commentNum;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_content.id
     *
     * @return the value of t_user_content.id
     *
     * @mbg.generated Tue Jul 17 16:26:37 CST 2018
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_content.id
     *
     * @param id the value for t_user_content.id
     *
     * @mbg.generated Tue Jul 17 16:26:37 CST 2018
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_content.user_id
     *
     * @return the value of t_user_content.user_id
     *
     * @mbg.generated Tue Jul 17 16:26:37 CST 2018
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_content.user_id
     *
     * @param userId the value for t_user_content.user_id
     *
     * @mbg.generated Tue Jul 17 16:26:37 CST 2018
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_content.title
     *
     * @return the value of t_user_content.title
     *
     * @mbg.generated Tue Jul 17 16:26:37 CST 2018
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_content.title
     *
     * @param title the value for t_user_content.title
     *
     * @mbg.generated Tue Jul 17 16:26:37 CST 2018
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_content.category
     *
     * @return the value of t_user_content.category
     *
     * @mbg.generated Tue Jul 17 16:26:37 CST 2018
     */
    public String getCategory() {
        return category;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_content.category
     *
     * @param category the value for t_user_content.category
     *
     * @mbg.generated Tue Jul 17 16:26:37 CST 2018
     */
    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_content.content
     *
     * @return the value of t_user_content.content
     *
     * @mbg.generated Tue Jul 17 16:26:37 CST 2018
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_content.content
     *
     * @param content the value for t_user_content.content
     *
     * @mbg.generated Tue Jul 17 16:26:37 CST 2018
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_content.personal
     *
     * @return the value of t_user_content.personal
     *
     * @mbg.generated Tue Jul 17 16:26:37 CST 2018
     */
    public String getPersonal() {
        return personal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_content.personal
     *
     * @param personal the value for t_user_content.personal
     *
     * @mbg.generated Tue Jul 17 16:26:37 CST 2018
     */
    public void setPersonal(String personal) {
        this.personal = personal == null ? null : personal.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_content.rpt_time
     *
     * @return the value of t_user_content.rpt_time
     *
     * @mbg.generated Tue Jul 17 16:26:37 CST 2018
     */
    public Date getRptTime() {
        return rptTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_content.rpt_time
     *
     * @param rptTime the value for t_user_content.rpt_time
     *
     * @mbg.generated Tue Jul 17 16:26:37 CST 2018
     */
    public void setRptTime(Date rptTime) {
        this.rptTime = rptTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_content.img_url
     *
     * @return the value of t_user_content.img_url
     *
     * @mbg.generated Tue Jul 17 16:26:37 CST 2018
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_content.img_url
     *
     * @param imgUrl the value for t_user_content.img_url
     *
     * @mbg.generated Tue Jul 17 16:26:37 CST 2018
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_content.nick_name
     *
     * @return the value of t_user_content.nick_name
     *
     * @mbg.generated Tue Jul 17 16:26:37 CST 2018
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_content.nick_name
     *
     * @param nickName the value for t_user_content.nick_name
     *
     * @mbg.generated Tue Jul 17 16:26:37 CST 2018
     */
    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_content.upvote
     *
     * @return the value of t_user_content.upvote
     *
     * @mbg.generated Tue Jul 17 16:26:37 CST 2018
     */
    public Integer getUpvote() {
        return upvote;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_content.upvote
     *
     * @param upvote the value for t_user_content.upvote
     *
     * @mbg.generated Tue Jul 17 16:26:37 CST 2018
     */
    public void setUpvote(Integer upvote) {
        this.upvote = upvote;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_content.downvote
     *
     * @return the value of t_user_content.downvote
     *
     * @mbg.generated Tue Jul 17 16:26:37 CST 2018
     */
    public Integer getDownvote() {
        return downvote;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_content.downvote
     *
     * @param downvote the value for t_user_content.downvote
     *
     * @mbg.generated Tue Jul 17 16:26:37 CST 2018
     */
    public void setDownvote(Integer downvote) {
        this.downvote = downvote;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_content.comment_num
     *
     * @return the value of t_user_content.comment_num
     *
     * @mbg.generated Tue Jul 17 16:26:37 CST 2018
     */
    public String getCommentNum() {
        return commentNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_content.comment_num
     *
     * @param commentNum the value for t_user_content.comment_num
     *
     * @mbg.generated Tue Jul 17 16:26:37 CST 2018
     */
    public void setCommentNum(String commentNum) {
        this.commentNum = commentNum == null ? null : commentNum.trim();
    }
}