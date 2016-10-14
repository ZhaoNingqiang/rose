package com.flower.rose.util;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

/**
 * @Description:
 * @Author: ZhaoNingqiang
 * @Time 2016/10/11 下午6:16
 */

public class GlideHelper {
    public static void load(String url, ImageView imageView){
        Glide.with(imageView.getContext().getApplicationContext()).load(url).into(imageView);
    }
}
