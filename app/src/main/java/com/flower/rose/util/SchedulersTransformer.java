package com.flower.rose.util;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @Description:
 * @Author: ZhaoNingqiang
 * @Time 2016/10/12 下午9:41
 */

public class SchedulersTransformer{

    /**
     * IO线程执行，主线程观察
     */
    public static <T> Observable.Transformer<T, T> io_main() {
        return new Observable.Transformer<T, T>() {

            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
