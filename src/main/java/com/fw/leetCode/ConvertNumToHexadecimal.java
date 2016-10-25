package com.fw.leetCode;

import com.fw.Tools.LogUtils;

/**
 * @Author fengwei
 * Created on 2016/10/25/0025.
 * Leetcode 405. Convert a Number to Hexadecimal
 * Given an integer, write an algorithm to convert it to hexadecimal. For negative integer, two’s complement method is used.
 *
 * Example 1:
 * Input:
 * 26
 * Output:
 * 1a
 */
public class ConvertNumToHexadecimal {
    public String toHex(int num) {
        char[] ch = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f','g'};
        StringBuffer st = new StringBuffer();
        if (num == 0)return "0";
        while(num != 0) {
            int temp = 15 & num;
            st.append(ch[temp]);
            num = num >>> 4;
        }
        return st.reverse().toString();
    }

    public String toBinary(int num) {
        StringBuffer st = new StringBuffer();
        for (int i = 31; i >= 0; i--) {
            int temp = num & (1 << i);
            if (0 != temp) {
                st.append(1);
            }else {
                st.append(0);
            }
        }
        return st.toString();
    }

    public static void main(String[] args) {
        ConvertNumToHexadecimal ch = new ConvertNumToHexadecimal();
        LogUtils.log.info(ch.toBinary(-2));
        LogUtils.log.info(ch.toHex(26));
    }
}
