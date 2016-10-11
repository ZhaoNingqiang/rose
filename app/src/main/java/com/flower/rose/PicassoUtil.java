package com.flower.rose;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * @Description:
 * @Author: ZhaoNingqiang
 * @Time 2016/10/11 下午6:16
 */

public class PicassoUtil {
    public static void load(String url, ImageView imageView){
        Picasso.with(imageView.getContext().getApplicationContext()).load(url).into(imageView);
    }
}
