package com.example.elasticsearch.entity;

public class EmployeeInfo {

    // 性格
    private String bio;
    private Integer age;
    // 兴趣爱好
    private String[] interests;

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String[] getInterests() {
        return interests;
    }

    public void setInterests(String[] interests) {
        this.interests = interests;
    }
}
