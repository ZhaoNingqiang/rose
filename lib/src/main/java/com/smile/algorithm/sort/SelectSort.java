package com.smile.algorithm.sort;

/**
 * @author: ningqiang
 * @time: 16/11/18
 * @description:简单选择排序
 */

public class SelectSort {
    public static void main(String args[]) {
        int a[] = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23, 34, 15, 35, 25, 53, 51};

        selectSort(a);
        for (int index : a) {
            System.out.println(index);
        }
    }

    /**
     * 基本思想：在要排序的一组数中，选出最小的一个数与第一个位置的数交换；
     * 然后在剩下的数当中再找最小的与第二个位置的数交换，如此循环到倒数第二个数和最后一个数比较为止。
     *
     * @param arr
     */
    private static void selectSort(int[] arr) {
        int position = 0;//标记被挖的坑
        for (int i = 0; i < arr.length; i++) {
            int j = i + 1;
            int temp = arr[i];//最小的值
            position = i;
            for (; j < arr.length; j++) {
                if (temp > arr[j]) {
                    temp = arr[j];
                    position = j;
                }
            }
            arr[position] = arr[i];
            arr[i] = temp;
        }

    }
}
