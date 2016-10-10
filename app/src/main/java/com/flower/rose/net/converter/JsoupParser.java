package com.flower.rose.net.converter;

import com.flower.rose.model.BaseModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;

/**
 * @Description:
 * @Author: ZhaoNingqiang
 * @Time 2016/09/29 下午4:46
 */

public class  JsoupParser<T> {



    public T parse(Type type, ResponseBody value){
        try {
            if (type instanceof Class<?>) {// Type is a normal class.
                Class<?> clazz = (Class<?>) type;

                Class<?> superclass = clazz.getSuperclass();

                if (superclass == BaseModel.class){
                    Constructor<?> constructor = clazz.getConstructor();
                    Object obj = constructor.newInstance();
                    Method parser = superclass.getDeclaredMethod("parser", Document.class);
                    Object invoke = parser.invoke(obj, Jsoup.parse(value.string()));
                    return (T) invoke;
                }else {
                    return null;
                }

            }


            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;

                // I'm not exactly sure why getRawType() returns Type instead of Class. Neal isn't either but
                // suspects some pathological case related to nested classes exists.
                Type rawType = parameterizedType.getRawType();
                if (!(rawType instanceof Class)) throw new IllegalArgumentException();
//                return (Class<?>) rawType;
            }
            if (type instanceof GenericArrayType) {
                Type componentType = ((GenericArrayType) type).getGenericComponentType();
//                return Array.newInstance(getRawType(componentType), 0).getClass();
            }
            if (type instanceof TypeVariable) {
                // We could use the variable's bounds, but that won't work if there are multiple. Having a raw
                // type that's more general than necessary is okay.
//                return Object.class;
            }
            if (type instanceof WildcardType) {
//                return getRawType(((WildcardType) type).getUpperBounds()[0]);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private void parser(Type type){
        if (type instanceof Class<?>) {
            Class<?> clazz = (Class<?>) type;


        }
        if (type instanceof ParameterizedType) {

        }
    }


}
