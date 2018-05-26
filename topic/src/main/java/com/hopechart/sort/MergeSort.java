package com.hopechart.sort;

/**
 * @author wang
 * @date 2018/4/3.
 */

public class MergeSort {

    public static void main(String[] args) {
        String result = "-1010,-999,-9,-5,-1,-1,0,0,0,0,1,2,3,3,3,4,4,4,5,5,8,9,9,11,12,15,21,21,54,55,78,99,1234,43532,327327";
        int[] array = {1234, 99, 21, 4, 5, 15, 8, 21, 1, 54, -1, 0, -5, 43532, 0, -1, 327327, -1010, 2, 3, 5, 4, 3, 9, 78, 55, -999, 11, 0, 3, 4, 9, 0, 12, -9};
        mergeSort(array);
        p(array);
    }

    public static void mergeSort(int[] array) {
        int len = array.length;
        int leftMin, leftMax, rightMin, rightMax;
        int tag;
        int[] temp = new int[len];
        for (int i = 1; i < len; i <<= 1) {
            for (leftMin = 0; leftMin < len; leftMin = rightMax) {
                rightMin = leftMax = (leftMin + i > len) ? len : leftMin + i;
                rightMax = (leftMax + i > len) ? len : leftMax + i;

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

    public static void p(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + ",");
        }
        System.out.println();
    }

    /**
     * 叶工第一次修改意见：
     * 1、for(int i=1;i<len; i*=2)中 i*=2可以改为 i<<=1,同时当 len=0x40000000及以上时存在 bug;
     * 2、for(leftMin=0;leftMin<len -i;leftMin=rightMax) 当 i! = 1 时最后一部分存在未归并的 bug;
     * 3、array[--rightMin]=array[--leftMax]复制左边剩余部分错误的 bug,
     *  应该: array[tag++] = array[leftMin++] ,若这样处理则后一个循环也会出问题;
     * 4、应该使用数组交换来减少拷贝次数。
     */

    /**
     * 叶工第二次修改意见：
     *  1、可以做一个归并函数，若返回值为 true 表示已归并到 ADest，否则已经排序；
     *  private static bool DoMerge(int[] ASouce, int[] ADest, int AFrom, int ACount);
     * 2、利用上面 DoMerge 函数，可以交换 source 和 dest 数组项。
     */
}



