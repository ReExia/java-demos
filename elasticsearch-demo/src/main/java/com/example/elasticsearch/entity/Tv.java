package com.example.elasticsearch.entity;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Document(indexName = "tvs", type = "sales")
public class Tv {

    @Field(type = FieldType.Integer)
    private Integer price;

    @Field(type = FieldType.Text)
    private String color;

    @Field(type = FieldType.Text)
    private String brand;

    @Field(type = FieldType.Date)
    private Date sold_date;

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Date getSold_date() {
        return sold_date;
    }

    public void setSold_date(Date sold_date) {
        this.sold_date = sold_date;
    }

    @Override
    public String toString() {
        return "Tv{" +
                "price=" + price +
                ", color='" + color + '\'' +
                ", brand='" + brand + '\'' +
                ", sold_date=" + sold_date +
                '}';
    }
}
