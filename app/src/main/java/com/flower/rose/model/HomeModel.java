package com.flower.rose.model;

import android.support.annotation.IntDef;

import com.flower.rose.base.BaseModel;
import com.flower.rose.been.PictureList;
import com.flower.rose.net.API;
import com.flower.rose.util.SchedulersTransformer;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import rx.Observable;

/**
 * @Description:
 * @Author: ZhaoNingqiang
 * @Time 2016/10/11 下午2:05
 */

public class HomeModel extends BaseModel {
    public static final int SHOW_NEW = 1;
    public static final int SHOW_HOT = 2;
    @IntDef({SHOW_NEW,SHOW_HOT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ShowMode{}


    public Observable<PictureList> pictureList(@ShowMode int showMode, int page) {
       if (showMode == SHOW_NEW){
           return newPicList(page);

       }else {
           return hotPicList(page);
       }
    }

    private Observable<PictureList> newPicList(int page) {
        return API.getInstance().
                getService().
                newPicList(page).
                compose(SchedulersTransformer.<PictureList>io_main());

    }

    private Observable<PictureList> hotPicList(int page) {
        return API.getInstance().
                getService().
                hotPicList(page).
                compose(SchedulersTransformer.<PictureList>io_main());
    }


}
