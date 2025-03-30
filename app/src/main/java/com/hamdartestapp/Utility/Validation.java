package com.hamdartestapp.Utility;


import android.content.Context;
import android.net.ConnectivityManager;

public class Validation {
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    public static boolean ValidationEmpty(String str){
        try {
            if (str==null) return false;
            if (str.equals("")) return false;
            if (str.isEmpty()) return false;

        }catch (Exception e){
            return false;
        }
        return true;
    }
    public static boolean ValidationEmptyTimeStamp(long timeStamp){
        try {
            if (timeStamp<=0) return false;


        }catch (Exception e){
            return false;
        }
        return true;
    }

}
