package com.design.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Bus {

    private static final Logger LOGGER = LoggerFactory.getLogger(Bus.class);

    public void run(){
        LOGGER.info("公共汽车在行驶");
    }


}
