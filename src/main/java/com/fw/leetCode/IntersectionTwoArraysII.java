package com.fw.leetCode;

import com.fw.Tools.LogUtils;

import java.util.ArrayList;

/**
 * Created by fengwei on 2016/11/13.
 * 350. Intersection of Two Arrays II
 * Given two arrays, write a function to compute their intersection.
 *
 * Example:
 * Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].
 *
 */
public class IntersectionTwoArraysII {

    public int[] intersect(int[] nums1, int[] nums2) {
        ArrayList<Integer> list = new ArrayList<>();
        insertSort(nums1);
        insertSort(nums2);
        for(int i = 0, j = 0; i < nums1.length && j < nums2.length; ) {
            if (nums1[i] == nums2[j]) {
                list.add(nums1[i]);
                i++;
                j++;
            } else if(nums1[i] > nums2[j]) {
                j++;
            } else {
                i++;
            }

        }
        int[] a = new int[list.size()];
        for (int i = 0; i < list.size(); i ++) {
            a[i] = list.get(i);
        }
        return a;
    }

    public void insertSort(int[] nums) {
        int len = nums.length;
        for (int i = 1; i < len; i++) {
            int temp = nums[i];
            int index = i;
            while(index > 0 && nums[index -1] > temp) {
                nums[index] = nums[index-1];
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
