package com.flower.rose.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.flower.rose.R;
import com.flower.rose.util.ArgbEvaluator;
import com.flower.rose.util.DensityUtils;


/**
 * 机械锁解锁码的弹窗
 *
 * @author 赵凝强
 * @version 1.0.0
 * @Time 16/12/15/下午3:07
 */

public class BGUnlockCodePopupWindow extends PopupWindow {
    private Activity mActivity;
    private BGColorView cv_unlock_code;
    private CardView cardview;
    private View locationView;
    private View enterView;
    private CountDownTimer timer;

    public BGUnlockCodePopupWindow(Activity activity, String unlockCode, View locationView, View enterView) {
        super(activity);
        mActivity = activity;
        View contentView = View.inflate(mActivity, R.layout.layout_unlock_code, null);

        cv_unlock_code = (BGColorView) contentView.findViewById(R.id.cv_unlock_code);
        cardview = (CardView) contentView.findViewById(R.id.cardview);

        LinearLayout ll_code = (LinearLayout) contentView.findViewById(R.id.ll_code);
        int childCount = ll_code.getChildCount();
        char[] chars = unlockCode.trim().toCharArray();

        for (int i = 0; i < childCount; i++) {
            ((TextView) ll_code.getChildAt(i)).setText(String.valueOf(chars[i]));
        }

        setContentView(contentView);

        setFocusable(true);
        setBackgroundDrawable(new BitmapDrawable());
        setOutsideTouchable(true);
        setTouchable(true);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);

        this.locationView = locationView;
        this.enterView = enterView;

    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        super.showAtLocation(parent, gravity, x, y);
        timer = new CountDownTimer(20 * 1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                dismiss();
            }
        };
        timer.start();
    }

    @Override
    public void dismiss() {
        if (timer != null){
            timer.cancel();
        }
        timer =null;

        final float size = DensityUtils.dip2px(mActivity, 20);

        float scaleWidth = size / cardview.getWidth();
        float scaleHeight = size / cardview.getHeight();

        float endRadius = Math.min(cardview.getWidth(), cardview.getHeight()) * .5f ;

        ValueAnimator widthAnim = ObjectAnimator.ofFloat(cardview, "scaleX", 1f, scaleWidth);
        ValueAnimator heightAnim = ObjectAnimator.ofFloat(cardview, "scaleY", 1f, scaleHeight);
        ValueAnimator radiusAnim = ObjectAnimator.ofFloat(cardview, "radius", cardview.getRadius(), endRadius);

        final int[] startLocation = new int[2];
        cardview.getLocationOnScreen(startLocation);
        final int startX = (int) (startLocation[0] + cardview.getWidth() * .5f);
        final int startY = (int) (startLocation[1] + cardview.getHeight() * .5f);

        int[] location = new int[2];
        locationView.getLocationOnScreen(location);
        int endX = location[0];
        int endY = location[1] + locationView.getHeight();

        Path path = new Path();
        path.moveTo(startX, startY);
        path.quadTo((startX + endX) * .5f, startY, endX, endY);
        final PathMeasure pathMeasure = new PathMeasure(path, false);
        final float[] currentPosition = new float[2];


        ValueAnimator pathAnim = ValueAnimator.ofFloat(0, pathMeasure.getLength());
        pathAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                pathMeasure.getPosTan(value, currentPosition, null);
                cardview.setTranslationX(currentPosition[0] - startX);
                cardview.setTranslationY(currentPosition[1] - startY);
            }
        });

        ObjectAnimator animator = ObjectAnimator.ofInt(cv_unlock_code, "BgColor", 0xAA000000, 0x00000000);
        animator.setEvaluator(ArgbEvaluator.getInstance());

        AnimatorSet set = new AnimatorSet();
        set.play(widthAnim).with(heightAnim).with(animator).with(radiusAnim).before(pathAnim);
        set.setDuration(500);
        set.start();
        set.setInterpolator(new AccelerateDecelerateInterpolator());
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                BGUnlockCodePopupWindow.super.dismiss();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    int endRadius = Math.max(enterView.getWidth(), enterView.getHeight());
                    Animator anim = ViewAnimationUtils.createCircularReveal(enterView, locationView.getLeft(), locationView.getBottom(), size * .5f, endRadius);
                    anim.setDuration(500);
                    anim.setInterpolator(new AccelerateDecelerateInterpolator());
                    anim.start();
                }

            }
        });
    }

}
