package com.hopechart.sort;

/**
 * @author wang
 * @date 2018/3/15.
 * 描述：以中间值，作为基点。
 * 时间复杂度：
 * 空间复杂度：
 * 稳定性：不稳定。
 */

public class NewFastSort {

    public static void main(String[] args) {
        int[] array = {1234, 99, 21, 4, 5, 15, 8, 21, 1, 54, -1, 0, -5, 43532, 0, -1, 327327, -1010, 2, 3, 5, 4, 3, 9, 78, 55, -999, 11, 0, 3, 4, 9, 0, 12, -9};
//        int[] array = {5, 3, 9, 7, 4, 2, 3, 1};
        fastSort(array, 0, array.length - 1);
        p(array);
    }

    public static void fastSort(int[] array, int l, int r) {
        System.out.println("============> fastSort :  " + l + " --> " + r);
        if (l < r) {
            int left = l;
            int right = r;
            int mid = left + (right - left) / 2;
            System.out.println("mid = " + mid);
            int midValue = array[mid];
            System.out.println("midValue = " + midValue);
            while (left < right) {
                while (left < right && array[right] > midValue) {
                    right--;
                }
                while (left < right && array[left] < midValue) {
                    left++;
                }
                if (left < right) {
                    int temp = array[left];
                    array[left] = array[right];
                    array[right] = temp;
                    right--;
                    left++;
                }
            }
            System.out.println("交换后 left = " + left);
            System.out.println("交换后 right = " + right);
            p(array);
            if (array[left] == midValue) {
                System.out.println("中位数相等");
                fastSort(array, l, left - 1);
                fastSort(array, left + 1, r);
            } else if (array[left] < midValue) {
                System.out.println("中位数偏小 ，放左边");
                fastSort(array, l, left);
                fastSort(array, left + 1, r);
            } else {
                System.out.println("中位数偏大 ，放右边");
                fastSort(array, l, left - 1);
                fastSort(array, left, r);
            }
        }
    }

    public static void p(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + ",");
        }
        System.out.println();
    }
}
