package com.example;

import java.util.LinkedList;

public class MyClass {
    public static void main(String[] a){
        String url = "http://www.ujipin.com/home?a=b&c=d";
        url = appendParamStringToUrl("success=success",url);
        System.out.printf("url = "+url);
    }

    static class Book{
        public Book(String name, float price) {
            this.name = name;
            this.price = price;
        }

        String name;
        float price;
    }


    public static String appendParamStringToUrl(String appendParam,String url){
        if (isEmpty(url)) return url;
        StringBuilder sb = new StringBuilder(url);
        if (url.contains("?")){
            String [] temp1 = url.split("/?");
            String temp = temp1[temp1.length-1];
            if (!isEmpty(temp)){
                return sb.append("&").append(appendParam).toString();
            }else{
                return sb.append(appendParam).toString();
            }
        }else{
            return sb.append("?").append(appendParam).toString();
        }
    }

    public static boolean isEmpty( CharSequence str) {
        if (str == null || str.length() == 0)
            return true;
        else
            return false;
    }
}
