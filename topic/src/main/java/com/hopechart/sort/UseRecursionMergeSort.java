package com.hopechart.sort;

/**
 * @author wang
 * @date 2018/3/22.
 * 归并排序的递归和非递归实现（C代码） - petercao - 博客园
 * https://www.cnblogs.com/bluestorm/archive/2012/09/06/2673138.html
 */

public class UseRecursionMergeSort {

    public static void main(String[] args) {
        int[] array = {1234, 99, 21, 4, 5, 15, 8, 21, 1, 54, -1, 0, -5, 43532, 0, -1, 327327, -1010, 2, 3, 5, 4, 3, 9, 78, 55, -999, 11, 0, 3, 4, 9, 0, 12, -9};
//        int[] array = {2, 4, 6, 8, 1, 3, 4, 7};
        mergeSort(array, 0, array.length - 1);
        p(array);
    }

    public static void merge(int[] array, int low, int mid, int high) {
        int len = array.length;
        int[] temp = new int[len];
        int left = low;
        int right = mid + 1;
        for (int i = low; i <= high; i++) {
            if (left > mid) {  //左数组元素已全比较完
                temp[i] = array[right++];
            } else if (right > high) { //右数组元素已全比较完
                temp[i] = array[left++];
            } else if (array[right] < array[left]) { //右数组元素小于左数组
                temp[i] = array[right++];
            } else {  //右数组元素大于等于左数组
                temp[i] = array[left++];
            }
        }
        for (int i = low; i <= high; i++) {
            array[i] = temp[i];
        }
    }

    public static void mergeSort(int[] array, int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            mergeSort(array, low, mid);
            mergeSort(array, mid + 1, high);
            merge(array, low, mid, high);
        }
    }

    public static void p(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + ",");
        }
        System.out.println();
    }

}
