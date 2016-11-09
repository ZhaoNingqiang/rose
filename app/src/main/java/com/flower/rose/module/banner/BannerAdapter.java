package com.flower.rose.module.banner;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.flower.rose.R;
import com.flower.rose.been.sub.Picture;
import com.flower.rose.util.GlideHelper;
import com.flower.rose.util.ViewInject;
import com.flower.rose.widget.viewpager.RecyclingPagerAdapter;

import java.util.ArrayList;

/**
 * @Description:
 * @Author: ZhaoNingqiang
 * @Time 2016/11/09 下午2:40
 */

public class BannerAdapter extends RecyclingPagerAdapter {
    private ArrayList<Picture> pictures;

    public BannerAdapter(ArrayList<Picture> pictures) {
        this.pictures = pictures;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup container) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(container.getContext(), R.layout.item_banner, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(R.id.tag_1,viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag(R.id.tag_1);
        }
        GlideHelper.load(pictures.get(position).href, viewHolder.iv_item_banner);

        return convertView;
    }

    @Override
    public int getCount() {
        return pictures == null ? 0 : pictures.size();
    }

    static class ViewHolder {
        public ViewHolder(View itemView) {
            ViewInject.injectViews(this, itemView);
        }

        ImageView iv_item_banner;
    }
}
