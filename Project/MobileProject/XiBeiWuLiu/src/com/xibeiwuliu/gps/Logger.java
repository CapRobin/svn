/**
 * Author : Agero
 * Copyright (c) 2012 Agero.
 * All rights reserved
 */

package com.xibeiwuliu.gps;

import android.util.Log;

/**
 * bing map log tool,it has two modes,include debug mode and product mode.in
 * debug mode,you can log your message,in product mode,you can not log your
 * message
 * 
 * @author: Bruce.Z
 * @version 1.0
 * @createDate 2012-6-19
 */
public class Logger {

    static {
        Logger.setMode(Logger.Mode.PRODUCT);
    }

    private static Mode LOGGER_MODE;

    public enum Mode {
        DEBUG(0), PRODUCT(1);

        private final int typeValue;

        Mode(int typeValue) {
            this.typeValue = typeValue;
        }

        public int getValue() {
            return typeValue;
        }
    }

    /**
     * set mode of this log tool,it has two modes,including debug mode and
     * product mode
     * 
     * @param mode
     */
    public static void setMode(Mode mode) {
        LOGGER_MODE = mode;
    }

    public static void d(String tag, String msg) {
        if (LOGGER_MODE == Mode.DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (LOGGER_MODE == Mode.DEBUG) {
            Log.e(tag, msg);
        }
    }

    public static void i(String tag, String msg) {

        if (LOGGER_MODE == Mode.DEBUG) {
            Log.i(tag, msg);
        }
    }

    public static void v(String tag, String msg) {
        if (LOGGER_MODE == Mode.DEBUG) {
            Log.v(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (LOGGER_MODE == Mode.DEBUG) {
            Log.w(tag, msg);
        }
    }
}
