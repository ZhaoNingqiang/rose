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

    public GridItemDecoration() {
        super();
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        LogUtil.d(tag, "onDraw");
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int spanCount = getSpanCount(parent);
        int position = parent.getLayoutManager().getPosition(view);
        int itemCount = parent.getAdapter().getItemCount();
        outRect.set(0,0,12,12);





        LogUtil.d(tag, "getItemOffsets pos = "+position+" spanCount = "+spanCount+" itemCount = "+itemCount);






    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

        LogUtil.d(tag, "onDrawOver");
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
}
