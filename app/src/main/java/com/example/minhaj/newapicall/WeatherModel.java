package com.example.minhaj.newapicall;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by minhaj on 23/08/2017.
 */

public class WeatherModel implements Parcelable {

    private int id;
    private String main,description,icon;

    public WeatherModel(int id,String main,String description,String icon){
        setId(id);
        setMain(main);
        setDescription(description);
        setIcon(icon);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
