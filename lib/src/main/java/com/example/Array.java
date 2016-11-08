package com.example;

/**
 * @Description:
 * @Author: ZhaoNingqiang
 * @Time 2016/11/08 下午2:32
 */

public class Array {
    public static void main(String aras[]){
        String[] arr1 = new String[]{"first","second"};
        String[] arr2 = new String[arr1.length + 1];
        arr2[0] = "abc";
        System.arraycopy(arr1,0,arr2,1,arr1.length);
        for (String s : arr2){
            System.out.println(s);
        }
    }
}
