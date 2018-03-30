package com.hopechart.sort;

/**
 * @author wang
 * @date 2018/3/30.
 */

public class NoRecursionMergeSort {

    public static void main(String[] args) {
//        int[] array = {1234, 99, 21, 4, 5, 15};
        String result = "-1010,-999,-9,-5,-1,-1,0,0,0,0,1,2,3,3,3,4,4,4,5,5,8,9,9,11,12,15,21,21,54,55,78,99,1234,43532,327327";
        int[] array = {1234, 99, 21, 4, 5, 15, 8, 21, 1, 54, -1, 0, -5, 43532, 0, -1, 327327, -1010, 2, 3, 5, 4, 3, 9, 78, 55, -999, 11, 0, 3, 4, 9, 0, 12, -9};
//        int[] array = {8, 7, 6, 5, 4, 3, 2, 1};
//        int[] array = {2, 4, 6, 8, 1, 3, 4, 7};
        mergeSort(array);
        p(array);
    }

    /**
     * 第二版
     * @param array
     */
    public static void mergeSort(int[] array) {
        int len = array.length;
        int leftMin, leftMax, rightMin, rightMax;
        int tag;
        int[] temp = new int[len];
        for (int i = 1; i < len; i *= 2) {
            for (leftMin = 0; leftMin < len - i; leftMin = rightMax) {
                rightMin = leftMax = leftMin + i;
                rightMax = leftMax + i;

                if (rightMax > len) {
                    rightMax = len;
                }

                tag = 0;
                // 第一步
                while (leftMin < leftMax && rightMin < rightMax) {
                    temp[tag++] = array[rightMin] < array[leftMin] ? array[rightMin++] : array[leftMin++];
                }

                // 第二步
                while (leftMin < leftMax) {
                    // 第一步如果 rightMin == rightMax，那么 leftMin < leftMax，从left复制到right
                    array[--rightMin] = array[--leftMax];
                }

                // 第三步
                while (tag > 0) {
                    // 第一步如果 rightMin == rightMax，经由第二步，rightMin = next
                    // 第一步如果 rightMin < rightMax,那么 rightMin 之后的就不用动
                    array[--rightMin] = temp[--tag];
                }
            }
        }
    }

    /**
     * 第一版
     * @param array
     */
    public static void mergesort(int[] array) {
        int len = array.length;
        int leftMin = 0, leftMax, rightMin, rightMax = 0;
        for (int i = 1; i < array.length; i *= 2) {
            System.out.println("=====================> 步长 i = " + i);
            for (leftMin = 0; leftMin < len - i; leftMin = rightMax + 1) {
                leftMax = leftMin + i - 1;
                rightMin = leftMax + 1;
                rightMax = rightMin + i - 1;

                if(leftMax >= len){
                    leftMax = len - 1;
                }
                if (rightMin >= len) {
                    rightMin = len - 1;
                }
                if (rightMax >= len) {
                    rightMax = len -1;
                }
                System.out.println("leftMin = " + leftMin + "     leftMax = " + leftMax + "     rightMin = " + rightMin + "   rightMax = " + rightMax);
                int[] temp = new int[rightMax - leftMin + 1];
                System.out.println("temp.length = " + temp.length);
                for (int j = 0; j < temp.length; j++) {
                    if (leftMin > leftMax) {  //左数组元素已全比较完
                        temp[j] = array[rightMin++];
                    } else if (rightMin > rightMax) { //右数组元素已全比较完
                        temp[j] = array[leftMin++];
                    } else if (array[rightMin] < array[leftMin]) { //右数组元素小于左数组
                        temp[j] = array[rightMin++];
                    } else {  //右数组元素大于等于左数组
                        temp[j] = array[leftMin++];
                    }
                    System.out.println("打印temp");
                    p(temp);
                }
                for (int j = 0; j < temp.length; j++) {
                    array[leftMax - i + 1 + j] = temp[j];
                }
                System.out.println("打印array");
                p(array);
                System.out.println("结束rightMax = " + rightMax);
            }
            p(array);
        }
    }

    public static void p(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + ",");
        }
        System.out.println();
    }

}
