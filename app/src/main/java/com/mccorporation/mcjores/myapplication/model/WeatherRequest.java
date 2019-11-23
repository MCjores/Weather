package com.mccorporation.mcjores.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by MCjores on 04.10.2019.
 */

    //На каждый класс создаем класс

    //Это общий класс где мы перечисляем все классы + остальные атрибуты

public class WeatherRequest {

    @SerializedName("clouds")
    @Expose
    private Clouds clouds;

    @SerializedName("Coord")
    @Expose
    private Coord coord;

    @SerializedName("main")
    @Expose
    private Main main;

    @SerializedName("sys")
    @Expose
    private Sys sys;

    @SerializedName("weather")
    @Expose
    private Weather[] weather;    

    @SerializedName("wind")
    @Expose
    private Wind wind;

    @SerializedName("base")
    @Expose
    private String base;

    @SerializedName("visibility")
    @Expose
    private int visibility;

    @SerializedName("dt")
    @Expose
    private int dt;

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("cod")
    @Expose
    private int cod;

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public void setWeather(Weather[] weather) {
        this.weather = weather;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public Coord getCoord() {
        return coord;
    }

    public Main getMain() {
        return main;
    }

    public Sys getSys() {
        return sys;
    }

    public Weather[] getWeather() {
        return weather;
    }

    public Wind getWind() {
        return wind;
    }

    public String getBase() {
        return base;
    }

    public int getVisibility() {
        return visibility;
    }

    public int getDt() {
        return dt;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCod() {
        return cod;
    }
}
