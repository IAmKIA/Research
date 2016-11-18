package com.tron;

import processing.core.PVector;

public class Player {
    int color;
    PVector[] trail = new PVector[300];
    PVector dir;
    public Player(int color, PVector start, PVector dir) {
        this.color = color;
        this.dir = dir;
        for(int i = 0; i< 300;i++){
            trail[i] = start;
        }
    }

    void shiftTrail(){
        for(int i = 298; i>=0;i--){
            trail[i+1]= trail[i].copy();
        }
        trail[0].add(dir);
        for(int i = 1;i<299;i++){
            if (trail[i].equals(trail[0])){
                System.out.println("die");
            }
        }
    }
}
