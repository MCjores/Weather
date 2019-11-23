package com.mccorporation.mcjores.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by MCjores on 04.02.2019.
 */
    //clouds":{"all":90}
public class Clouds {

    @SerializedName("all")
    @Expose
    private int Clouds;

    public void setClouds(int clouds) {
        Clouds = clouds;
    }

    public int getClouds() {
        return Clouds;
    }
}
