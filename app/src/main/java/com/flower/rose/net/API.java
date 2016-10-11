package com.flower.rose.net;

import com.flower.rose.net.converter.HtmlConverterFactory;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * @Description:
 * @Author: ZhaoNingqiang
 * @Time 2016/10/11 下午4:45
 */

public class API {
    private static final String ZB_HOST = "http://www.zhuangbi.info/";
    private static API instance;
    private  RoseService roseService;
    static {
        instance = new API();
    }


    private API(){
        roseService = createRetrofit().create(RoseService.class);
    }

    public static API getInstance(){
        return instance;
    }

    public RoseService getService(){
        return roseService;
    }

    private Retrofit createRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ZB_HOST)
                .addConverterFactory(HtmlConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit;
    }
}
