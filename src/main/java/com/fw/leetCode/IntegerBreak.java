package com.fw.leetCode;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/9/5/0005.
 */
public class IntegerBreak {

    public static int integerBreak(int n) {
        if(2 == n)return 1;
        if(3 == n)return 2;
        if(4 == n)return 4;
        if(5 == n)return 6;
        int k = n % 3;
        int result = 0;
        int m = n/3;
        switch(k) {
            case 0 :
                result = (int)Math.pow(3, m);
                break;
            case 1 :
                result = (int)Math.pow(3, m-1)*4;
                break;
            case 2:
                result = (int)Math.pow(3, m)*2;
                break;
        }
        return result;

    }
}
