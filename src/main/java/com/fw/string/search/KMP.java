package com.fw.string.search;

import com.fw.Tools.LogUtils;

/**
 * @Author fengwei
 * Created on 2017/1/3/0003.
 * comprehensive : KMP算法其实就是针对每一个pattern串模拟构建一个
 *                 确定有穷状态机（dfa）并用数组保存起来，查找的过
 *                 程就是把样本串丢到状态机中，如能到达最后一个状态
 *                 则表示存在匹配串。
 *
 *                 j：可以理解为状态机的状态
 *                 X：可以理解为重启状态，比如“ABABX”下查找ABABAC
 *                    串时则扫描“BAB”，得到X为2状态。
 *
 *                 核心：为每一个pattern构建状态机数组
 */
public class KMP {
    /** the size of source alphabet */
    private int R = 256;
    private int[][] dfa;
    /** the size of source txt string */
    private int N;
    /** the size of pattern string */
    private int M;

    private int search(String txt, String pattern) {
        DFASimulate(pattern);
        N = txt.length();
        int j = 0;
        int i = 0;
        for(; i < N && j < M; i++) {
            j = dfa[txt.charAt(i)][j];
        }
        if (j == M) {
            return i - M;
        } else {
            return N;
        }
    }

    private void DFASimulate(String pattern) {
        M = pattern.length();
        dfa = new int[R][M];
        // initial dfa[][]
        dfa[pattern.charAt(0)][0] = 1;
        for (int X = 0, j = 1; j < M; j++) {
            // matching failed situation
            for (int c = 0; c < R; c++) {
                dfa[c][j] = dfa[c][X];
            }
            // matching success situation
            dfa[pattern.charAt(j)][j] = j + 1;
            // update restart point X
            X = dfa[pattern.charAt(j)][X];
        }
    }


    public static void main(String[] args) {
        KMP kmp = new KMP();
        int result = kmp.search("BCBAABACAABABACAA", "ABABAC");
        LogUtils.log.info(result);
    }
}