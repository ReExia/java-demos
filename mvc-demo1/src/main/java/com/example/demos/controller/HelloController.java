package com.example.demos.controller;

import com.example.mvc.base.BaseServlet;
import com.example.demos.entity.Book;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

@WebServlet(name = "HelloController", urlPatterns = "/hello")
public class HelloController extends BaseServlet {

    //http://localhost:8080/hello?param=list
    public String list(HttpServletRequest request){
        //System.out.println("xxxx");
        return "forward:/index.jsp";
    }

    //http://localhost:8080/hello?param=add&id=2&name=语文书
    public String add(Book book){
        System.out.println(book.toString());
        return "forward:/index.jsp";
    }

}
