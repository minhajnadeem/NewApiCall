package com.example.minhaj.newapicall;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by minhaj on 23/08/2017.
 */

public class TaskLoader extends AsyncTaskLoader<ArrayList<WeatherModel>> {

    String url;
    ArrayList<WeatherModel> weatherModels;
    public TaskLoader(Context context,String api) {
        super(context);
        url = api;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        Log.d("tag","onStartLoading");

        if (weatherModels!=null){
            Log.d("tag","deliverResult immediately");
            deliverResult(weatherModels);
        }else {
            Log.d("tag","forceLoad");
            forceLoad();
        }
    }

    @Override
    public ArrayList<WeatherModel> loadInBackground() {
        Log.d("tag","loadInBackground");
        String response;
        response = ResponseReader.getResponseFromStream(url);
        weatherModels = JsonParser.getModelFromJson(response);
        return weatherModels;
    }

    @Override
    public void deliverResult(ArrayList<WeatherModel> data) {
        super.deliverResult(data);
        data.add(new WeatherModel(1,"a","b","c"));
        Log.d("tag","deliverResult");
    }

    @Override
    protected void onStopLoading() {
        super.onStopLoading();
        Log.d("tag","onStopLoading");
    }
}
