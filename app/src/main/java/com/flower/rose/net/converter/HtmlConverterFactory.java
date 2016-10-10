package com.flower.rose.net.converter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * @Description:
 * @Author: ZhaoNingqiang
 * @Time 2016/09/29 上午11:51
 */

public class HtmlConverterFactory extends Converter.Factory {
    public static HtmlConverterFactory create() {
        return create(new JsoupParser());
    }

    public static HtmlConverterFactory create(JsoupParser parser) {
        return new HtmlConverterFactory(parser);
    }

    private HtmlConverterFactory(JsoupParser parser) {
        if (parser == null) throw new NullPointerException("JsoupParser == null");
        this.parser = parser;
    }

    private final JsoupParser parser;


    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                            Retrofit retrofit) {

        return new JsoupResponseBodyConverter<>(type, parser);

    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type,
                                                          Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {

        return new JsoupRequestBodyConverter();
    }
}
