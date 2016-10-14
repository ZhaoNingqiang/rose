package com.flower.rose.widget.recyclerview;

import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @Description: 为recyclerView 添加header 和 footer 的adapter
 * @Author: ZhaoNingqiang
 * @Time 2016/10/12 下午3:54
 */

public abstract class RoseAdapter<D, VH extends RoseViewHolder> extends RecyclerView.Adapter<RoseViewHolder> {
    private static final int BASE_ITEM_TYPE_HEADER = 1000000;
    private static final int BASE_ITEM_TYPE_FOOTER = 2000000;

    private SparseArrayCompat<View> mHeaderViews = new SparseArrayCompat<View>();
    private SparseArrayCompat<View> mFooterViews = new SparseArrayCompat<View>();


    protected List<D> mData;


    public boolean isHeaderViewPos(int postion) {
        return postion < getHeaderCount();
    }

    public boolean isFootViewsPos(int position) {
        return position >= getHeaderCount() + getContentItemCount();
    }

    public void addHeaderView(View headerView) {
        mHeaderViews.put(getHeaderCount() + BASE_ITEM_TYPE_HEADER, headerView);
    }

    public void addFooterView(View footerView) {
        mFooterViews.put(getFooterCount() + BASE_ITEM_TYPE_FOOTER, footerView);
    }

    public int getHeaderCount() {
        return mHeaderViews.size();
    }

    public int getFooterCount() {
        return mFooterViews.size();
    }

    public int getContentItemCount() {
        return mData == null ? 0 : mData.size();
    }


    @Override
    public int getItemViewType(int position) {
        if (isHeaderViewPos(position)) {
            return mHeaderViews.keyAt(position);
        }
        if (isFootViewsPos(position)) {
            return mFooterViews.keyAt(position - getHeaderCount() - getContentItemCount());
        }

        return getContentViewType(position - getHeaderCount());
    }


    public int getContentViewType(int position) {
        return 0;
    }


    public abstract VH onCreateContentViewHolder(ViewGroup parent, int viewType);


    public abstract void onBindContentViewHolder(VH holder, int position);


    @Override
    public RoseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderViews.get(viewType) != null) {
            return new RoseViewHolder(mHeaderViews.get(viewType));
        }

        if (mFooterViews.get(viewType) != null) {
            return new RoseViewHolder(mFooterViews.get(viewType));
        }

        return onCreateContentViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RoseViewHolder holder, int position) {
        if (isFootViewsPos(position)) {
            return;
        }
        if (isHeaderViewPos(position)) {
            return;
        }
        onBindContentViewHolder((VH) holder, position - getHeaderCount());
    }


    @Override
    public int getItemCount() {
        return getContentItemCount() + getHeaderCount() + getFooterCount();
    }


    /**
     * 兼容gridLayoutManager
     * @param recyclerView
     */
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {

        super.onAttachedToRecyclerView(recyclerView);

        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();

        if (layoutManager instanceof GridLayoutManager){

            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;

            final GridLayoutManager.SpanSizeLookup spanSizeLookup = gridLayoutManager.getSpanSizeLookup();

            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (isHeaderViewPos(position) || isFootViewsPos(position)){
                        return gridLayoutManager.getSpanCount();
                    }
                    if (spanSizeLookup != null){
                        return spanSizeLookup.getSpanSize(position);
                    }
                    return 1;
                }
            });

        }
    }

    /**
     * 兼容staggeredGridLayoutManger
     * @param holder
     */
    @Override
    public void onViewAttachedToWindow(RoseViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        int position = holder.getLayoutPosition();
        if (isHeaderViewPos(position) || isFootViewsPos(position)){
            ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
            if (params != null && params instanceof StaggeredGridLayoutManager.LayoutParams){
                StaggeredGridLayoutManager.LayoutParams sgParams = (StaggeredGridLayoutManager.LayoutParams) params;
                sgParams.setFullSpan(true);
            }
        }
    }

    public void addData(List<D> data){
        if (mData == null){
            throw new IllegalArgumentException();
        }
        mData.addAll(data);
        notifyDataSetChanged();

    }

    public void setData(List<D> data){
        mData = data;
        notifyDataSetChanged();
    }

    public boolean isEmpty(){
        return mData == null || mData.size() == 0;
    }

    public D getItem(int position){
        if (isEmpty()){
            return null;
        }else {
            return mData.get(position);
        }
    }

    public boolean showLoaderMore(RecyclerView recyclerView) {
        return true;
    }

}
