package com.rocko.android.common.app;

import android.app.Application;

/**
 * Created by Rocko on 2015/2/17 16:51 16:56
 */
public class ACApplication extends Application {
    private static ACApplication instance;

    public ACApplication() {
        instance = this;
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Application getContext() {
        return instance;
    }
}
