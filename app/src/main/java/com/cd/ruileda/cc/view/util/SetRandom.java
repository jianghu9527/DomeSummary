package com.cd.ruileda.cc.view.util;

import java.util.Random;

public  class SetRandom {

    public static   int getNumber(int Ranger){
        Random rand = new Random();
       return rand.nextInt(Ranger);
    }
}
