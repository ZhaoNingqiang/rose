package com.flower.rose.widget.recyclerview;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.flower.rose.util.LogUtil;

/**
 * @author: ningqiang
 * @time: 16/11/12
 * @description:
 */

public class GridItemDecoration extends RecyclerView.ItemDecoration {
    private String tag = this.getClass().getSimpleName();
    int dividerHeight = 0;

    public GridItemDecoration(int dividerHeight) {
        super();
        this.dividerHeight = dividerHeight;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }



    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int spanCount = getSpanCount(parent);
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        int position = layoutManager.getPosition(view);
        int itemCount = parent.getAdapter().getItemCount();

        if (isFirstColumn(layoutManager, spanCount, position)) {
            outRect.set(0, 0, Math.round(dividerHeight * .5f), dividerHeight);
        } else if (isLastColumn(layoutManager, spanCount, position)) {
            outRect.set(Math.round(dividerHeight * .5f), 0, 0, dividerHeight);
        } else {
            outRect.set(Math.round(dividerHeight * .5f), 0, Math.round(dividerHeight * .5f), dividerHeight);
        }
        LogUtil.d(tag, "getItemOffsets pos = " + position + " spanCount = " + spanCount + " itemCount = " + itemCount);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

        LogUtil.d(tag, "onDrawOver");


//        c.drawColor(Color.RED);
    }


    private int getSpanCount(RecyclerView parent) {

        int spanCount = -1;

        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();

        if (layoutManager instanceof GridLayoutManager) {

            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();

        } else if (layoutManager instanceof StaggeredGridLayoutManager) {

            spanCount = ((StaggeredGridLayoutManager) layoutManager).getSpanCount();

        }

        return spanCount;
    }

    /**
     * 是否是最后一列
     *
     * @return
     */
    private boolean isLastColumn(RecyclerView.LayoutManager layoutManager, int spanCount, int position) {

        if (layoutManager instanceof GridLayoutManager) {
            if ((position + 1) % spanCount == 0) {
                return true;
            }
        }
        return false;

    }

    /**
     * 是否是第一列
     *
     * @return
     */
    private boolean isFirstColumn(RecyclerView.LayoutManager layoutManager, int spanCount, int position) {

        if (layoutManager instanceof GridLayoutManager) {
            if ((position) % spanCount == 0) {
                return true;
            }
        }
        return false;

    }
}
