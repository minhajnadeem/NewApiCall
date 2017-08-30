package com.example.minhaj.newapicall;

import android.content.Intent;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ApiAsync.MyInterface ,View.OnClickListener{

    private final String TAG = "tag";
    private final String API = "http://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b1b15e88fa797225412429c1c50c122a1";
    private WeatherModel weatherModel;
    private ArrayList<WeatherModel> list = new ArrayList<>();

    private boolean flag = true;

    private TextView textView;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.text);
        progressBar = (ProgressBar) findViewById(R.id.bar);
        textView.setOnClickListener(this);
        textView.setText("hello");

        getSupportLoaderManager().initLoader(0, null, new LoaderManager.LoaderCallbacks<ArrayList<WeatherModel>>() {
            @Override
            public Loader<ArrayList<WeatherModel>> onCreateLoader(int id, Bundle args) {
                Log.d("tag","onCreateLoader");
                progressBar.setVisibility(View.VISIBLE);
                return new TaskLoader(getApplicationContext(),API);
            }

            @Override
            public void onLoadFinished(Loader<ArrayList<WeatherModel>> loader, ArrayList<WeatherModel> data) {
                Log.d("tag","onLoadFinished");
                progressBar.setVisibility(View.GONE);
                list = data;
                updateUi();
            }

            @Override
            public void onLoaderReset(Loader<ArrayList<WeatherModel>> loader) {
                Log.d("tag","onLoaderReset");
            }
        });


    }



    @Override
    public void doTask(ArrayList<WeatherModel> models) {
        list = models;
        progressBar.setVisibility(View.GONE);
        updateUi();
    }

    private void updateUi(){
        Log.d("tag","list size ="+list.size());
        Toast.makeText(this, "update", Toast.LENGTH_SHORT).show();
        weatherModel = list.get(0);
        String weather = weatherModel.getDescription()+"\n"+weatherModel.getMain()+"\n"+weatherModel.getId()+"\n"+weatherModel.getIcon();
        textView.setText(weather);
    }

    @Override
    public void onClick(View view) {
        startNewActivity();
    }

    private void startNewActivity() {
        startActivity(new Intent(this,SecondActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //getSupportLoaderManager().destroyLoader(0);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //getSupportLoaderManager().destroyLoader(0);
    }
}