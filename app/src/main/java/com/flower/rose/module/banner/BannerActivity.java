package com.flower.rose.module.banner;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;

import com.flower.rose.R;
import com.flower.rose.base.BaseActicity;
import com.flower.rose.base.BasePresenter;
import com.flower.rose.been.sub.Picture;
import com.flower.rose.tinker.util.RoseContext;

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


    public static void runBannerActivity(Context context, ArrayList<Picture> pictures,int position, Runnable action) {
        Intent i = new Intent(RoseContext.context, BannerActivity.class);
        i.putExtra(EXTRA_BANNER_DATA, pictures);
        i.putExtra(EXTRA_BANNER_POSITION, position);
        if (action != null) action.run();
        context.startActivity(i);
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
