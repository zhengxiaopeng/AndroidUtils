package com.rocko.android.common.util;

import android.graphics.Color;

/**
 * Description: ColorUtils
 * Author: Rocko
 * Update: Rocko(2015-01-13 14:00)
 */
public class ColorUtils {

    /**
     * RGB颜色加深
     *
     * @param RGBValue RGB颜色的值
     * @param value    加深的程度值0.0 ~ 1，建议0.1
     * @return 加深后的RGB值
     */
    public static int colorBurn(int RGBValue, int value) {
        int alpha = RGBValue >> 24; //取出RGB值中alpha的值,这里没有用到，透明值不需要改
        int red = RGBValue >> 16 & 0xFF;
        int green = RGBValue >> 8 & 0xFF;
        int blue = RGBValue & 0xFF;
        int v = 1 - value;
        red = (int) Math.floor(red * v);
        green = (int) Math.floor(green * v);
        blue = (int) Math.floor(blue * v);
        return Color.rgb(red, green, blue);
    }
}
