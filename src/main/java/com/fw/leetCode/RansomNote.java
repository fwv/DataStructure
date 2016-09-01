package com.fw.leetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Leetcode 383. Ransom Note
 */
public class RansomNote {
    public static Logger log = Logger.getLogger(RansomNote.class.getName());

    public boolean canConstruct(String ransomNote, String magazine) {
        char[] a = ransomNote.toCharArray();
        char[] b = magazine.toCharArray();
        List<Character> B = new ArrayList<>();
        for(int i = 0; i < b.length; i++) {
            B.add(b[i]);
        }
        B.remove("a");
        for(int i = 0; i < a.length; i++) {
            char s = a[i];
            if(!B.contains(s))return false;
            B.remove(new Character(a[i]));
        }
        return true;
    }

    public static void main(String[] args) {
        RansomNote r= new RansomNote();
        String s = r.canConstruct("aa", "aba")? "y" : "n";
        log.info(s);
    }
}
