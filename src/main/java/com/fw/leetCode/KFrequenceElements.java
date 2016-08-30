package com.fw.leetCode;

import java.util.*;
import java.util.logging.Logger;

/**
 * Created by fengwei on 2016/8/30.
 */
public class KFrequenceElements {

    public static Logger log = Logger.getLogger(KFrequenceElements.class.getName());

    public List<Integer> topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.containsKey(nums[0]) ? map.get(nums[0]) + 1 : 1);
        }

        Comparator<Map.Entry<Integer, Integer>> comparator = new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o1.getValue() <= o2.getValue() ? 0 : 1;
            }
        };

        Queue<Map.Entry<Integer, Integer>> queue = new PriorityQueue(k, comparator);
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
/*        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(2, 5);
        map.put(2, 2);
        log.info(map.size()+" ");
        log.info(map.get(2)+"");*/
        KFrequenceElements kfe = new KFrequenceElements();
        int[] temps = new int[]{2,3,2,3,3,4};
        List<Integer> list = new ArrayList<Integer>();
        list = kfe.topKFrequent(temps, 2);
        for (Integer i : list) {
            log.info(i+"");
        }
    }

}
