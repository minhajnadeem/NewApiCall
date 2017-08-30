package com.example.minhaj.newapicall;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by minhaj on 23/08/2017.
 */

public class JsonParser {

    public JsonParser(){
    }

    public static ArrayList<WeatherModel> getModelFromJson(String strJson){
        ArrayList<WeatherModel> arrayList = new ArrayList<>();
        JSONObject jsonObject = getEntryArray(strJson);
        JSONArray jsonArray = getWeatherArray(jsonObject);
        Log.d("tag","jason parser array size = "+arrayList.size());
        for (int i=0; i<=100000; i++){
            JSONObject weather = getJsonObject(jsonArray,0);
            try {
                arrayList.add(parseJsonObject(weather));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    private static JSONArray getWeatherArray(JSONObject jsonObject) {
        try {
            return jsonObject.getJSONArray("weather");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static WeatherModel parseJsonObject(JSONObject jsonObject) throws JSONException {
        int id = 0;
        String main = null,description = null,icon = null;
        Iterator<String> iterator = jsonObject.keys();
        while (iterator.hasNext()){
            String name = iterator.next();
            //Log.d("tag",name);
            if (name.equals("id")){
                id = jsonObject.getInt(name);
            }else if (name.equals("main")){
                main = jsonObject.getString(name);
            }else if (name.equals("description")){
                description = jsonObject.getString(name);
            }else if (name.equals("icon")){
                icon = jsonObject.getString(name);
            }

        }
        return new WeatherModel(id,main,description,icon);
    }

    private static JSONObject getEntryArray(String str){
        try {
            return new JSONObject(str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static JSONObject getJsonObject(JSONArray jsonArray,int index){
        try {
            return jsonArray.getJSONObject(index);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
