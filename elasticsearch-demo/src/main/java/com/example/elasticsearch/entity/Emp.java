package com.example.elasticsearch.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Document(indexName = "company", type = "emp")
public class Emp {

    @Field(index = true, type = FieldType.Text)
    private String empId;

    @Field(index = true, type = FieldType.Text)
    private String name;

    @Field(index = true, type = FieldType.Text)
    private Integer age;

    @Field(index = true, type = FieldType.Text)
    private String position;

    @Field(index = true, type = FieldType.Text)
    private String country;

    @Field(index = true, type = FieldType.Date)
    private Date joinDate;

    @Field(index = true, type = FieldType.Long)
    private Long salary;

    @Override
    public String toString() {
        return "Emp{" +
                "empId='" + empId + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", position='" + position + '\'' +
                ", country='" + country + '\'' +
                ", joinDate=" + joinDate +
                ", salary=" + salary +
                '}';
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }
}
