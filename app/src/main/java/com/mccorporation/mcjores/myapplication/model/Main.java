package com.mccorporation.mcjores.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by MCjores on 04.10.2019.
 */

public class Main {
    //"Main":{"temp":280.32,"pressure":1012,"humidity":81,"temp_min":279.15,"temp_max":281.15}

    @SerializedName("temp")
    @Expose
    private float temp;

    @SerializedName("pressure")
    @Expose
    private float prssure;

    @SerializedName("humidity")
    @Expose
    private int humidity;

    @SerializedName("temp_min")
    @Expose
    private float temp_min;

    @SerializedName("temp_max")
    @Expose
    private float temp_max;

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public void setPrssure(int prssure) {
        this.prssure = prssure;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public void setTemp_min(float temp_min) {
        this.temp_min = temp_min;
    }

    public void setTemp_max(float temp_max) {
        this.temp_max = temp_max;
    }

    public float getTemp() {
        return temp;
    }

    public float getPrssure() {
        return prssure;
    }

    public int getHumidity() {
        return humidity;
    }

    public float getTemp_min() {
        return temp_min;
    }

    public float getTemp_max() {
        return temp_max;
    }
}
