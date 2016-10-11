package com.flower.rose.module.home;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.flower.rose.R;
import com.flower.rose.base.BaseFragment;
import com.flower.rose.been.PictureList;
import com.flower.rose.widget.RecycleViewSwipeLayout;

/**
 * @Description:
 * @Author: ZhaoNingqiang
 * @Time 2016/10/11 下午1:17
 */

public class HomeFragment extends BaseFragment<HomePresenter> implements HomeView {
    private static final int COLUMNS_NUM = 2;
    private RecycleViewSwipeLayout rvsl_home;
    private RecyclerView rv_home;
    private HomeAdapter homeAdapter;


    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        rvsl_home = findView(R.id.rvsl_home);
        rv_home = findView(R.id.rv_home);

    }

    @Override
    protected void initListener() {
        GridLayoutManager layoutManager = new GridLayoutManager(mActivity, COLUMNS_NUM);
        rv_home.setLayoutManager(layoutManager);
        homeAdapter = new HomeAdapter();
        rv_home.setAdapter(homeAdapter);

    }

    @Override
    public HomePresenter initPresenter() {
        HomePresenter homePresenter = new HomePresenter();
        homePresenter.setView(this);
        return homePresenter;
    }

    @Override
    protected void loadDataIfNull() {
        mPresenter.loadHomeData(1);
    }


    @Override
    public void showPictures(int page, PictureList pictureList) {
        if (page == 1) {
            homeAdapter.initData(pictureList);
        }

    }
}
