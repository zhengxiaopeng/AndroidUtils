package com.roc.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;

/**
 * 通用工具
 *
 * @author Mr.Zheng
 * @date 2014年8月14日21:36:02
 */
public class CommonUtils {
    /**
     * InputStream转换成字符串
     *
     * @param is
     * @return
     */
    public static String inputStream2Str(InputStream is) {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String content = "";
        String line;
        try {
            line = br.readLine();
            while (line != null) {
                content += line + "\n";
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }

    /**
     * 字符串转换为InputStream
     *
     * @param str
     * @return
     */
    public static InputStream str2InputStream(String str) {
        return new ByteArrayInputStream(str.getBytes());
    }

    /**
     * 通过语言String简写返回Locale<br>
     * like en_us -->en_US
     *
     * @param localeStr
     * @return
     */
    public static Locale createLocaleFromString(String localeStr) {
        if (null == localeStr)
            return Locale.getDefault();
        String[] brokenDownLocale = localeStr.split("_", 3);
        if (1 == brokenDownLocale.length) {
            return new Locale(brokenDownLocale[0]);
        } else if (2 == brokenDownLocale.length) {
            return new Locale(brokenDownLocale[0], brokenDownLocale[1]);
        } else {
            return new Locale(brokenDownLocale[0], brokenDownLocale[1], brokenDownLocale[2]);
        }
    }

}
