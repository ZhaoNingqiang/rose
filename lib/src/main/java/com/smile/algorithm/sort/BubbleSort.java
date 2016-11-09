package com.smile.algorithm.sort;

/**
 * @Description:.快速排序
 * @Author: ZhaoNingqiang
 * @Time 2016/11/08 下午9:08
 */

public class BubbleSort {
    public static void main(String args[]){
        int a[]={49,38,65,97,76,13,27,49,78,34,12,64,5,4,62,99,98,54,56,17,18,23,34,15,35,25,53,51};
        bubbleSort(a);
        for (int i : a){
            System.out.println(i);
        }

    }

    /**
     * √
     * 基本思想：在要排序的一组数中，对当前还未排好序的范围内的全部数，自上而下对相邻的两个数依次进行比较和调整，
     * 让较大的数往下沉，较小的往上冒。即：每当两相邻的数比较后发现它们的排序与排序要求相反时，就将它们互换。
     * @param data
     */
    private static void bubbleSort(int[] data){

        for (int i = 0 ; i< data.length-1; i++){

            for (int j = 0; j<data.length - 1- i;j++){
                if (data[j] > data[j+1]){
                    int temp = data[j];
                    data[j] = data[j+1];
                    data[j+1] = temp;
                }
            }
        }
    }
}
