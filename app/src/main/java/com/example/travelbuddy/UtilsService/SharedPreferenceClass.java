package com.example.travelbuddy.UtilsService;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceClass {
    private static final String USER_PREF="user";
    private SharedPreferences appShared;
    private static SharedPreferences.Editor prefsEditor;

    public SharedPreferenceClass(Context context){
        appShared = context.getSharedPreferences(USER_PREF, Activity.MODE_PRIVATE);
        this.prefsEditor = appShared.edit();
    }
    //key:value
    //"id":1234
    //int
    public int getValue_int(String key){
        return appShared.getInt(key,0);
    }

    public void setValue_int(String key,int value){
        prefsEditor.putInt(key,value).commit();
    }

    //string
    public String getValue_String(String key){
        return appShared.getString(key,"");
    }

    public void setValue_String(String key, String value){
        prefsEditor.putString(key,value).commit();
    }

    //boolean
    public boolean getValue_boolean(String key){
        return appShared.getBoolean(key,false);
    }

    public void setValue_boolean(String key,boolean value){
        prefsEditor.putBoolean(key,value).commit();
    }
    public void clear() {
        prefsEditor.clear().commit();
    }
}
