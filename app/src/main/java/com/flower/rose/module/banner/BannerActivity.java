package com.flower.rose.module.banner;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.flower.rose.R;
import com.flower.rose.base.BaseActicity;
import com.flower.rose.base.BasePresenter;
import com.flower.rose.been.sub.Picture;
import com.flower.rose.tinker.util.SampleApplicationContext;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * @Description:
 * @Author: ZhaoNingqiang
 * @Time 2016/11/09 下午12:04
 */

public class BannerActivity extends BaseActicity {
    private static final String EXTRA_BANNER_DATA = "extra_banner_data";
    private static final String EXTRA_BANNER_POSITION = "extra_banner_position";
    @BindView(R.id.banner)
    ViewPager mBanner;

    private ArrayList<Picture> mPictures;
    private int mPosition;


    public static void runBannerActivity(Activity activity, ArrayList<Picture> pictures, int position, View v) {
        Intent i = new Intent(SampleApplicationContext.context, BannerActivity.class);
        i.putExtra(EXTRA_BANNER_DATA, pictures);
        i.putExtra(EXTRA_BANNER_POSITION, position);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options =
                    ActivityOptions.makeSceneTransitionAnimation(activity, v,activity.getString(R.string.image_transition_name));
            activity.startActivity(i,options.toBundle());
        }else {
            activity.startActivity(i);
        }

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_banner;
    }

    @Override
    protected void initView() {
        Intent fromIntent = getIntent();
        mPictures = fromIntent.getParcelableArrayListExtra(EXTRA_BANNER_DATA);
        mPosition = fromIntent.getIntExtra(EXTRA_BANNER_POSITION,0);
        BannerAdapter mBannerAdapter = new BannerAdapter(mPictures);
        mBanner.setAdapter(mBannerAdapter);
        mBanner.setCurrentItem(mPosition);
    }

    @Override
    protected void initListener() {

    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

}
