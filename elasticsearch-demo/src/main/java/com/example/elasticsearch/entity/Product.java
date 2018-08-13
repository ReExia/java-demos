package com.example.elasticsearch.entity;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "shop", type = "product")
public class Product {

    /**
     * fielddata 使用排序等必须加
     */
    @Field(type = FieldType.Text,fielddata = true)
    private String productName;

    @Field(type = FieldType.Text,fielddata = true)

    private String desc;

    @Field(type = FieldType.Text,fielddata = true)
    private String producer;

    /**
     * Caused by: java.lang.IllegalArgumentException: Fielddata is disabled on text fields by default.
     */
    @Field(type = FieldType.Integer,fielddata = true)
    private Integer price;

    @Field(type = FieldType.Object,fielddata = true)
    private ProductTag tags;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public ProductTag getTags() {
        return tags;
    }

    public void setTags(ProductTag tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", desc='" + desc + '\'' +
                ", producer='" + producer + '\'' +
                ", price=" + price +
                ", tags=" + tags +
                '}';
    }
}
