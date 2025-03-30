package com.hamdartestapp.Utility;

import static com.hamdartestapp.Utility.Validation.ValidationEmptyTimeStamp;

import android.content.Context;

public class TimeStamp {
    private static long getCurrentTimestamp() {
        return System.currentTimeMillis();
    }

    public static boolean CheckTimeStampAppList(Context context) {
        try {
            long timeStamp = getCurrentTimestamp();
                    long saveTimeStamp = SharedPreferences.getSharedPreferencesLong(context, "TimeStampAppList");
                    if (!ValidationEmptyTimeStamp(saveTimeStamp)) return false;
                    if (timeStamp >= saveTimeStamp) return false;
                    return true;
                } catch (Exception e) {
                    return false;
                }
            }

    public static void saveTimeStampListApp(Context context) {
        long timeStamp = getCurrentTimestamp();
        long timeStampWithAddedMinutes = timeStamp + (3 * 60 * 1000);
        SharedPreferences.setSharedPreferences(context, "TimeStampAppList", timeStampWithAddedMinutes);

    }

}
