package com.mccorporation.mcjores.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by MCjores on 04.10.2019.
 */
//wind":{"speed":4.1,"deg":80}
public class Wind {

    @SerializedName("speed")
    @Expose
    private float speed;

    @SerializedName("deg")
    @Expose
    private int deg;

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setDeg(int deg) {
        this.deg = deg;
    }

    public float getSpeed() {
        return speed;

    }

    public int getDeg() {
        return deg;
    }
}
