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
    private static final float DRAG_RATE = .5f;

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
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.d(TAG, "onTouchEvent");

        final int action = MotionEventCompat.getActionMasked(ev);
        int pointerIndex = -1;


        if (!isEnabled() || canChildScrollBottom()) {
            // Fail fast if we're not in a state where a swipe is possible
            return false;
        }

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mActivePointerId = MotionEventCompat.getPointerId(ev, 0);
                mIsBeingDragged = true;
                break;

            case MotionEvent.ACTION_MOVE: {
                pointerIndex = MotionEventCompat.findPointerIndex(ev, mActivePointerId);
                if (pointerIndex < 0) {
                    Log.e(TAG, "Got ACTION_MOVE event but have an invalid active pointer id.");
                    return false;
                }
                if (getScrollY() > mFooterHeight) {
                    Log.e(TAG, " getScrollY() = " + getScrollY() + "  mFooterHeight = " + mFooterHeight);
                    return false;
                }

                final float y = MotionEventCompat.getY(ev, pointerIndex);

                float overscrollBottom = (mInitialMotionY - y) * DRAG_RATE;

                if (overscrollBottom > 0) {
                    mIsBeingDragged = true;
                    overscrollBottom = overscrollBottom > mFooterHeight ? mFooterHeight : overscrollBottom;
                    Log.e(TAG, " overscrollBottom 1 = " + overscrollBottom);
                    setScrollY((int) overscrollBottom);
                } else {
                    mIsBeingDragged = true;
//                    Log.e(TAG, " overscrollBottom = "+ overscrollBottom);
                    return false;
                }

                break;
            }

            case MotionEvent.ACTION_UP: {
                pointerIndex = MotionEventCompat.findPointerIndex(ev, mActivePointerId);
                if (pointerIndex < 0) {
                    Log.e(TAG, "Got ACTION_UP event but don't have an active pointer id.");
                    return false;
                }

                final float y = MotionEventCompat.getY(ev, pointerIndex);
                final float overscrollBottom = (mInitialMotionY - y) * DRAG_RATE;


                if (getScrollY() < mFooterHeight && getScrollY() > mFooterHeight * .5) {

                }


                mIsBeingDragged = false;
                mActivePointerId = INVALID_POINTER;
                return false;
            }
            case MotionEvent.ACTION_CANCEL:
                return false;
        }

        return super.onTouchEvent(ev);
    }


    float mInitialDownY = 0;
    private int mTouchSlop;
    float mInitialMotionY;


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d(TAG, "onInterceptTouchEvent");

        int action = MotionEventCompat.getActionMasked(ev);
        if (!isEnabled() || canChildScrollBottom()) {
            return false;
        }


        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mActivePointerId = MotionEventCompat.getPointerId(ev, 0);
                mIsBeingDragged = getScrollY() > 0 ;
                final float initialDownY = getMotionEventY(ev, mActivePointerId);
                if (initialDownY == -1) {
                    return false;
                }
                mInitialDownY = initialDownY;
                break;

            case MotionEvent.ACTION_MOVE: {
                if (mActivePointerId == INVALID_POINTER) {
                    Log.e(TAG, "Got ACTION_MOVE event but don't have an active pointer id.");
                    return false;
                }

                final float y = getMotionEventY(ev, mActivePointerId);
                if (y == -1) {
                    return false;
                }

                final float yDiff = y - mInitialDownY;
                if (-yDiff > mTouchSlop && !mIsBeingDragged) {
                    mInitialMotionY = mInitialDownY + mTouchSlop;
                    mIsBeingDragged = true;
                    Log.e(TAG, "拦截了事件 ");
                }

                break;
            }

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mIsBeingDragged = getScrollY() > 0 ;
                mActivePointerId = INVALID_POINTER;
                break;
        }
        return super.onInterceptTouchEvent(ev) || mIsBeingDragged;
    }

    private boolean mIsBeingDragged;
    private static final int INVALID_POINTER = -1;

    private int mActivePointerId = INVALID_POINTER;

    private float getMotionEventY(MotionEvent ev, int activePointerId) {
        final int index = MotionEventCompat.findPointerIndex(ev, activePointerId);
        if (index < 0) {
            return -1;
        }
        return MotionEventCompat.getY(ev, index);
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












