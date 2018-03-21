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
        if (l < r) {
            int left = l;
            int right = r;
            int midValue = array[left + (right - left) / 2];
            do {
                while (array[right] > midValue) {
                    right--;
                }
                while (array[left] < midValue) {
                    left++;
                }
                if (left <= right) {
                    int temp = array[left];
                    array[left] = array[right];
                    array[right] = temp;
                    right--;
                    left++;
                }
            } while (left <= right);
            fastSort(array, l, right);
            fastSort(array, left, r);
        }
    }

    public static void p(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + ",");
        }
        System.out.println();
    }
    /**
     * 叶工第一次修改意见：
     *  1、mid 与 midValue 变量可以合并；
     *  2、while (left < right) 应该用 do {..} while (left <= right)；
     *  3、if (left < right) 处理没有按我讲的去做；
     *  4、if (array[left] == midValue) 由于上一条没有处理好导致递归处理复杂了。
     */

}
