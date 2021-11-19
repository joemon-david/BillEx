package com.billex.utils.common;

public class Delay {

    public static void addDelay(int seconds){
        try {
            Thread.sleep(seconds*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
