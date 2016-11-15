package com.flower.rose.module.home;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.flower.rose.R;
import com.flower.rose.been.sub.Picture;
import com.flower.rose.module.banner.BannerActivity;
import com.flower.rose.util.GlideHelper;
import com.flower.rose.widget.recyclerview.RoseAdapter;
import com.flower.rose.widget.recyclerview.RoseViewHolder;

import java.util.ArrayList;

/**
 * @Description:
 * @Author: ZhaoNingqiang
 * @Time 2016/10/11 下午4:13
 */

public class HomeAdapter extends RoseAdapter<Picture, HomeAdapter.ViewHolder> {


    @Override
    public ViewHolder onCreateContentViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(parent.getContext(), R.layout.grid_item_home, null));
    }

    @Override
    public void onBindContentViewHolder(final ViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Picture> pictures = (ArrayList<Picture>) mData;
                BannerActivity.runBannerActivity(v.getContext(), pictures, position,null);
            }
        });
        Picture picture = getItem(position);
        GlideHelper.load(picture.thumbnail, holder.iv_cover);
        holder.tv_title.setText(picture.alt);
    }

    static class ViewHolder extends RoseViewHolder {
        ImageView iv_cover;
        TextView tv_title;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }


}
