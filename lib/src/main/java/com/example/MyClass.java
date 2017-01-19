package com.example;

import java.util.regex.Pattern;

public class MyClass {
    public static final String REGEX_ID_CARD = "(^\\d{17}([0-9]|x|X)$)|(^\\d{15})";

    public static void main(String[] s) {
        String a = "0199";
        System.out.println(Integer.parseInt(a));
    }

    public static boolean isIDCard(String s) {
        if (s == null) {
            return false;
        }
        String idPattern = "(^\\d{17}([0-9]|x|X)$)|(^\\d{15}$)";
        return Pattern.matches(idPattern, s);
    }

    static class Book {
        public Book(String name, float price) {
            this.name = name;
            this.price = price;
        }

        String name;
        float price;
    }


    public static String appendParamStringToUrl(String appendParam, String url) {
        if (isEmpty(url)) return url;
        StringBuilder sb = new StringBuilder(url);
        if (url.contains("?")) {
            String[] temp1 = url.split("/?");
            String temp = temp1[temp1.length - 1];
            if (!isEmpty(temp)) {
                return sb.append("&").append(appendParam).toString();
            } else {
                return sb.append(appendParam).toString();
            }
        } else {
            return sb.append("?").append(appendParam).toString();
        }
    }

    public static boolean isEmpty(CharSequence str) {
        if (str == null || str.length() == 0)
            return true;
        else
            return false;
    }
}
