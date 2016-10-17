package com.flower.rose.widget.recyclerview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ScrollerCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.AbsListView;

import com.flower.rose.R;

/**
 * @Description: add footer for load more
 * @Author: ZhaoNingqiang
 * @Time 2016/09/28 下午8:18
 */

public class RoseSwipeRefreshLayout<RV extends RoseRecycleView> extends SwipeRefreshLayout {
    private static final String TAG = "RoseSwipeRefreshLayout";
    public static final int FOOTER_HEIGHT = 80;

    private boolean mRefreshRecyclerView = true;

    private int mRecyclerViewId;
    private RV mRecyclerView;

    private View mFooterView;
    private int mFooterHeight;

    private int mCurrentTargetOffsetTop = 0;

    private boolean mNotify;

    public RoseSwipeRefreshLayout(Context context) {
        this(context, null);
    }

    public RoseSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RoseSwipeRefreshLayout);
        mRecyclerViewId = a.getResourceId(R.styleable.RoseSwipeRefreshLayout_recyclerViewId, -1);
        a.recycle();

        final DisplayMetrics metrics = getResources().getDisplayMetrics();
        mFooterHeight = (int) (FOOTER_HEIGHT * metrics.density);
        createFooterView();
    }

    private void createFooterView() {
        mFooterView = View.inflate(getContext(), R.layout.footer_load_more, null);
        mFooterView.setVisibility(VISIBLE);
        addView(mFooterView);

    }


    private void ensureRecyclerView() {
        if (!mRefreshRecyclerView) {
            return;
        }
        //首先清空当前的recyclerView
        mRecyclerView = null;

        if (mRecyclerViewId != -1) {
            //如果设置了id
            mRecyclerView = (RV) findViewById(mRecyclerViewId);
        }
        if (mRecyclerView == null) {
            RV rv = null;
            for (int i = 0, count = getChildCount(); i < count; i++) {
                final View child = getChildAt(i);
                if (child instanceof RoseRecycleView) {
                    rv = (RV) child;
                    break;
                }
            }
            mRecyclerView = rv;
        }

        if (mRecyclerView != null) {
            mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                }

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    RoseAdapter adapter = (RoseAdapter) recyclerView.getAdapter();

                    LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    int lastVisibleItemPosition = layoutManager.findLastCompletelyVisibleItemPosition();
                    if (adapter.getItemCount() - lastVisibleItemPosition == 1 && dy > 0) {

                        //startScrollAnimation();
                        if (mLoadMoreListener != null) {
                            mLoadMoreListener.onLoaderMore();
                        }
                    }

                }
            });
        }
        mRefreshRecyclerView = false;
    }

    int ALPHA_ANIMATION_DURATION = 300;

    private void startScrollAnimation() {
//        if (mLoading)return;
        mCurrentTargetOffsetTop = mFooterHeight;
        requestLayout();
        ValueAnimator animator = ValueAnimator.ofInt(0, mFooterHeight);
        animator.setDuration(ALPHA_ANIMATION_DURATION);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int currentValue = (int) animation.getAnimatedValue();
                setScrollY(currentValue);
            }
        });
        animator.start();
//        mLoading = true;
    }



    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mRecyclerView == null) {
            ensureRecyclerView();
        }
        if (mRecyclerView == null) {
            return;
        }


        mFooterView.measure(MeasureSpec.makeMeasureSpec(getMeasuredWidth() - getPaddingLeft() - getPaddingRight(), MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(mFooterHeight, MeasureSpec.EXACTLY));


        mRecyclerView.measure(MeasureSpec.makeMeasureSpec(
                getMeasuredWidth() - getPaddingLeft() - getPaddingRight(),
                MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(
                getMeasuredHeight() - mFooterHeight - getPaddingTop() - getPaddingBottom(), MeasureSpec.EXACTLY));

        Log.d(TAG, "onMeasure");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        final int width = getMeasuredWidth();
        final int height = getMeasuredHeight();
        if (getChildCount() == 0) {
            return;
        }
        if (mRecyclerView == null) {
            ensureRecyclerView();
        }
        if (mRecyclerView == null) {
            return;
        }


        final View child = mRecyclerView;
        final int childLeft = getPaddingLeft();
        final int childTop = getPaddingTop();
        final int childWidth = width - getPaddingLeft() - getPaddingRight();
        final int childHeight = height - getPaddingTop() - getPaddingBottom();
        child.layout(childLeft, childTop - mCurrentTargetOffsetTop, childLeft + childWidth, childTop + childHeight);

        mFooterView.layout(childLeft, childTop + childHeight, childLeft + childWidth, childTop + childHeight + mFooterHeight);
        Log.d(TAG, "onLayout mCurrentTargetOffsetTop = " + mCurrentTargetOffsetTop);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = MotionEventCompat.getActionMasked(ev);


        switch (action) {
            case MotionEvent.ACTION_DOWN:
                break;

            case MotionEvent.ACTION_MOVE: {
//                if (!canChildScrollBottom()){
//                        setScrollY((int) mInitialMotionY);
//                        return true;
//                }
                break;
            }

            case MotionEvent.ACTION_UP: {
//                if (!canChildScrollBottom()){
//                    startScrollAnimation();
//                    return true;
//                }

            }
            case MotionEvent.ACTION_CANCEL:
//                if (!canChildScrollBottom()){
//                    startScrollAnimation();
//                    return true;
//                }
        }
        Log.d(TAG, "onTouchEvent");
        return super.onTouchEvent(ev);
    }


    float mInitialDownY = 0;
    private int mTouchSlop;
    float mInitialMotionY;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d(TAG, "onInterceptTouchEvent");

        int action = MotionEventCompat.getActionMasked(ev);
        if (!isEnabled() || canChildScrollBottom()){
            return false;
        }

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                 mInitialDownY =  MotionEventCompat.getY(ev, 0);
                break;

            case MotionEvent.ACTION_MOVE: {
                if (canChildScrollBottom()){
                    final float y =  MotionEventCompat.getY(ev, 0);
                    final float yDiff = y - mInitialDownY;
                    if (yDiff > mTouchSlop && canChildScrollBottom()) {
                        mInitialMotionY = mInitialDownY + mTouchSlop;
                        return true;
                    }else {
                        return false;
                    }
                }

            }

            case MotionEvent.ACTION_UP: {
                if (!canChildScrollBottom()){
                    return true;
                }
            }
        }
        return super.onInterceptTouchEvent(ev);
    }

    private boolean mLoading = false;

    LoadMoreListener mLoadMoreListener;

    public interface LoadMoreListener {
        void onLoaderMore();
    }

    public boolean canChildScrollBottom() {
        return ViewCompat.canScrollVertically(mRecyclerView, 1);
    }


}












