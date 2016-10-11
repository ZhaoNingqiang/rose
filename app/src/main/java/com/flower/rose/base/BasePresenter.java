package com.flower.rose.base;

/**
 * @Description:
 * @Author: ZhaoNingqiang
 * @Time 2016/10/11 上午11:50
 */

public abstract class BasePresenter<V extends BaseView, M extends BaseModel> {
    protected V mView;
    protected M mModel;

    public BasePresenter(){
        mModel = initModel();
    }
    public void setView(V view) {
        this.mView = view;
    }

    protected abstract M initModel();

}
