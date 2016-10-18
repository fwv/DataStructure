package com.fw.leetCode;

import com.fw.Tools.LogUtils;

/**
 * Created by fengwei on 2016/10/18.
 */
public class AddString {
    public String addStrings(String num1, String num2) {
        char[] nums1 = num1.toCharArray();
        char[] nums2 = num2.toCharArray();
        int i = nums1.length;
        int j = nums2.length;
        char[] result;
        while(i > 0 && j > 0)  {

            i--;
            j--;
        }
        return null;
    }

    public int toInt(char i) {
        return Integer.parseInt(String.valueOf(i));
    }

    public static void main(String[] args) {
        char s = '2';
        char k ='3';
        int i = s + k;
    }
}
