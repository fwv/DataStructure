package com.fw.leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author fengwei
 * Created on 2016/11/11/0011.
 * 412. Fizz Buzz
 * Write a program that outputs the string representation of numbers from 1 to n.
 * n = 15,
 * Return:
        [
        "1",
        "2",
        "Fizz",
        "4",
        "Buzz",
        "Fizz",
        "7",
        "8",
        "Fizz",
        "Buzz",
        "11",
        "Fizz",
        "13",
        "14",
        "FizzBuzz"
        ]
 */
public class FizzBuzz {

    public List<String> fizzBuzz(int n) {
        List<String> res = new ArrayList();
        for(int i = 1; i <= n; i++) {
            if(i % 3 == 0 || i % 5 == 0) {
                res.add(convert3or5(i));
                continue;
            }
            res.add(i+"");
        }
        return res;
    }

    public String convert3or5(int m) {
        if(m % 3 == 0 && m % 5 == 0)return "FizzBuzz";
        return (m % 3 == 0)?  "Fizz" : "Buzz";
    }

}
