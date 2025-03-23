package de.androidcrypto.android_hce_emulate_a_creditcard;

import android.util.Log;

public class Logger {

    final static boolean OPEN_LOG = true;
    final static String TAG = "ONLY_MSG_NFC";
    final static String TAG_MSG = "TAG_MSG_NFC";

    public static void d(String tag, String msg) {
        if (OPEN_LOG) {
            Log.d(TAG_MSG, tag + "-*2*-" + msg);
        }
    }

    public static void d(String msg) {
        if (OPEN_LOG) {
            Log.d(TAG, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (OPEN_LOG) {
            Log.i(TAG_MSG, tag + "-*2*-" + msg);
        }
    }

    public static void i(String msg) {
        if (OPEN_LOG) {
            Log.i(TAG, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (OPEN_LOG) {
            Log.e(TAG_MSG, tag + "-*2*-" + msg);
        }
    }

    public static void e(String msg) {
        if (OPEN_LOG) {
            Log.e(TAG, msg);
        }
    }
}
