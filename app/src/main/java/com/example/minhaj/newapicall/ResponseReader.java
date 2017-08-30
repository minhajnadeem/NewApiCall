package com.example.minhaj.newapicall;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by minhaj on 23/08/2017.
 */

public class ResponseReader {

    public  static String getResponseFromStream(String strUrl){

        URL url;
        HttpURLConnection httpURLConnection;
        InputStream is;
        String response =null;
        try {
            url = new URL(strUrl);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            is = httpURLConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null){
                builder.append(line);
            }
            response = builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d("tag","response :"+response);
        return response;
    }
}
