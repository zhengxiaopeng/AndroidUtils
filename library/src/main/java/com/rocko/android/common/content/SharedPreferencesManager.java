package com.rocko.android.common.content;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author Mr.Zheng
 * @date 2014年9月9日 下午8:02:58
 */
public class SharedPreferencesManager {
    /**
     * 当前这个SharedPreferencesManager实例所持有管理的SharedPreferences,
     * 由这个sp来最终操作构造方法里spFileName参数对应的SharedPreferences文件
     * ,也就是说由这个sp来操作各种SharedPreferences数据的增加和删除。
     */
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private SharedPreferences.OnSharedPreferenceChangeListener l;

    /**
     * Context.MODE_PRIVATE模式
     *
     * @param context    ApplicationContext
     * @param spFileName sp的文件名
     */
    public SharedPreferencesManager(Context context, String spFileName) {
        this(context, spFileName, Context.MODE_PRIVATE);
    }

    /**
     * @param context    ApplicationContext
     * @param spFileName sp的文件名
     * @param mode       模式
     */
    public SharedPreferencesManager(Context context, String spFileName, int mode) {
        sp = context.getSharedPreferences(spFileName, mode);
        editor = sp.edit();
    }

    /**
     * Registers a callback to be invoked when a change happens to a preference.
     * <p/>
     * <p class="caution"><strong>Caution:</strong> The preference manager does
     * not currently store a strong reference to the listener. You must store a
     * strong reference to the listener, or it will be susceptible to garbage
     * collection. We recommend you keep a reference to the listener in the
     * instance data of an object that will exist as long as you need the
     * listener.</p>
     *
     * @param listener The callback that will run.
     * @see #unregisterOnSharedPreferenceChangeListener
     */
    public void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        this.l = listener;
        sp.registerOnSharedPreferenceChangeListener(listener);
    }

    /**
     * Unregisters a previous callback.
     *
     * @see #registerOnSharedPreferenceChangeListener
     */
    public void unregisterOnSharedPreferenceChangeListener() {
        sp.unregisterOnSharedPreferenceChangeListener(l);
    }

    /**
     * 获取Boolean值
     *
     * @param valuesKey
     * @param defaultValue
     * @return
     */
    public boolean getBooleanValue(String valuesKey, boolean defaultValue) {
        return sp.getBoolean(valuesKey, defaultValue);
    }

    /**
     * put进Boolean value
     *
     * @param valuesKey
     * @param value
     */
    public SharedPreferencesManager putBooleanValue(String valueKey, boolean value) {
        editor.putBoolean(valueKey, value);
        return this;
    }

    /**
     * 获取String值
     *
     * @param valueKey
     * @param defaultValue
     * @return
     */
    public String getStringValue(String valueKey, String defaultValue) {
        return sp.getString(valueKey, defaultValue);
    }

    /**
     * put 进String value
     *
     * @param valueKey
     * @param value
     */
    public SharedPreferencesManager putStringValue(String valueKey, String value) {
        editor.putString(valueKey, value);
        return this;
    }

    /**
     * 获取Integer值
     *
     * @param valueKey
     * @param defaultValue
     * @return
     */
    public int getIntValue(String valueKey, int defaultValue) {
        return sp.getInt(valueKey, defaultValue);
    }

    /**
     * put 进Integer value
     *
     * @param valueKey
     * @param value
     */
    public SharedPreferencesManager putIntValue(String valueKey, int value) {
        editor.putInt(valueKey, value);
        return this;
    }

    /**
     * 获取Float值
     *
     * @param valueKey
     * @param defaultValue
     * @return
     */
    public float getFloatValue(String valueKey, float defaultValue) {
        return sp.getFloat(valueKey, defaultValue);
    }

    /**
     * put 进Float value
     *
     * @param valueKey
     * @param value
     */
    public SharedPreferencesManager putFloatValue(String valueKey, Float value) {
        editor.putFloat(valueKey, value);
        return this;
    }

    /**
     * 获取Long值
     *
     * @param valueKey
     * @param defaultValue
     * @return
     */
    public long getLongValue(String valueKey, long defaultValue) {
        return sp.getLong(valueKey, defaultValue);
    }

    /**
     * put 进Long value
     *
     * @param valueKey
     * @param value
     */
    public SharedPreferencesManager putLongValue(String valueKey, long value) {
        sp.edit().putLong(valueKey, value).commit();
        return this;
    }

    /**
     * 在存储进当前所有需要存储的内容后执行commit操作。注意不要每put进一个值就commit一次，这是相对耗时的操作。
     */
    public void commit() {
        editor.commit();
    }

    public SharedPreferences getSharedPreferences() {
        return sp;
    }

    public SharedPreferences.Editor getEditor() {
        return editor;

    }
}
