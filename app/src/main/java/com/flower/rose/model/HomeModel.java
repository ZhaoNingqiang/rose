package com.flower.rose.model;

import com.flower.rose.base.BaseModel;
import com.flower.rose.been.PictureList;
import com.flower.rose.net.API;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * @Description:
 * @Author: ZhaoNingqiang
 * @Time 2016/10/11 下午2:05
 */

public class HomeModel extends BaseModel{
    public Observable<PictureList> pictureList(int page){
       return API.getInstance().getService().pictureList(page).doOnNext(new Action1<PictureList>() {
           @Override
           public void call(PictureList pictureList) {

           }
       }).compose(new Observable.Transformer<PictureList, PictureList>() {
           @Override
           public Observable<PictureList> call(Observable<PictureList> pictureListObservable) {
               return pictureListObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
           }
       });

    }



}
