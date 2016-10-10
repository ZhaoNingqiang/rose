package com.flower.rose.net;

import com.flower.rose.model.PictureList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @Description:
 * @Author: ZhaoNingqiang
 * @Time 2016/09/29 上午11:15
 */

public interface RoseService {

    @GET("/")
    Call<PictureList> groupList(@Query("page") int page);
}
