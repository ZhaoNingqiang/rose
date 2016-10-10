package com.flower.rose.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * @Description:
 * @Author: ZhaoNingqiang
 * @Time 2016/09/28 上午11:36
 */

public class Rose extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
