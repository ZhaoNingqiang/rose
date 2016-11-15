package com.flower.rose.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.flower.rose.app.Rose;

/**
 * @Description:
 * @Author: ZhaoNingqiang
 * @Time 2016/10/11 下午1:28
 */

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView {
    protected static final int START_PAGE = 1;
    protected P mPresenter;
    protected BaseActicity mActivity;
    protected View mRootView;
    protected Rose mApp;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = initPresenter();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (BaseActicity) context;
        mApp = (Rose) mActivity.getApplication();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mActivity = null;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null){
            mRootView = inflater.inflate(getLayout(), container,false);
            initView();
            initListener();
        }else {
            ViewParent parent = mRootView.getParent();
            if (parent != null){
                ((ViewGroup)parent).removeView(mRootView);
            }
        }
        return mRootView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isResumed()) {
            loadDataIfNull();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getUserVisibleHint()) {
            loadDataIfNull();
        }

    }

    protected abstract void loadDataIfNull();

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
