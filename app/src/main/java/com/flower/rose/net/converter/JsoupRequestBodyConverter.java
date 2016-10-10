package com.flower.rose.net.converter;

import java.io.IOException;

import okhttp3.RequestBody;
import retrofit2.Converter;

/**
 * @Description:
 * @Author: ZhaoNingqiang
 * @Time 2016/09/29 下午4:37
 */

public class JsoupRequestBodyConverter<T> implements Converter<T, RequestBody> {
    @Override
    public RequestBody convert(T value) throws IOException {
        return null;
    }
}
