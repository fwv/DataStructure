package com.fw.string.sort;

import com.fw.Tools.LogUtils;

/**
 * @Author fengwei
 * Created on 2016/12/19/0019.
 */
public class LSDStrSort {

    //every string which in a[] must have same length.
    public static void LSD(String[] a) {
        int R = 256;
        int N = a.length;
        String [] aux = new String [N];
        int W = a[0].length()-1;
        for (int D = W; D >= 0; D--) {

            int count[] = new int[R + 1];
            // statistics frequency
            for (int i = 0; i < N; i++) {
                count[a[i].charAt(D) + 1]++;
            }

            // transform frequency to index
            for (int m = 0; m < count.length-1; m++) {
                count[m+1] += count[m];
            }

            // category
            for (int j = 0; j < N; j++) {
                aux[count[a[j].charAt(D)]++] = a[j];
            }

            // write back
            for (int k = 0; k < N; k++) {
                a[k] = aux[k];
            }
        }

    }

    public static void main(String[] args) {
        String [] strs = {"aldol", "casld","zzzzz","abcde","alsol"};
        LSDStrSort.LSD(strs);
        for (int i = 0; i < strs.length; i++) {
            LogUtils.log.info(strs[i]);
        }
    }

}
