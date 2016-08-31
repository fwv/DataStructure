package com.fw.leetCode;

import java.util.*;
import java.util.logging.Logger;

/**
 * Leetcode 347. K Frequence Elements
 *
 * Given a non-empty array of integers, return the k most frequence elements.
 * For Example:
 * Given: [2,3,3,2,1,2,2] and k = 2, return [2, 3]
 * @author fw
 * Summary : Since the Algorithm's time complexity must be better than O(nlogn),
 * traverse all array need n complexity, maintain heap invariant need logn
 * use priority queue to solve problem.
 */
public class KFrequenceElements {

    public static Logger log = Logger.getLogger(KFrequenceElements.class.getName());

    public List<Integer> topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.containsKey(nums[i]) ? map.get(nums[i]) + 1 : 1);
        }
        Comparator<Map.Entry<Integer, Integer>> comparator = new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                int result =o2.getValue() - o1.getValue();
                return result;
            }
        };
        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue(k, comparator);
        queue.addAll(map.entrySet());
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < k; i++) {
            Map.Entry<Integer, Integer> entry = queue.poll();
            Integer temp = entry.getKey();
            result.add(temp);
        }
        return result;
    }

    public static void main(String[] args) {
        KFrequenceElements kfe = new KFrequenceElements();
        int[] temps = new int[]{2,2,2,3,3,1,2,2,2,3,3,3,3,3,3,5,6,7,8,9,32,32,14,52};
        List<Integer> list = new ArrayList<Integer>();
        list = kfe.topKFrequent(temps, 4);
        for (Integer i : list) {
            log.info(i+"");
        }
    }

}
