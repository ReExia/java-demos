package com.demo.orm.example.entity;


import com.demo.orm.core.annotation.Column;
import com.demo.orm.core.annotation.PK;
import com.demo.orm.core.annotation.Table;

import java.util.Date;

/**
 * @author setsuna
 * 账户
 */
@Table("account")
public class Account {

    private static final long serialVersionUID = 1L;

    @PK("id")
    @Column("id")
    private int id;

    @Column("username")
    private String username;

    @Column("money")
    private double money;

    @Column("tran_time")
    private Date tranTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Date getTranTime() {
        return tranTime;
    }

    public void setTranTime(Date tranTime) {
        this.tranTime = tranTime;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", money=" + money +
                ", tranTime=" + tranTime +
                '}';
    }
}
