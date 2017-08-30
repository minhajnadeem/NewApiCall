package com.example.minhaj.newapicall;

import android.util.Log;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by minhaj on 23/08/2017.
 */

public class StateCache {

    public static ConcurrentHashMap<Class<?>,Serializable> concurrentHashMap = new ConcurrentHashMap<>();

    public static void push(Serializable object){
        synchronized (concurrentHashMap){
            concurrentHashMap.put(object.getClass(),object);
        }
    }

    public static <T> T pop(Class<T> type){

        synchronized (concurrentHashMap){
            return (T) concurrentHashMap.remove(type);
        }
    }
    public static void clear(){
        synchronized (concurrentHashMap){
            concurrentHashMap.clear();
        }
    }
}
