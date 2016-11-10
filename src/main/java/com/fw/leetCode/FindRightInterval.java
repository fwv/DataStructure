package com.fw.leetCode;

import java.util.Map;
import java.util.TreeMap;

/**
 * @Author fengwei
 * Created on 2016/11/10/0010.
 * 436. Find Right Interval
 * Given a set of intervals, for each of the interval i, check if there exists an interval j
 * whose start point is bigger than or equal to the end point of the interval i, which can be
 * called that j is on the "right" of i.
 *
 * For any interval i, you need to store the minimum interval j's index, which means that the
 * interval j has the minimum start point to build the "right" relationship for interval i. If
 * the interval j doesn't exist, store -1 for the interval i. Finally, you need output the stored
 * value of each interval as an array.
 *
 * Note:
 * 1.You may assume the interval's end point is always bigger than its start point.
 * 2.You may assume none of these intervals have the same start point.
 *
 * Example 3:
 * Input: [ [1,4], [2,3], [3,4] ]
 * Output: [-1, 2, -1]
 *
 * Explanation: There is no satisfied "right" interval for [1,4] and [3,4].
 * For [2,3], the interval [3,4] has minimum-"right" start point.
 *
 */
public class FindRightInterval {

     public class Interval {
             int start;
             int end;
             Interval() { start = 0; end = 0; }
             Interval(int s, int e) { start = s; end = e; }
     }

    public int[] findRightInterval(Interval[] intervals) {
        if (0 == intervals.length)return null;
        int[] res = new int[intervals.length];
        TreeMap<Integer, Integer> tree = new TreeMap<>();
        for (Interval interval : intervals) {
            tree.put(interval.start, interval.end);
        }
        for (int i = 0; i < intervals.length; i++) {
            int end = intervals[i].end;
            Map.Entry<Integer, Integer> entry = tree.ceilingEntry(end);
            res[i] = (entry != null) ? getIndexOfIntervals(intervals, entry.getKey()) : -1;
        }

        return res;
    }

    public int getIndexOfIntervals(Interval[] intervals, Integer key) {
        for (int i = 0; i < intervals.length; i++) {
            if (key == intervals[i].start)return i;
        }
        return -1;
    }
}
