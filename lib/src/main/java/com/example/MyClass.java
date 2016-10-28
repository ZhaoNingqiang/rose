package com.example;

import java.util.regex.Pattern;

public class MyClass {
    public static final String REGEX_ID_CARD = "(^\\d{17}([0-9]|x|X)$)|(^\\d{15})";

    public static void main(String[] a) {
//        String[] s = {"401039900039493930","123456789012345","40103990003949393v","12345678901234X","40103990003949393X"};
//        for (String ss:s) {
//            boolean idCard = isIDCard(ss);
//            System.out.println("idCard = " + idCard + "  length = " + ss.length());
//        }

        String aa = "优集品UJIPIN是品质生活电商中的佼佼者<br />致力于把“更高品质，更好生活”带给每一位热爱生活的中国城市消费者<br />30位专业买手，搜遍全球<br />100位资深编辑，推荐下载<br />300位意见领袖，分享攻略<br />500+优选品牌，15000+优质商品<br />我们以挑剔态度和国际视野<br />提升你的日常生活细节<br />用专业服务和内容场景<br />实现你的品质生活梦想<br />花很长的时间，好好地泡个热水澡，彻底放松自己<br />每个周末，给家人做一顿像样的早餐，看着他们吃完<br />去巴黎呆两周，住在玛黑区的小公寓，整个假期都在散步<br />陪着宝宝，简单而美好地观察这个世界，就像曾经的自己那样<br />开始学习一样乐器，或者重新写日记，情怀细腻如初<br />你相信生活中总有些事物，值得被拥有，值得被守护<br />但你往往无暇顾及<br />在大世界中已经恰当而美好地获得了社会肯定，坚定地拒绝向平庸妥协<br />你在意服饰与妆容的穿搭品味，也重视自己与家人的生活品质<br />你渴望着一个精致而温暖的小世界<br />但你不知从何下手<br />生活需要空间，更需要时间，优集品用最便捷的方式告诉你<br />为何选择，如何选择";
//       String aaa=  aa.replace("<br />","\r");
//        System.out.printf(aaa);
//        Fil
        String[] split = aa.split("<br />");
        for (String aaa : split){
            System.out.println(aaa);
        }
    }

    public static boolean isIDCard(String s) {
        if (s == null) {
            return false;
        }
        String idPattern = "(^\\d{17}([0-9]|x|X)$)|(^\\d{15})";
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
