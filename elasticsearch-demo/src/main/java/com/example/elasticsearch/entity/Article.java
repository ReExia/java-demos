package com.example.elasticsearch.entity;


import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import java.util.Date;
import java.util.List;

@Document(indexName = "blog_article", type = "article")
public class Article {

    @Field(index=true ,store=true, type=FieldType.Text)
    private String articleId;

    @Field(index=true ,store=true, type=FieldType.Text)
    private String title;

    @Field(index=true ,store=true, type=FieldType.Text)
    private String author;

    @Field(index=true ,store=true, type=FieldType.Text)
    private List<String> comment;

    @Field(index=true ,store=true, type=FieldType.Text)
    private String content;

    @Field(index=true ,store=true, type=FieldType.Text)
    private Date createDate;

    @Field(index=true ,store=true, type=FieldType.Text)
    private Date updateDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<String> getComment() {
        return comment;
    }

    public void setComment(List<String> comment) {
        this.comment = comment;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }
}
