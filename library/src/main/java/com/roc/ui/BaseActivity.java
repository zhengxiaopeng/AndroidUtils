package com.roc.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import com.roc.annotation.ViewInstaller;
import com.roc.app.AppManager;
import com.roc.common.ToastHelper;
import com.roc.content.SharedPreferencesManager;
import com.roc.utils.PhoneUtils;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 应用程序Activity的基类
 *
 * @author Mr.Zheng
 * @date 2014年8月17日20:50:32
 * @bug 在继承FragmengActivity时有问题，待解决。
 */
public abstract class BaseActivity extends Activity {
    private AppManager appManager = null;
    private boolean isExit = false; // 双击返回键退出标记
    private boolean isLeftFlingFinish = true; //默认左滑可以finish掉Activity
    private GestureDetectorCompat mGestureDetector;//手势检测

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.v(this.getClass().getSimpleName(), ">>>onCreate");
        if (appManager == null)
            appManager = AppManager.getAppManager();
        appManager.pushActivity(this);
        ViewInstaller.processAnnotation(this);
        mGestureDetector = new GestureDetectorCompat(getApplicationContext(), new MyGestureListener());
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v(this.getClass().getSimpleName(), ">>>onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(this.getClass().getSimpleName(), ">>>onResume");
    }

    @Override
    protected void onStop() {
        super.onResume();
        Log.v(this.getClass().getSimpleName(), ">>>onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(this.getClass().getSimpleName(), ">>>onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.v(this.getClass().getSimpleName(), ">>>onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(this.getClass().getSimpleName(), ">>>onDestroy");
        //这里需注意屏幕旋转时，这样写会导致finish掉当前activity!
        appManager.finishActivity(this);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (getLeftFlingFinishEnable()) {
            mGestureDetector.onTouchEvent(event);//遇到左滑动作就会finish
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
            /* 返回键处理，当栈中剩1时即主界面了，双击退出 */
                if (appManager.getActivityStack().size() > 1)
                    break;
                if (!isExit) {
                    isExit = true;
                    ToastHelper.showCustomInfoShortToast(getApplicationContext(), "再按一次退出程序");
                    Timer time = new Timer();
                    time.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            isExit = false;
                        }
                    }, 2000);
                } else
                    appManager.AppExit();
                return true;
            default:
                break;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 设置Activity界面上左滑滑动是否finish掉Activity
     *
     * @param isLeftFlingFinish
     */
    public void setLeftFlingFinish(boolean isLeftFlingFinish) {
        this.isLeftFlingFinish = isLeftFlingFinish;
    }

    public boolean getLeftFlingFinishEnable() {
        return isLeftFlingFinish;
    }

    public Activity getActivity() {
        return this;
    }

    public Context getContext() {
        return this;
    }

    /**
     * 不想每次findViewById都需要强转......
     *
     * @param id
     * @return
     */
    public <T extends View> T findView(int id) {
        return (T) findViewById(id);
    }

    /**
     * 得到sp的管理器,模式为Context.MODE_PRIVATE
     *
     * @param spFileName sp文件名
     * @return 返回一个新的sp管理器实例, 此时sp管理的是spFileName对应的SharedPreferences文件
     */
    public SharedPreferencesManager getSharedPreferencesManager(String spFileName) {
        return getSharedPreferencesManager(spFileName, Context.MODE_PRIVATE);
    }

    /**
     * 得到sp的管理器
     *
     * @param spFileName sp文件名
     * @param mode       读写模式
     * @return 返回一个新的sp管理器实例，此时sp管理的是spFileName对应的SharedPreferences文件
     */
    public SharedPreferencesManager getSharedPreferencesManager(String spFileName, int mode) {
        return new SharedPreferencesManager(getApplicationContext(), spFileName, mode);
    }

    /**
     * 手势监听器
     */
    private class MyGestureListener implements GestureDetector.OnGestureListener {
        private float xLeftEdgeMinDistance;
        private float xMinDistance;
        private float yMinDistance;

        public MyGestureListener() {
            DisplayMetrics dm = PhoneUtils.getDisplayMetrics(getActivity());
            xLeftEdgeMinDistance = dm.widthPixels / 15.0f;
            xMinDistance = dm.widthPixels / 20.f;
            yMinDistance = dm.heightPixels / 30.f;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float x = e2.getX() - e1.getX();
            float y = e2.getY() - e1.getY();
            if (x > xMinDistance && y < yMinDistance && y > -yMinDistance) {
                finish();
            }
            return false;
        }
    }

}