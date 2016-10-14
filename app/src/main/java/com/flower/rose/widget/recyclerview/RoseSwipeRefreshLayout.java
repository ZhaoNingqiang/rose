package com.flower.rose.widget.recyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.flower.rose.R;

/**
 * @Description: add footer for load more
 * @Author: ZhaoNingqiang
 * @Time 2016/09/28 下午8:18
 */

public class RoseSwipeRefreshLayout<RV extends RoseRecycleView> extends SwipeRefreshLayout {
    private boolean mRefreshRecyclerView = true;

    private int mRecyclerViewId;
    private RV mRecyclerView;

    private View mFooterView;

    public RoseSwipeRefreshLayout(Context context) {
        this(context, null);
    }

    public RoseSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RoseSwipeRefreshLayout);
        mRecyclerViewId = a.getResourceId(R.styleable.RoseSwipeRefreshLayout_recyclerViewId, -1);
        a.recycle();
        createFooterView();
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
                    int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                    if (adapter.getItemCount() - lastVisibleItemPosition == 1 && dy > 0) {
                        if (mLoadMoreListener != null) {
                            mLoadMoreListener.onLoaderMore();
                        }
                    }

                }
            });
        }
        mRefreshRecyclerView = false;
    }


    private void createFooterView() {
        mFooterView = View.inflate(getContext(), R.layout.footer_load_more, null);
        mFooterView.setVisibility(VISIBLE);
        addView(mFooterView);

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

        int measuredHeight = mFooterView.getMeasuredHeight();
        Log.d("", "measuredHeight = " + measuredHeight + " height = " + getMeasuredHeight());
        final DisplayMetrics metrics = getResources().getDisplayMetrics();
        measuredHeight = (int) (80 * metrics.density);


        mFooterView.measure(MeasureSpec.makeMeasureSpec(getMeasuredWidth() - getPaddingLeft() - getPaddingRight(), MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(measuredHeight, MeasureSpec.EXACTLY));


        mRecyclerView.measure(MeasureSpec.makeMeasureSpec(
                getMeasuredWidth() - getPaddingLeft() - getPaddingRight(),
                MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(
                getMeasuredHeight() - measuredHeight - getPaddingTop() - getPaddingBottom(), MeasureSpec.EXACTLY));


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
        final int childHeight = height - getPaddingTop() - getPaddingBottom() - mFooterView.getMeasuredHeight();
        child.layout(childLeft, childTop, childLeft + childWidth, childTop + childHeight);


        mFooterView.layout(childLeft, childTop + childHeight, childLeft + childWidth, childTop + childHeight + mFooterView.getMeasuredHeight());

    }


    LoadMoreListener mLoadMoreListener;

    public interface LoadMoreListener {
        void onLoaderMore();
    }


}












