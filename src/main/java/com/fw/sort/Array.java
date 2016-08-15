package com.fw.sort;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Administrator on 2016/8/11/0011.
 */
public class Array {

    private long[] a;

    private int length;

    private int nElems;

    private static Logger log = Logger.getLogger(Array.class.getName());

    public Array(int length) {
        this.length = length;
        a = new long[this.length];
        this.nElems = 0;
    }

    public void insert(long temp) {
        this.a[nElems++] = temp;
    }

    public void show() {
        log.log(Level.INFO,"==========Show Array==========");
        String str = "Array: ";
        for(int i = 0; i < nElems; i++) {
            str += " "+a[i];
        }
        log.log(Level.WARNING, str);
        log.log(Level.INFO, "==========Show Array==========");
    }

    public void swap(int i, int j) {
        a[i] = a[i] ^ a[j];
        a[j] = a[i] ^ a[j];
        a[i] = a[i] ^ a[j];
    }

    public void bubbleFromRightSort() {
       for(int i = nElems-1; i > 1; i--) {
            for(int j = 0; j < i; j++) {
                if(a[j] > a[j+1]) {
                    swap(j, j+1);
                }
            }
       }
    }

    public void bubbleFromLeftSort() {
        for(int i = 0; i < nElems-2; i++) {
            for(int j = nElems-1; j > i; j--) {
                if(a[j] < a[j-1]) {
                 swap(j, j-1);
                }
            }
        }
    }

    public void selectionSort() {
       for(int i = 0; i < nElems-1; i++) {
           for(int j = i+1; j < nElems; j++) {
                if(a[j] < a[i]) {
                    swap(j, i);
                }
           }
       }
    }
    // 双向冒泡排序
    public void doubleDirectBubbleSort() {
        int outLeft = 0;
        int outRight = nElems-1;
        for( ; outLeft < outRight; outLeft++, outRight--) {
            for(int in = outLeft; in < outRight; in++) {
                if(a[in] > a[in+1]) {
                    swap(in, in+1);
                }
            }
            for(int in = outRight; in > outLeft; in--) {
                if(a[in] < a[in-1]) {
                    swap(in, in-1);
                }
            }
        }
    }

    public void insertSort() {
        for(int i = 1; i < nElems; i++) {
            int j = i;
            long temp = a[j];
            while(j > 0 && a[j-1] > temp) {
                a[j] = a[j-1];
                j--;
            }
            a[j] = temp;
        }
     }

    public static void main(String[] args) {
        Array a = new Array(100);
        for (int i = 0; i < 15; i++)
        {
            long temp = (long)(Math.random()*(15-1));
            a.insert(temp);
        }

        a.show();
        a.doubleDirectBubbleSort();
        a.show();
    }

}
