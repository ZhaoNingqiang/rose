package com.flower.rose.widget.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.flower.rose.util.ViewInject;

/**
 * @Description:
 * @Author: ZhaoNingqiang
 * @Time 2016/10/12 下午3:55
 */

public class RoseViewHolder extends RecyclerView.ViewHolder{
    public RoseViewHolder(View itemView) {
        super(itemView);
        ViewInject.injectViews(this,itemView);
    }
}
