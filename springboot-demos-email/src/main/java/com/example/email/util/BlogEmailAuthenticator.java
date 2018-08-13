package com.example.email.util;

import javax.mail.PasswordAuthentication;

public class BlogEmailAuthenticator extends  javax.mail.Authenticator{

    private String username;

    private String password;

    public BlogEmailAuthenticator(String username, String password){
        this.username = username;
        this.password = password;
    }

    public PasswordAuthentication getPasswordAuthentication(){
        return new PasswordAuthentication(this.username,  this.password);
    }


}
