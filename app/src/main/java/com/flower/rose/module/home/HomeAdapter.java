package com.flower.rose.module.home;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.flower.rose.PicassoUtil;
import com.flower.rose.R;
import com.flower.rose.been.PictureList;
import com.flower.rose.been.sub.Picture;

/**
 * @Description:
 * @Author: ZhaoNingqiang
 * @Time 2016/10/11 下午4:13
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.Holder> {
    private PictureList pictureList;

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(View.inflate(parent.getContext(), R.layout.grid_item_home, null));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Picture picture = pictureList.picture_list.get(position);
        PicassoUtil.load(picture.thumbnail,holder.iv_cover);
        holder.tv_title.setText(picture.alt);
    }

    @Override
    public int getItemCount() {
       return (pictureList == null || pictureList.picture_list == null) ? 0 : pictureList.picture_list.size();
    }

    public void initData(PictureList pictureList) {
        this.pictureList = pictureList;
        notifyDataSetChanged();
    }

    class Holder extends RecyclerView.ViewHolder {
        ImageView iv_cover;
        TextView tv_title;

        public Holder(View itemView) {
            super(itemView);
            iv_cover = (ImageView) itemView.findViewById(R.id.iv_cover);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }
}
