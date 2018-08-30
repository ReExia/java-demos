package com.effective.chapter1;

import org.apache.commons.collections4.MapUtils;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class App {

    public static void main(String[] args) {

        Services.registerDefaultProvider(DEFAULT_PROVIDER);
        Services.registerProvider("comp", COMP_PROVIDER);
        Services.registerProvider("armed", ARMED_PROVIDER);

        Service s1 = Services.newInstance();
        Service s2 = Services.newInstance("comp");
        Service s3 = Services.newInstance("armed");

        System.out.printf("%s, %s, %s%n", s1, s2, s3);
    }

    private static Provider DEFAULT_PROVIDER = () -> new Service() {
        @Override
        public String toString() {
            return "Default service";
        }
    };

    private static Provider COMP_PROVIDER = () -> new Service() {
        @Override
        public String toString() {
            return "Complementary service";
        }
    };

    private static Provider ARMED_PROVIDER = () -> new Service() {
        @Override
        public String toString() {
            return "Armed service";
        }
    };

}
