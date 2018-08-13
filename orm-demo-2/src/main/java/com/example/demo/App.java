package com.example.demo;

import com.example.core.config.holder.PropertyHolder;
import com.example.core.config.manager.ProPertyHolderManager;
import com.example.core.convertor.EntityToParamtersConvertor;
import com.example.core.jdbc.JdbcUtil;
import com.example.demo.domain.Book;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class App {

    public static void main(String[] args) {

        for (int i = 0 ; i < 1000; i ++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    JdbcUtil.executeUpdate("");
                }
            }).start();
        }

    }
}
