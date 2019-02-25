package com.kotlin.anonyper.testapplication;

import android.util.Log;

/**
 * TestApplication
 * Created by anonyper on 2018/12/14.
 */

public class LogUtil {
    public static boolean isLogEnable = BuildConfig.DEBUG;
    public static String TAG = "LOG";

    public static void i(String message) {
        if (isLogEnable)
            i(TAG, format(message));
    }

    public static void i(String tag, String message) {
        if (isLogEnable)
            Log.i(tag, format(message));
    }

    public static void w(String message) {
        if (isLogEnable)
            w(TAG, message);
    }

    public static void w(String tag, String message) {
        if (isLogEnable)
            Log.w(tag, format(message));
    }

    public static void e(String message) {
        if (isLogEnable)
            e(TAG, message);
    }

    public static void e(String tag, String message) {
        if (isLogEnable)
            Log.e(tag, format(message));
    }

    public static void d(String message) {
        if (isLogEnable)
            d(TAG, format(message));
    }

    public static void d(String tag, String message) {
        if (isLogEnable)
            Log.d(tag, format(message));
    }

    public static void v(String tag, String message) {
        if (isLogEnable)
            Log.v(tag, format(message));
    }

    /**
     * 将一个json字符串按照可观的模式格式化，方便打印观看
     *
     * @param jsonStr
     * @return
     */
    public static String format(String jsonStr) {
        int level = 0;
        StringBuffer jsonForMatStr = new StringBuffer();
        for (int i = 0; i < jsonStr.length(); i++) {
            char c = jsonStr.charAt(i);
            if (level > 0 && '\n' == jsonForMatStr.charAt(jsonForMatStr.length() - 1)) {
                jsonForMatStr.append(getLevelStr(level));
            }
            switch (c) {
                case '{':
                case '[':
                    jsonForMatStr.append(c + "\n");
                    level++;
                    break;
                case ',':
                    jsonForMatStr.append(c + "\n");
                    break;
                case '}':
                case ']':
                    jsonForMatStr.append("\n");
                    level--;
                    jsonForMatStr.append(getLevelStr(level));
                    jsonForMatStr.append(c);
                    break;
                default:
                    jsonForMatStr.append(c);
                    break;
            }
        }

        return jsonForMatStr.toString();

    }

    /**
     * 进行tap转换
     *
     * @param level
     * @return
     */
    private static String getLevelStr(int level) {
        StringBuffer levelStr = new StringBuffer();
        for (int levelI = 0; levelI < level; levelI++) {
            levelStr.append("\t");
        }
        return levelStr.toString();
    }

}
