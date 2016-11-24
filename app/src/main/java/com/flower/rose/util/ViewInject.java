package com.flower.rose.util;

import android.view.View;

import com.flower.rose.R;

import java.lang.reflect.Field;

/**
 * @Description:
 * @Author: ZhaoNingqiang
 * @Time 2016/10/12 下午4:34
 */

public class ViewInject {
    public static int getId(String FieldName) {
        int id = 0;
        try {
            Field field = R.id.class.getField(FieldName);
            id = (int) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    public static void injectView(Object object,View parent,String fieldName) throws NoSuchFieldException, IllegalAccessException {
            Class<?> clazz = object.getClass();
            Field  field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            View view = parent.findViewById(getId(fieldName));
            field.set(object,view);
    }

    public static void injectViews(Object object,View rootView){
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field: fields){
            String fieldName = field.getName();
            try {
                injectView(object,rootView,fieldName);
            }catch (Exception e){
                continue;
            }

        }
    }

}
