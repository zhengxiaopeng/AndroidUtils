package com.roc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Mr.Zheng
 * @date 2014年10月18日 上午12:04:12
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ContentView {
    /**
     * setContentView的Layout id
     *
     * @return
     */
    int id();
}
