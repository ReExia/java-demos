package com.design.adapter;

public class BusAdapter implements Car {

    private Bus bus;

    public BusAdapter(){
        this.bus = new Bus();
    }

    @Override
    public void drive() {
        bus.run();
    }
}
