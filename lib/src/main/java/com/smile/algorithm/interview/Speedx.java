package com.smile.algorithm.interview;

/**
 * @author: ningqiang
 * @time: 16/11/23
 * @description: 野兽骑行的面试题
 * 有一组数，已知前两个数的值为0和1，其余后面的数为前面两个相邻数字值之和，求第index个数字的值？
 * 通过对递归和遍历的比较发现两种算法的差异还是很大的，如果index的值大一些，递归的深度就很大，效率就很慢，
 * 遍历算法相对递归来说就会好很多。
 * 通过实验，在获取index从0到50的过程中，递归用时73034毫秒。index的值越大，后面用到的时间就很多，递归的缺点就特别明显。
 * 遍历算法中，用到的时间为1毫秒。
 * 比较后，两种实现的性能差异还是蛮大的。
 */

public class Speedx {
    public static void main(String[] args) {
        Speedx speedx = new Speedx();
        long startTime1 = System.currentTimeMillis();
        for (int i = 1; i < 50; i++) {
            int data = speedx.calculateData(i);
            System.out.println("方法一 data = " + data + " i = " + i);
        }
        long endTime1 = System.currentTimeMillis();
        System.out.println("方法一 使用时间 ： "+(endTime1 - startTime1));


        long startTime2 = System.currentTimeMillis();
        for (int i = 1; i < 50; i++) {
            int data = speedx.calculateData2(i);
            System.out.println("方法二 data = " + data + " i = " + i);
        }
        long endTime2 = System.currentTimeMillis();
        System.out.println("方法二 使用时间 ： "+(endTime2 - startTime2));
    }


    /**
     * 第一种方式，使用递归
     *
     * @param index
     * @return
     */
    private int calculateData(int index) {
        if (index < 1) {
            throw new IllegalArgumentException("index must >= 1");
        }
        int a = 0, b = 0, result = 0;
        if (index == 1) {
            a = 0;
            b = 0;
            result = a + b;
        } else if (index == 2) {
            a = 0;
            b = 1;
            result = a + b;
        } else {
            a = calculateData(index - 1);
            b = calculateData(index - 2);
            result = a + b;
        }
        return result;
    }

    /**
     * 第二种方式：使用遍历
     *
     * @param index
     * @return
     */
    private int calculateData2(int index) {
        int a = 0, b = 1, restult = 0;
        if (index == 1) {
            return a;
        } else if (index == 2) {
            return b;
        } else {
            for (int i = 2; i < index; i++) {
                restult = a + b;
                a = b;
                b = restult;
            }
            return restult;
        }
    }
}
