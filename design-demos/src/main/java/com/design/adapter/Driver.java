package com.design.adapter;

public class Driver {

    private Car car;

    public void drive(){
        car.drive();
    }

    public Driver(Car car){
        this.car = car;
    }

}
