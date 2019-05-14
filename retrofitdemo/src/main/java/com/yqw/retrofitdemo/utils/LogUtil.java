package com.yqw.retrofitdemo.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * 日志打印
 * Created by yqw on 2016/5/10.
 */
public class LogUtil {
    public static boolean isDebug = true;
    private static final String DEFAULT_TAG = "yqw";

    public static void i(String tag, String msg) {
        if (isDebug) {
            Log.i(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (isDebug) {
            Log.e(tag, msg);
        }
    }

    public static void i(String msg) {
        if (isDebug) {
            Log.i(DEFAULT_TAG, msg);
        }
    }

    public static void e(String msg) {
        if (isDebug) {
            Log.e(DEFAULT_TAG, msg);
        }
    }

    public static void showToast(Context context, String msg) {
        if (isDebug) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }
    }
}
