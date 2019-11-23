package com.mccorporation.mcjores.myapplication;

import android.app.DownloadManager;
import android.content.SharedPreferences;
import android.support.v4.content.SharedPreferencesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
//import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputEditText;


import com.mccorporation.mcjores.myapplication.interfaces.OpenWeather;
import com.mccorporation.mcjores.myapplication.model.WeatherRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private OpenWeather openWeather;
    private TextView textTemp;      //Температура в кельвинах
    private EditText editSity;  //TextInputEditText
    private EditText editApiKey;
    private SharedPreferences sharedPreferences;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRetrofit();
        initGUI();
        initPreferences();

    }
    // Здесь создадим обработку клика кнопки


    private void initPreferences() {
        sharedPreferences = getPreferences(MODE_PRIVATE);
        loadPreference();       // Загружаем настройки
    }

    //Загрузить настройки
    private void loadPreference() {
        String loadedAPIkey = sharedPreferences.getString("apiKEY", "039e3881e57788515341fa549cb7ed32");
        editApiKey.setText(loadedAPIkey);
    }

    private void initRetrofit() {
        Retrofit retrofit;
        retrofit = new Retrofit.Builder()
                //Базовая часть адреса
        .baseUrl("http://api.openweathermap.org/")
                //Конвертер, необходимый для преобразования JSON в объекты
        .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Создаем объект, при помощи которого будем выполнять запросы
            openWeather = retrofit.create(OpenWeather.class);
    }

    private void initGUI() {
        textTemp = (TextView) findViewById(R.id.textTemp);
        editApiKey = (EditText) findViewById(R.id.editApiKey);
        editSity = (EditText) findViewById(R.id.editCity);

    }

    public void btnGetClick(View view) {
        savePreference();   // Сохранем настройки
            requestRetrofit(editSity.getText().toString(),  // отправляем запрос
                editApiKey.getText().toString());       // api.openweathermap.org/data/2.5/weather?q= editSity.getText()
                                                        // &appid=editApiKey.getText()
    }

    private void requestRetrofit(String sity, String APIkey) {
        openWeather.loadWeather(sity,APIkey)
                .enqueue(new Callback<WeatherRequest>() {
                    @Override
                    public void onResponse(Call<WeatherRequest> call, Response<WeatherRequest> response) {

                        if(response.body() !=null )
                            textTemp.setText(Float.toString( (response.body() .getMain() .getTemp()) - 273 ));
//                        Log.i("request",Float.toString(response.body() .getMain() .getTemp()));
                    }

                    @Override
                    public void onFailure(Call<WeatherRequest> call, Throwable t) {
                            textTemp.setText("Error");
                        Log.i("request", t.getMessage() );
                    }
                });
    }

    // Сохранить настройки
    private void savePreference() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("apiKey", editApiKey.getText().toString());
        editor.apply();
    }
}
