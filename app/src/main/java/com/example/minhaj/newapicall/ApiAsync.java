package com.example.minhaj.newapicall;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Looper;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Created by minhaj on 23/08/2017.
 */

public class ApiAsync extends AsyncTask<String,Void,ArrayList<WeatherModel>> {

    private Context context;
    private String response;
    ArrayList<WeatherModel> weatherModels;
    private MyInterface myInterface;

    public ApiAsync(Context context){
        this.context = context;
        try {
            myInterface = (MyInterface) context;
        }catch (ClassCastException e){
            throw new ClassCastException("must implement inferFace");
        }
    }


    @Override
    protected ArrayList<WeatherModel> doInBackground(String... strings) {

        String strUrl = strings[0];
        response = ResponseReader.getResponseFromStream(strUrl);
        weatherModels = JsonParser.getModelFromJson(response);
        return weatherModels;
    }

    @Override
    protected void onPostExecute(final ArrayList<WeatherModel> weatherModels) {
        super.onPostExecute(weatherModels);
        myInterface.doTask(weatherModels);
    }

    public interface MyInterface{
         void doTask(ArrayList<WeatherModel> models);
    }
}