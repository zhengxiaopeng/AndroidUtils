package com.roc.animation;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.Animator.AnimatorListener;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ArgbEvaluator;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.ValueAnimator;
import com.roc.androidutils.R;
import com.roc.annotation.ContentView;
import com.roc.ui.BaseActivity;

/**
 * nineoldandroids兼容动画库
 *
 * @author Mr.Zheng
 */
public class AnimationTestActivity extends BaseActivity implements OnClickListener {
    private Button btn1, btn2, btn3, btn4, btn5, btnMenu;
    private LinearLayout linearLayout;

    private boolean mIsMenuOpen = false;

    @Override
    @ContentView(id = R.layout.activity_animation)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
    }

    private void initViews() {
        btn1 = (Button) findViewById(R.id.btn_animation_item1);
        btn2 = (Button) findViewById(R.id.btn_animation_item2);
        btn3 = (Button) findViewById(R.id.btn_animation_item3);
        btn4 = (Button) findViewById(R.id.btn_animation_item4);
        btn5 = (Button) findViewById(R.id.btn_animation_item5);
        btnMenu = (Button) findViewById(R.id.btn_animation_menu);
        btnMenu.setOnClickListener(this);
        MenuListener menuListener = new MenuListener();
        btn1.setOnClickListener(menuListener);
        btn2.setOnClickListener(menuListener);
        btn3.setOnClickListener(menuListener);
        btn4.setOnClickListener(menuListener);
        btn5.setOnClickListener(menuListener);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_animation_test1:
                // 改变translationY属性
                ObjectAnimator.ofFloat(btn1, "translationX", btn1.getHeight()).start();
                break;
            case R.id.btn_animation_test2:
                /**
                 * 改变一个对象的背景色属性，典型的情形是改变View的背景色，
                 * 下面的动画可以让背景色在3秒内实现从0xFFFF8080到0xFF8080FF的渐变，并且动画会无限循环而且会有反转的效果
                 */
                ValueAnimator colorAnim = ObjectAnimator.ofInt(linearLayout, "backgroundColor", 0xFFFF8080,
                        0xFF8080FF);
                colorAnim.setDuration(3000);
                colorAnim.setEvaluator(new ArgbEvaluator());
                colorAnim.setRepeatCount(ValueAnimator.INFINITE);
                colorAnim.setRepeatMode(ValueAnimator.REVERSE);
                colorAnim.start();
                break;
            case R.id.btn_animation_test3:
                /**
                 * 动画集合，5秒内对View的旋转、平移、缩放和透明度都进行了改变
                 */
                AnimatorSet set = new AnimatorSet();
                set.playTogether(ObjectAnimator.ofFloat(btn3, "rotationX", 0, 360),
                        ObjectAnimator.ofFloat(btn3, "rotationY", 0, 180),
                        ObjectAnimator.ofFloat(btn3, "rotation", 0, -90),
                        ObjectAnimator.ofFloat(btn3, "translationX", 0, 90),
                        ObjectAnimator.ofFloat(btn3, "translationY", 0, 90),
                        ObjectAnimator.ofFloat(btn3, "scaleX", 1, 1.5f),
                        ObjectAnimator.ofFloat(btn3, "scaleY", 1, 0.5f),
                        ObjectAnimator.ofFloat(btn3, "alpha", 1, 0.25f, 1));
                set.setDuration(5 * 1000).start();
                break;
            case R.id.btn_animation_menu:
            /* menu里的布局要FrameLayout且都填满 */
                if (!mIsMenuOpen) {
                    mIsMenuOpen = true;
                    doAnimateOpen(btn1, 0, 5, 300);
                    doAnimateOpen(btn2, 1, 5, 300);
                    doAnimateOpen(btn3, 2, 5, 300);
                    doAnimateOpen(btn4, 3, 5, 300);
                    doAnimateOpen(btn5, 4, 5, 300);
                } else {
                    mIsMenuOpen = false;
                    doAnimateClose(btn1, 0, 5, 300);
                    doAnimateClose(btn2, 1, 5, 300);
                    doAnimateClose(btn3, 2, 5, 300);
                    doAnimateClose(btn4, 3, 5, 300);
                    doAnimateClose(btn5, 4, 5, 300);
                }
                break;
            default:
                break;
        }
    }

    private class MenuListener implements OnClickListener {
        public MenuListener() {
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_animation_item1:

                case R.id.btn_animation_item2:

                case R.id.btn_animation_item3:

                case R.id.btn_animation_item4:

                case R.id.btn_animation_item5:
                default:
                    Toast.makeText(AnimationTestActivity.this, "你点击了" + v.getId(), Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    /**
     * 打开菜单的动画
     *
     * @param view   执行动画的view
     * @param index  view在动画序列中的顺序
     * @param total  动画序列的个数
     * @param radius 动画半径
     */
    private void doAnimateOpen(View view, int index, int total, int radius) {
        if (view.getVisibility() != View.VISIBLE)
            view.setVisibility(View.VISIBLE);
        double degree = Math.PI * index / ((total - 1) * 2);
        int translationX = (int) (radius * Math.cos(degree));
        int translationY = (int) (radius * Math.sin(degree));
        // Log.d(TAG,
        // String.format("degree=%f, translationX=%d, translationY=%d",
        // degree, translationX, translationY));
        AnimatorSet set = new AnimatorSet();
        // 包含平移、缩放和透明度动画
        set.playTogether(ObjectAnimator.ofFloat(view, "translationX", 0, translationX),
                ObjectAnimator.ofFloat(view, "translationY", 0, translationY),
                ObjectAnimator.ofFloat(view, "scaleX", 0f, 1f),
                ObjectAnimator.ofFloat(view, "scaleY", 0f, 1f), ObjectAnimator.ofFloat(view, "alpha", 0f, 1));
        // 动画周期为500ms
        set.setDuration(1 * 500).start();
    }

    /**
     * 关闭菜单的动画
     *
     * @param view   执行动画的view
     * @param index  view在动画序列中的顺序
     * @param total  动画序列的个数
     * @param radius 动画半径
     */
    private void doAnimateClose(final View view, int index, int total, int radius) {
        if (view.getVisibility() != View.VISIBLE)
            view.setVisibility(View.VISIBLE);
        double degree = Math.PI * index / ((total - 1) * 2);
        int translationX = (int) (radius * Math.cos(degree));
        int translationY = (int) (radius * Math.sin(degree));
        // Log.d(TAG,
        // String.format("degree=%f, translationX=%d, translationY=%d", degree,
        // translationX, translationY));
        AnimatorSet set = new AnimatorSet();
        // 包含平移、缩放和透明度动画
        set.playTogether(ObjectAnimator.ofFloat(view, "translationX", translationX, 0),
                ObjectAnimator.ofFloat(view, "translationY", translationY, 0),
                ObjectAnimator.ofFloat(view, "scaleX", 1f, 0f),
                ObjectAnimator.ofFloat(view, "scaleY", 1f, 0f), ObjectAnimator.ofFloat(view, "alpha", 1f, 0f));
        // 为动画加上事件监听，当动画结束的时候，我们把当前view隐藏
        set.addListener(new AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                view.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animator) {
            }
        });

        set.setDuration(1 * 500).start();
    }
}
