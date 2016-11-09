package com.flower.rose.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;

/**
 * @Description:
 * @Author: ZhaoNingqiang
 * @Time 2016/10/08 下午1:04
 */

public abstract class BaseActicity<P extends BasePresenter> extends AppCompatActivity implements BaseView{
    protected static final int START_PAGE = 1;
    protected P mPresenter;
    protected View mRootView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRootView = View.inflate(this,getLayout(),null);
        setContentView(mRootView);
        ButterKnife.bind(this);
        mPresenter = initPresenter();
        initView();
        initListener();
    }



    protected <T extends View> T findView(int id) {
        return (T) mRootView.findViewById(id);
    }

    @Override
    public void showToast() {

    }

    protected abstract int getLayout();

    protected abstract void initView();

    protected abstract void initListener();

    public abstract P initPresenter();
}
