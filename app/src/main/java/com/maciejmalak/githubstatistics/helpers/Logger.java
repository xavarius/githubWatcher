package com.maciejmalak.githubstatistics.helpers;

import android.util.Log;

public class Logger {
    private static final boolean DEBBUG = true;
    private static final String TAG = "githubstatistics";
    private String className;

    public Logger(String className) {
        this.className = className;
    }

    public void logd(final String msg) {
        if(DEBBUG) Log.d(TAG + " " + className, msg);
    }
}
