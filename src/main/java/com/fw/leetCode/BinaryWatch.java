package com.fw.leetCode;

import com.fw.Tools.LogUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author fengwei
 * Created on 2016/11/14/0014.
 * 401. Binary Watch
 * A binary watch has 4 LEDs on the top which represent the hours (0-11),
 * and the 6 LEDs on the bottom represent the minutes (0-59).
 *
 * Example:
 * Input: n = 1
 * Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
 */
public class BinaryWatch {

    public List<String> readBinaryWatch(int num) {
        ArrayList<String> res = new ArrayList<>();
        for (int h = 0; h < 12; h++) {
            for (int m = 0; m < 60; m++) {
                if (Integer.bitCount(h * 64 + m) == num) {
                    res.add(String.format("%d:%02d", h, m));
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        //LogUtils.log.info(Integer.bitCount(-1));
        int a = 600;
        int b = 3000;
        LogUtils.log.info((float)a/(float) b);
    }
}
