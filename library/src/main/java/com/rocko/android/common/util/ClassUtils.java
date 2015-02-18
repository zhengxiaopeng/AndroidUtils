package com.rocko.android.common.util;

/**
 * Description: ClassHelper
 * Author: Rocko
 * Update: Rocko(2015-01-15 14:09)
 */
public class ClassUtils {
    /**
     * 是否存在某个类
     *
     * @param className Class's full name
     * @return
     */
    public static boolean hasClass(String className) {
        try {
            Class.forName(className);
            return true;
        } catch (ClassNotFoundException ignored) {
        }
        return false;
    }

}