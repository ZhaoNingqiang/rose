package com.flower.rose.module.home;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;

import com.flower.rose.R;
import com.flower.rose.base.BaseFragment;
import com.flower.rose.been.PictureList;
import com.flower.rose.widget.recyclerview.GridItemDecoration;
import com.flower.rose.widget.recyclerview.RoseRecycleView;
import com.flower.rose.widget.recyclerview.RoseSwipeRefreshLayout;

/**
 * @Description:
 * @Author: ZhaoNingqiang
 * @Time 2016/10/11 下午1:17
 */

public class HomeFragment extends BaseFragment<HomePresenter> implements HomeView {
    private String TAG = "HomeFragment";
    private static final int COLUMNS_NUM = 2;
    private RoseSwipeRefreshLayout rvsl_home;
    private RoseRecycleView rv_home;
    private HomeAdapter homeAdapter;
    private int currentPage = 1;


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
        rv_home.addItemDecoration(new GridItemDecoration());
        rvsl_home.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.d(TAG,"onRefresh ");
                loadDataIfNull();
            }
        });
        rvsl_home.setLoadMoreListener(new RoseSwipeRefreshLayout.LoadMoreListener() {
            @Override
            public void onLoaderMore() {
                Log.d(TAG,"onLoaderMore currentPage = "+currentPage);
                currentPage++;
                mPresenter.loadHomeData(currentPage);
            }
        });

    }

    @Override
    public HomePresenter initPresenter() {
        HomePresenter homePresenter = new HomePresenter();
        homePresenter.setView(this);
        return homePresenter;
    }

    @Override
    protected void loadDataIfNull() {
        currentPage = START_PAGE;
        mPresenter.loadHomeData(currentPage);
    }


    @Override
    public void showPictures(int page, PictureList pictureList) {
        if (page == START_PAGE) {
//            if (rvsl_home.isRefreshing()){
//                rvsl_home.setRefreshing(false);
//            }
            homeAdapter.setData(pictureList.picture_list);
        }else {
            rvsl_home.setLoading(false);
            homeAdapter.addData(pictureList.picture_list);
        }

    }
}
