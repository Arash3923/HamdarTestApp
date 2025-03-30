package com.hamdartestapp.Utility;

import android.content.Context;


public class SharedPreferences {



    public static void setSharedPreferences(Context ctx, String paramName, Long paramValue) {
        android.content.SharedPreferences setsp = ctx.getSharedPreferences(ctx.getPackageName(), Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = setsp.edit();
        editor.putLong(paramName, paramValue).apply();
    }


    public static Long getSharedPreferencesLong(Context ctx, String paramName) {
        try {
            android.content.SharedPreferences setsp = ctx.getSharedPreferences(ctx.getPackageName(), Context.MODE_PRIVATE);
            return setsp.getLong(paramName,0);
        } catch (Exception ex) {
            return -1l;
        }
    }






}
