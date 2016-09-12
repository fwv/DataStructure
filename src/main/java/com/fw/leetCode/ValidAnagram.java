package com.fw.leetCode;

import java.util.HashMap;
import java.util.logging.Logger;

public class ValidAnagram {

    public static Logger log = Logger.getLogger(ValidAnagram.class.getName());

    public boolean isAnagram(String s, String t) {
        if(null == s && null == t)return true;
        if(s.length() != t.length())return false;
        char[] c1 = s.toCharArray();
        char[] c2 = t.toCharArray();
        HashMap<Integer, Integer> h1 = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> h2 = new HashMap<Integer, Integer>();
        for (int i = 0; i < c1.length; i++) {
            int key = c1[i];
            if (!h1.containsKey(key)) {
                h1.put(key, 1);
            }else {
                int val = h1.get(key);
                h1.put(key, ++val);
            }
        }
        for (int i = 0; i < c2.length; i++) {
            int key = c2[i];
            if (!h2.containsKey(key)) {
                h2.put(key, 1);
            }else {
                int val = h2.get(key);
                h2.put(key, ++val);
            }
        }
        for (Integer key : h1.keySet()) {
            if(null == h1.get(key) || null == h2.get(key)) {
                return false;
            }
            int r1 = h1.get(key);
            int r2 = h2.get(key);
            if (r1 == r2) {
                continue;
            }else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
/*        String s = "aacc";
        String s1 = "ccac";
        ValidAnagram va = new ValidAnagram();
        boolean r = va.isAnagram(s, s1);
        log.info(r+"");*/
        String s1 = "ss";
        String s2 = "sa";

        HashMap<Integer,Integer> h1 = new HashMap<>();
        HashMap<Integer,Integer> h2 = new HashMap<>();
        Integer l1 = s1.length();
        Integer l2 = s2.length();
        h1.put(1, s1.length());
        h2.put(1, s2.length());
        Integer a = h1.get(1);
        Integer b = h2.get(1);
        log.info((a==b) + "");
    }
}
