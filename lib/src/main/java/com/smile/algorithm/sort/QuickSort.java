package com.smile.algorithm.sort;

/**
 * @author: ningqiang
 * @time: 16/11/9
 * @description:
 */

public class QuickSort {

    public static void main(String args[]) {
        int a[] = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23, 34, 15, 35, 25, 53, 51};

        quick_sort(a, 0, a.length - 1);
        for (int index : a) {
            System.out.println(index);
        }
    }

    /**
     * 快速排序
     * 基本思想：选择一个基准元素,通常选择第一个元素或者最后一个元素,
     * 通过一趟扫描，将待排序列分成两部分,一部分比基准元素小,一部分大于等于基准元素,
     * 此时基准元素在其排好序后的正确位置,然后再用同样的方法递归地排序划分的两部分。
     * @param arr 要排序的数据
     * @param low 低位 index
     * @param heigt 高位 index
     */
    private static void quick_sort(int[] arr, int low, int heigt) {
        if (low < heigt) {
            //Swap(s[l], s[(l + r) / 2]); //将中间的这个数和第一个数交换 参见注1
            int i = low, j = heigt, x = arr[low];
            while (i < j) {
                while (i < j && arr[j] >= x) // 从右向左找第一个小于x的数
                    j--;
                if (i < j)
                    arr[i++] = arr[j];

                while (i < j && arr[i] < x) // 从左向右找第一个大于等于x的数
                    i++;
                if (i < j)
                    arr[j--] = arr[i];
            }
            arr[i] = x;
            quick_sort(arr, low, i - 1); // 递归调用
            quick_sort(arr, i + 1, heigt);
        }
    }

}
