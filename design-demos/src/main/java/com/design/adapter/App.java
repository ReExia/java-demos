package com.design.adapter;

public class App {

    public static void main(String[] args) {

        //开公交车
        Driver driver = new Driver(new BusAdapter());
        driver.drive();

        //开私人汽车
        Driver driver1 = new Driver(new PersonCar());
        driver1.drive();
    }

}
