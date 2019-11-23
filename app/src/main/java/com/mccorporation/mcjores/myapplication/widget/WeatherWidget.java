package com.mccorporation.mcjores.myapplication.widget;

import android.app.Application;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import com.mccorporation.mcjores.myapplication.R;
import com.mccorporation.mcjores.myapplication.interfaces.OpenWeather;
import com.mccorporation.mcjores.myapplication.model.Weather;
import com.mccorporation.mcjores.myapplication.model.WeatherRequest;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Query;

/**
 * Created by MCjores on 04.10.2019.
 */

public class WeatherWidget extends AppWidgetProvider {
    private Timer timer = null;
    private int[] widgetsID = null;

    private OpenWeather openWeather;
    private static final String ACTION_CHANGE = "ACTION_CHANGE";
    private String keyAPI;
    private TextView gradus;
    private RemoteViews remoteViews;
    private PendingIntent pIntent;
    private float LocalTemp;
    public final static String STOP_WIDGET_ANIMATION = "ru.ztrap.STOP_WIDGET_ANIMATION";
    public final static String FORCE_WIDGET_UPDATE = "ru.ztrap.FORCE_WIDGET_UPDATE";

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);

    }

    @Override
    public void onUpdate(final Context context, final AppWidgetManager appWidgetManager, final int[] appWidgetIds) {
        initRetrofit();

        widgetsID = appWidgetIds;
//        for(int i=0; i<appWidgetIds.length;i++)
//            updateWidget(context, appWidgetManager, i);
        if (timer == null) {
            timer = new Timer();

            final Calendar cal = Calendar.getInstance();
            cal.add(Calendar.SECOND, 5);
            cal.set(Calendar.MILLISECOND, 0);

            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    updateWidget(context, appWidgetManager);
                }
            }, cal.getTime(), 1000);
        }

        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }


    private void updateWidget(Context context, AppWidgetManager appWidgetManager) {

        for (int aWidgetsID : widgetsID) {


            remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);

            requestRetrofit("Moscow, ru", remoteViews);
            remoteViews.setTextViewText(R.id.widgetText, Float.toString(LocalTemp));      // сюда добавить темепературу

            appWidgetManager.updateAppWidget(aWidgetsID, remoteViews);
            //        Intent updateIntent = new Intent(context, WeatherWidget.class);
//        updateIntent.setAction(FORCE_WIDGET_UPDATE);
//        updateIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetIds);
//        int[] allWidgetIds = {appWidgetIds}; // id виджетов, которые необходимо будет обновить
//        updateIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, allWidgetIds);
//        PendingIntent pIntent = PendingIntent.getBroadcast(context, appw, updateIntent, 0); // создаём PendingIntent который будет отправляться при нажатии

//        remoteViews.setOnClickPendingIntent(R.id.btnUpdate,pIntent);
//        appWidgetManager.updateAppWidget(appWidgetIds,remoteViews);
        }
   /*     //Кнопка обновить  DON'T WORK
        Intent updateIntent = new Intent(context, WeatherWidget.class);
        updateIntent.setAction(ACTION_CHANGE);
        updateIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                        new int[] {appWidgetIds} );
        pIntent = PendingIntent.getActivity(context,appWidgetIds,updateIntent,0);
//        remoteViews.setTextViewText(R.id.widgetText, "12");
        remoteViews.setOnClickPendingIntent(appWidgetIds,pIntent);

//            remoteViews.setTextViewText(appWidgetIds,"+1");
            appWidgetManager.updateAppWidget(appWidgetIds,remoteViews);

        */

    }

    private void requestRetrofit(String sity, final RemoteViews remote) {
//        openWeather.loadWeather(sity,keyAPI).enqueue(Callback< WeatherRequest>);
        openWeather.loadWeather(sity, keyAPI).enqueue(new Callback<WeatherRequest>() {
            @Override
            public void onResponse(Call<WeatherRequest> call, Response<WeatherRequest> response) {

//                if (response.body() != null)
//                    remote.setTextViewText(R.id.widgetText, Float.toString(response.body().getMain().getTemp() - 273)); //т.к. температура в кельвинах\


                    Log.i("onResponse", "pre ok");
                    Log.i("onResponse", "" + response.code() + " " + response.message());
                    if (response.body() != null) {
                        Log.i("onResponse", "ok");
                        LocalTemp = response.body().getMain().getTemp() - 273;




                }
            }

            @Override
            public void onFailure(Call<WeatherRequest> call, Throwable t) {
                remote.setTextViewText(R.id.widgetText, "Error");
                Log.i("onResponse", "no");
            }
        });
    }


    //вызывается каждый раз, при отлове broadcast'a с установленным для виджета фильтром

    //сдесь мы выполняем все что с нашим флагом ACTION_CHANGE
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        if (intent.getAction().equals(ACTION_CHANGE)) {
            Log.i("onReceive", "ACTION_CHANGE ");
            int getID = AppWidgetManager.INVALID_APPWIDGET_ID;
            Bundle extras = intent.getExtras();
            if (extras != null) {
                getID = extras.getInt(
                        AppWidgetManager.EXTRA_APPWIDGET_ID,
                        AppWidgetManager.INVALID_APPWIDGET_ID
                );
            }
            if (getID != AppWidgetManager.INVALID_APPWIDGET_ID) {
                //То что мы делаеи при нажатии
                Log.i("onReceive", "AppWidgetManager.INVALID_APPWIDGET_ID ");
                remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);
                remoteViews.setTextViewText(R.id.widgetText, "+1");
                updateWidget(context, AppWidgetManager.getInstance(context));
//                updateWidget(context, AppWidgetManager.getInstance(context), getID);
            }
        }
    }

    private void initRetrofit() {
        Retrofit retroft = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        openWeather = retroft.create(OpenWeather.class);

    }
}
