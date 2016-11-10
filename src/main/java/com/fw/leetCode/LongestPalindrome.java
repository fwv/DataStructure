package com.fw.leetCode;

import com.fw.Tools.LogUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @Author fengwei
 * Created on 2016/11/10/0010.
 * 409. Longest Palindrome
 * Given a string which consists of lowercase or uppercase letters,
 * find the length of the longest palindromes that can be built with those letters.
 *
 * This is case sensitive, for example "Aa" is not considered a palindrome here.
 * Note:
 * Assume the length of given string will not exceed 1,010.
 *
 * Example:
 * Input:
 * "abccccdd"
 *
 * Output:
 * 7
 *
 * Explanation:
 * One longest palindrome that can be built is "dccaccd", whose length is 7.
 */
public class LongestPalindrome {

    // runtime : 27ms
    public int longestPalindrome(String s) {
        int result = 0;
        boolean addOne = false;
        char[] strs = s.toCharArray();
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        if (0 == strs.length)return 0;

        for(int i = 0; i < strs.length; i++) {
            if (!map.containsKey(strs[i])) {
                map.put(strs[i], 1);
            } else {
                map.put(strs[i], map.get(strs[i])+1);
            }
        }

        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() % 2 == 1) {
                addOne = true;
            }
            result += (0 == entry.getValue() % 2) ? entry.getValue() : entry.getValue() -1;
        }

        return addOne ? result+1 : result;
    }

    // runtime : 20ms
    public int optimazationLongestPalindrome(String s) {
        char[] strs = s.toCharArray();
        int result = 0;
        if (0 == strs.length)return 0;

        HashSet<Character> set = new HashSet<Character>();

        for (int i = 0; i < strs.length; i++) {
            if (set.contains(strs[i])) {
                set.remove(strs[i]);
                result += 2;
            } else {
                set.add(strs[i]);
            }
        }

        return set.size() == 0 ? result : result +1;
    }

    public static void main(String[] args) {
        LongestPalindrome ld = new LongestPalindrome();
        LogUtils.log.info(ld.longestPalindrome("abccccdd"));
        LogUtils.log.info(ld.optimazationLongestPalindrome("abccccdd"));
    }
}
