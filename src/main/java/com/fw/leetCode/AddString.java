package com.fw.leetCode;

import com.fw.Tools.LogUtils;

/**
 * Created by fengwei on 2016/10/18.
 * Leetcode415. Add String
 * Given two non-negative numbers num1 and num2 represented as string, return the sum of num1 and num2.
 * 1.The length of both num1 and num2 is < 5100.
 * 2.Both num1 and num2 contains only digits 0-9.
 * 3.Both num1 and num2 does not contain any leading zero.
 * 4.You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */
public class AddString {
    public String addStrings(String num1, String num2) {
        char[] nums1 = num1.toCharArray();
        char[] nums2 = num2.toCharArray();
        int i = nums1.length;
        int j = nums2.length;
        int len = (i > j ? i : j) + 1;
        char[] result = new char[len];
        int flag = len;
        while(i > 0 || j > 0)  {
            int temp = (i-1 < 0 ? 0 : toInt(nums1[i-1])) + (j - 1 < 0 ? 0 : toInt(nums2[j-1]));
            int temp1 = (toInt(result[flag - 1]) +  temp);
            result[flag-1] = toChar(temp1 % 10);
            if (temp1 >= 10)
            result[flag-2] = toChar((int)(temp1 / 10));
            i--;
            j--;
            flag--;
        }
        String str = "";
        for (int k = 0; k < result.length; k++) {
            str += result[k];
        }
        return str.trim();
    }

    public int toInt(char i) {
        if (i == '\u0000')return 0;
        return Integer.parseInt(String.valueOf(i));
    }

    // int 类型与 char类型之间ascii差值为48
    public char toChar(int i) {return (char)(i + 48);}

    public static void main(String[] args) {
        AddString as = new AddString();
        LogUtils.log.info(as.addStrings("999999999", "265456651564654135456156"));
    }
}
