/*
 * Tencent is pleased to support the open source community by making Tinker available.
 *
 * Copyright (C) 2016 THL A29 Limited, a Tencent company. All rights reserved.
 *
 * Licensed under the BSD 3-Clause License (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 *
 * https://opensource.org/licenses/BSD-3-Clause
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" basis, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.flower.rose.tinker.util;

import android.app.Application;
import android.content.Context;

import cn.bmob.v3.Bmob;

/**
 * Created by zhangshaowen on 16/8/9.
 */
public class RoseContext {
    public static Application application = null;
    public static Context context = null;

    public static int getScreenWidth(){
        return Holder.SCREEN_WIDTH;
    }

    public static int getScreenHeight(){
        return Holder.SCREEN_HEIGHT;
    }

    static class Holder {
        static int SCREEN_WIDTH = application.getResources().getDisplayMetrics().widthPixels;
        static int SCREEN_HEIGHT = application.getResources().getDisplayMetrics().heightPixels;
    }

    public static void initConfig(Context context){
        //提供以下两种方式进行初始化操作：

        //第一：默认初始化
        Bmob.initialize(context, "3e0f830ee5a402cb653a2d7caff8c233");

        //第二：自v3.4.7版本开始,设置BmobConfig,允许设置请求超时时间、文件分片上传时每片的大小、文件的过期时间(单位为秒)，
        //BmobConfig config =new BmobConfig.Builder(this)
        ////设置appkey
        //.setApplicationId("Your Application ID")
        ////请求超时时间（单位为秒）：默认15s
        //.setConnectTimeout(30)
        ////文件分片上传时每片的大小（单位字节），默认512*1024
        //.setUploadBlockSize(1024*1024)
        ////文件的过期时间(单位为秒)：默认1800s
        //.setFileExpiration(2500)
        //.build();
        //Bmob.initialize(config);
    }


}
