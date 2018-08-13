package com.example.solr.entity;

public class Person {

    private String personName;

    private String personSex;

    private String personCode;

    private String personAddress;

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonSex() {
        return personSex;
    }

    public void setPersonSex(String personSex) {
        this.personSex = personSex;
    }

    public String getPersonCode() {
        return personCode;
    }

    public void setPersonCode(String personCode) {
        this.personCode = personCode;
    }

    public String getPersonAddress() {
        return personAddress;
    }

    public void setPersonAddress(String personAddress) {
        this.personAddress = personAddress;
    }

    @Override
    public String toString() {
        return "Person{" +
                "personName='" + personName + '\'' +
                ", personSex='" + personSex + '\'' +
                ", personCode='" + personCode + '\'' +
                ", personAddress='" + personAddress + '\'' +
                '}';
    }
}
