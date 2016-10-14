package com.flower.rose.module.home;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;

import com.flower.rose.R;
import com.flower.rose.base.BaseFragment;
import com.flower.rose.been.PictureList;
import com.flower.rose.widget.recyclerview.RoseRecycleView;
import com.flower.rose.widget.recyclerview.RoseSwipeRefreshLayout;

/**
 * @Description:
 * @Author: ZhaoNingqiang
 * @Time 2016/10/11 下午1:17
 */

public class HomeFragment extends BaseFragment<HomePresenter> implements HomeView {
    private static final int COLUMNS_NUM = 2;
    private RoseSwipeRefreshLayout rvsl_home;
    private RoseRecycleView rv_home;
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
        mPresenter.loadHomeData(START_PAGE);
    }


    @Override
    public void showPictures(int page, PictureList pictureList) {
        if (page == START_PAGE) {
            homeAdapter.setData(pictureList.picture_list);
        }else {
            homeAdapter.addData(pictureList.picture_list);
        }

    }
}
