package com.fw.string.sort;

import com.fw.Tools.LogUtils;

/**
 * @Author fengwei
 * Created on 2016/12/29/0029.
 */
public class MSDStrSort {

    public static int R = 256;
    public static String[] aux;

    public static int CharAt(String a, int d) {
        return (d >= a.length()) ? -1 : a.charAt(d);
    }

    public static void MSD(String[] a) {
        R = 256;
        int N = a.length;
        aux = new String[N];
        msdSort(a, 0, N-1, 0);
    }

    public static void msdSort(String[] a, int lo, int hi, int d) {
        if (lo >= hi)return;
        int[] count = new int[R+2];

        //统计频率
        for (int i = lo; i <= hi; i++) {
            count[CharAt(a[i], d) + 2]++;
        }
        //将统计转化为索引
        for (int r = 0; r < R + 1; r++) {
            count[r+1] += count[r];
        }
        //数据分类
        for (int i = lo; i <= hi; i++) {
            int temp = CharAt(a[i], d);
            int index = count[temp + 1];
            aux[index] = a[i];
            count[temp + 1]++;
        }
        //回写
        for (int i = lo; i <= hi; i++) {
            a[i] = aux[i - lo];
        }
        //递归排序子数组
        for (int r = 0; r < R; r++) {
            msdSort(a, lo + count[r], lo + count[r + 1] - 1, d + 1);
        }
    }

    public static void main(String[] args) {
        String [] strs = {"acc", "abc","cab","bab"};
        MSDStrSort.MSD(strs);
        for (int i = 0; i < strs.length; i++) {
            LogUtils.log.info(strs[i]);
        }
    }
}
