package com.example.ngapi.model;

public class BoardVO {

    //id编号
    private String id;

    //资金渠道
    private String capitalChannel;

    //未放款项目金额含失败
    private String unloanMoneyAll;

    //未放款项目失败笔数
    private String unloanfailedTimes;

    //未放款金额
    private String unLoanMoney;

    //未放款笔数
    private String unloanTimes;

    //失败笔数
    private String failedTimes;

    //失败金额
    private String failedMoney;

    public BoardVO() {
    }

    public BoardVO(String id, String capitalChannel, String unloanMoneyAll, String unloanfailedTimes, String unLoanMoney, String unloanTimes, String failedTimes, String failedMoney) {
        this.id = id;
        this.capitalChannel = capitalChannel;
        this.unloanMoneyAll = unloanMoneyAll;
        this.unloanfailedTimes = unloanfailedTimes;
        this.unLoanMoney = unLoanMoney;
        this.unloanTimes = unloanTimes;
        this.failedTimes = failedTimes;
        this.failedMoney = failedMoney;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCapitalChannel() {
        return capitalChannel;
    }

    public void setCapitalChannel(String capitalChannel) {
        this.capitalChannel = capitalChannel;
    }

    public String getUnloanMoneyAll() {
        return unloanMoneyAll;
    }

    public void setUnloanMoneyAll(String unloanMoneyAll) {
        this.unloanMoneyAll = unloanMoneyAll;
    }

    public String getUnloanfailedTimes() {
        return unloanfailedTimes;
    }

    public void setUnloanfailedTimes(String unloanfailedTimes) {
        this.unloanfailedTimes = unloanfailedTimes;
    }

    public String getUnLoanMoney() {
        return unLoanMoney;
    }

    public void setUnLoanMoney(String unLoanMoney) {
        this.unLoanMoney = unLoanMoney;
    }

    public String getUnloanTimes() {
        return unloanTimes;
    }

    public void setUnloanTimes(String unloanTimes) {
        this.unloanTimes = unloanTimes;
    }

    public String getFailedTimes() {
        return failedTimes;
    }

    public void setFailedTimes(String failedTimes) {
        this.failedTimes = failedTimes;
    }

    public String getFailedMoney() {
        return failedMoney;
    }

    public void setFailedMoney(String failedMoney) {
        this.failedMoney = failedMoney;
    }
}
