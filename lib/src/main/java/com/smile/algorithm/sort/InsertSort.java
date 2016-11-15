package com.smile.algorithm.sort;

/**
 * @author: ningqiang
 * @time: 16/11/11
 * @description: 直接插入排序
 */

public class InsertSort {
    public static void main(String args[]) {
        int a[] = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23, 34, 15, 35, 25, 53, 51};

        insertSort(a);
        for (int index : a) {
            System.out.println(index);
        }
    }

    /**
     * 直接插入排序
     * 基本思想：在要排序的一组数中，假设前面(n-1)[n>=2] 个数已经是排
     * 好顺序的，现在要把第n个数插到前面的有序数中，使得这n个数
     * 也是排好顺序的。如此反复循环，直到全部排好顺序。
     * @param arr
     */
    private static void insertSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int temp = arr[i];
            int j = i - 1;
            for (; j >= 0 && arr[j] > temp; j--) {
                arr[j + 1] = arr[j];//将大于temp的值整体后移一个单位
            }
            arr[j + 1] = temp;
        }
    }
}
