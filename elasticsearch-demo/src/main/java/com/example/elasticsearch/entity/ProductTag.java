package com.example.elasticsearch.entity;

import java.util.Arrays;

public class ProductTag {

    private String[] values;

    public String[] getValues() {
        return values;
    }

    public void setValues(String[] values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "ProductTag{" +
                "values=" + Arrays.toString(values) +
                '}';
    }
}
