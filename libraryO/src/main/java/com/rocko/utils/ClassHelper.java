package com.rocko.utils;

/**
 * Description: ClassHelper
 * Author: Rocko
 * Update: Rocko(2015-01-15 14:09)
 */
public class ClassHelper {
    /**
     * 是否存在某个类
     *
     * @param className The full name of the class
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