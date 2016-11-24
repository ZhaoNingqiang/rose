package com.flower.rose.util;

/**
 * @Description:
 * @Author: ZhaoNingqiang
 * @Time 2016/11/24 下午12:49
 */

public class TextConvert {
    public static int convert2Int(String value) {
        int restult = 0;
        try {
            restult = Integer.valueOf(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return restult;

    }
}
