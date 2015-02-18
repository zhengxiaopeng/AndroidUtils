package com.rocko.android.common.ui.view;

/**
 * Created by Rocko on 2014/12/26 0026.
 * View点击事件工具类
 */
public final class ViewClickUtils {
    private static long mLastClickTime = 0;

    private ViewClickUtils() {

    }

    /**
     * 判断view上的点击是不是快速多次点击
     *
     * @return
     */
    public static boolean isTimesClick() {
        long time = System.currentTimeMillis();
        long timeT = time - mLastClickTime;
        if (0 < timeT && timeT < 500) {
            return true;
        }
        mLastClickTime = time;
        return false;
    }
}
