package com.rocko.view;

import android.app.Activity;
import android.text.InputType;
import android.text.Selection;
import android.view.WindowManager;
import android.widget.EditText;

import com.rocko.common.DebugLog;

import java.lang.reflect.Method;

/**
 * @author Mr.Zheng
 * @date 2014年10月25日 下午11:03:39
 */
public class ViewUtils {
    /**
     * 设置EditText光标的位置
     *
     * @param editText
     * @param position
     */
    public static void setEditTextCursorPosition(EditText editText, int position) {
        Selection.setSelection(editText.getText(), position);
    }

    /**
     * edittext不显示软键盘,要显示光标
     */
    public void initPhoneEditText(EditText numEditText) {
        if (android.os.Build.VERSION.SDK_INT <= 10) {
            numEditText.setInputType(InputType.TYPE_NULL);
        } else {
            ((Activity) numEditText.getContext()).getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            try {
                Class<EditText> cls = EditText.class;
                Method setSoftInputShownOnFocus;
                setSoftInputShownOnFocus = cls.getMethod("setShowSoftInputOnFocus", boolean.class);
                setSoftInputShownOnFocus.setAccessible(true);
                setSoftInputShownOnFocus.invoke(numEditText, false);
            } catch (Exception e) {
                DebugLog.e(">>>" + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
