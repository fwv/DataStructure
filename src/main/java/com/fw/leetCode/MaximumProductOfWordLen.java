package com.fw.leetCode;

import com.fw.Tools.LogUtils;

/**
 * Created by fengwei on 2016/10/24.
 */
public class MaximumProductOfWordLen {
    public int maxProduct(String[] words) {
        int len  = words.length;
        int[] mask = new int[len];
        if (len <= 1)return 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                mask[i] |= 1 << (words[i].charAt(j) - 'a');
            }
        }

        int max = 0;
        for (int i = 0; i <len; i++){
            for (int j = i+1; j < len; j++){
                if ((mask[i] & mask[j]) == 0) {
                    max = Math.max(max, words[i].length() * words[j].length());
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        MaximumProductOfWordLen mx = new MaximumProductOfWordLen();
        String[] test = {"abcw", "baz", "foo", "bar", "xtfn", "abcdef"};
        LogUtils.log.info(mx.maxProduct(test));
    }
}
