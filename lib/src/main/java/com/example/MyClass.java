package com.example;

import java.util.LinkedList;

public class MyClass {
    public static void main(String[] a){
//        LinkedList<Book> books = new LinkedList<Book>();
//        Class<? extends LinkedList> aClass = books.getClass();
//        System.out.printf("aClass = "+aClass);
        String aa = "{\"status_code\":200,\"message\":\"success\",\"data\":{\"payment\":{\"name\":\"在线支付\",\"pay_id\":31,\"is_online\":true},\"pay_fee\":\"0.00\",\"order_amount\":\"0.00\",\"goods_amount\":\"58.00\",\"promotions_amount\":\"0.00\",\"coupon_amount\":\"0.00\",\"integral_amount\":\"0.00\",\"surplus_amount\":\"63.00\",\"postage\":\"5.00\",\"address\":{\"address_id\":525526,\"consignee\":\"Deeds\",\"province\":\"北京\",\"city\":\"北京\",\"district\":\"东城区\",\"address\":\"FedEx\",\"mobile\":\"18635291062\",\"zipcode\":\"\"},\"cod\":{\"allow\":true,\"desc\":\"需付5元手续费，仅支持金额2000元以下订单\"},\"hide_price_term\":{\"allow\":false,\"desc\":\"我要送人，请帮我去掉价签\",\"hide_price\":false},\"coupon_term\":{\"allow\":true,\"desc\":\"\"},\"integral_term\":{\"allow\":true,\"desc\":\"\",\"enable_integral\":\"290\",\"enable_deduction\":\"2.90\",\"cost_integral\":\"0\",\"cost_deduction\":\"0.00\"},\"surplus_term\":{\"allow\":true,\"desc\":\"\",\"enable_surplus\":\"63.00\",\"cost_surplus\":\"63.00\"},\"invoice_term\":{\"allow\":true,\"desc\":\"发票信息\",\"payee\":\"\",\"content\":\"\"},\"sub_orders\":[{\"postage\":\"0.00\",\"tips\":\"邮费没那么简单\",\"shipper\":\"优集品自营\",\"goods_list\":[{\"product_id\":34931,\"goods_name\":\"高丝植物无硅保湿沐浴露   粉色保湿花香\",\"goods_id\":27670,\"number\":1,\"attrs\":{\"color\":\"保湿\",\"size\":\"均码\"},\"goods_price\":\"58.00\",\"subtotal\":\"58.00\",\"thumbnail\":\"http://ujipin.ufile.ucloud.com.cn/559de0da5ec7b6e293b165cd7affd134?UCloudPublicKey=ucloudjiawoyong@ujipin.cn14466281690001077321672&Expires=1476512713&Signature=NLkNQ+cEq2vWqHEKGcygk75LT9k=&width=200&iopcmd=thumbnail&type=4\"}]}],\"verify_idcard\":false},\"time\":1475907913}\n";
        String head = aa.substring(0,1064);
        String end = aa.substring(1064,aa.length());

        System.out.println("head = "+head);

        System.out.println("end = "+end);    }

    static class Book{
        public Book(String name, float price) {
            this.name = name;
            this.price = price;
        }

        String name;
        float price;
    }
}
