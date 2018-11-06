package com.min.resultdialog.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

import com.min.resultdialog.R;

public class LoadingView extends View {
    private Context context;

    // 动画播放周期
    private int duration = 1000;

    // 动画控制器
    private Interpolator interpolator = new LinearInterpolator();

    // 是否正在播放loading动画
    private boolean isLoading;

    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        this.context = context;

        setBackgroundDrawable(context.getResources().getDrawable(R.drawable.circle_loading));
    }

    /**
     * 开始播放动画
     */
    public void start() {
        if (isLoading) {
            return;
        }
        isLoading = true;
        RotateAnimation animation = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(duration);
        animation.setInterpolator(interpolator);
        animation.setRepeatCount(-1);
        startAnimation(animation);
    }

    /**
     * 停止播放动画
     */
    public void stop() {
        clearAnimation();
        isLoading = false;
    }

    /**
     * 设置动画播放周期
     * @param duration 播放周期，单位毫秒
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * 设置动画控制器
     * @param interpolator
     */
    public void setInterpolator(Interpolator interpolator) {
        this.interpolator = interpolator;
    }

    /**
     * 获取是否正在播放loading动画
     * @return
     */
    public boolean isLoading() {
        return isLoading;
    }
}