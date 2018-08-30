package com.design.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PersonCar implements Car{

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonCar.class);

    @Override
    public void drive() {
        LOGGER.info("私人汽车在行驶");
    }
}
