package com.flower.rose.model;

import com.flower.rose.base.BaseModel;
import com.flower.rose.been.PictureList;
import com.flower.rose.net.API;
import com.flower.rose.util.SchedulersTransformer;

import rx.Observable;

/**
 * @Description:
 * @Author: ZhaoNingqiang
 * @Time 2016/10/11 下午2:05
 */

public class HomeModel extends BaseModel {
    public Observable<PictureList> pictureList(int page) {
        return API.getInstance().
                getService().
                pictureList(page).
                compose(SchedulersTransformer.<PictureList>io_main());

    }


}
