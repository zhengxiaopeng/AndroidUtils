package com.rocko.android.common.log;

/**
 * Created by Rocko on 2015/2/17 17:16
 */
public class LogUtils {

    /**
     * Make log tag.
     *
     * @param obj
     * @return
     */
    public static String makeTag(Class<?> clazz) {
        return clazz.getSimpleName();
    }

}
