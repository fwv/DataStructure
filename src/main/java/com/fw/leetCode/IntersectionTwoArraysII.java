package com.fw.leetCode;

import com.fw.Tools.LogUtils;

import java.util.ArrayList;

/**
 * Created by fengwei on 2016/11/13.
 */
public class IntersectionTwoArraysII {

    public int[] intersect(int[] nums1, int[] nums2) {
        ArrayList<Integer> list = new ArrayList<>();
        insertSort(nums1);
        insertSort(nums2);
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                if (nums1[i] == nums2[j]) {
                    list.add(nums1[i]);
                    i++;
                    j++;
                }
            }
        }

        return null;
    }

    public void insertSort(int[] nums) {
        int len = nums.length;
        for(int i = 1; i < len; i++) {
            int temp = nums[i];
            int index = i;
            while(index > 0 && nums[index-1] > temp) {
                nums[index] = nums[index -1];
                index--;
            }
            nums[index] = temp;
        }
    }

    public static void main(String[] args) {
        int[] a = {2,5,3,0,1};
        IntersectionTwoArraysII in = new IntersectionTwoArraysII();
        in.insertSort(a);
        for (int i = 0; i < a.length; i++) {
            LogUtils.log.info(a[i]);
        }
    }

}
