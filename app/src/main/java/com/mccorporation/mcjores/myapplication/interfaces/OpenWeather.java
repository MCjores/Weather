package com.mccorporation.mcjores.myapplication.interfaces;

import com.google.gson.annotations.Expose;
import com.mccorporation.mcjores.myapplication.model.WeatherRequest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;



    //интерфейс доступа к web-сервису

public interface OpenWeather {
    //@GET определяет тип http-запроса, в качестве параметра указывается относительный путь к web-сервису.
    //Сам метод имеет два параметра, помеченных аннотацией @Query – она создает пару «имя параметра»=«значение»,
    // где имя параметра указывается параметром @Query, а в качестве значения выступает переменная.

    //Это тип запроса GET для указанного адреса при построении запроса. Указывается относительный адрес;


    //пример вызова API api.openweathermap.org/data/2.5/weather?q=London&appid=b6907d289e10d714a6e88b30761fae22

    @GET("data/2.5/weather")    // либо http://api.openweathermap.org/ data/2.5/weather
    Call<WeatherRequest> loadWeather(@Query("q") String sityCountry,    //?q=London
                                     @Query("appid") String keyAPI);    //&appid=b6907d289e10d714a6e88b30761fae22
}
