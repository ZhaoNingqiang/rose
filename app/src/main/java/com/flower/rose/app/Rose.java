package com.flower.rose.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * @Description:
 * @Author: ZhaoNingqiang
 * @Time 2016/09/28 上午11:36
 */

public class Rose extends TinkerApplication {
    public Rose() {
        super(ShareConstants.TINKER_ENABLE_ALL,"com.flower.rose.app.RoseLike");
        Fresco.initialize(this);
    }

}
