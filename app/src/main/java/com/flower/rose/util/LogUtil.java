package com.flower.rose.util;

import android.util.Log;

/**
 * @author: ningqiang
 * @time: 16/11/12
 * @description:
 */

public class LogUtil {
    private static String TAG = "LogUtil";

    public static void d(String tag, String msg) {
        Log.d(tag, msg);
    }

    public static void d(String msg) {
        Log.d(TAG, msg);
    }
}
