package com.flower.rose.module.home;

import com.flower.rose.base.BasePresenter;
import com.flower.rose.been.PictureList;
import com.flower.rose.model.HomeModel;

import rx.functions.Action1;

/**
 * @Description:
 * @Author: ZhaoNingqiang
 * @Time 2016/10/11 上午11:53
 */

public class HomePresenter extends BasePresenter<HomeView,HomeModel> {

    public void loadHomeData(final int page) {
        mModel.pictureList(page).subscribe(new Action1<PictureList>() {
            @Override
            public void call(PictureList pictureList) {
                mView.showPictures(page,pictureList);
            }
        });

    }

    @Override
    protected HomeModel initModel() {
        return new HomeModel();
    }
}
