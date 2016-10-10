package com.flower.rose.net.converter;


import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * @Description:
 * @Author: ZhaoNingqiang
 * @Time 2016/09/29 下午3:33
 */

public class JsoupResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private JsoupParser jsoupParser;
    private Type type;


    public JsoupResponseBodyConverter(Type type, JsoupParser jsoupParser) {
        this.type = type;
        this.jsoupParser = jsoupParser;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        return (T) jsoupParser.parse(type, value);
    }
}
