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

    public void shellSort() {
        int out,in;
        long temp;
        int h = 1;
        while(h < nElems/3) {
            h = h * 3 + 1;
        }
        while(h > 0) {
            for(out = h; out < nElems; out++) {
                temp = a[out];
                in = out;
                while(in > h-1 && a[in - h] > temp) {
                    a[in] = a[in -h];
                    in -= h;
                }
                a[in] = temp;
            }
            h = (h - 1) / 3;
        }
    }

    public void quickSort(int left, int right) {
        if (right - left <= 0)return;

        long pivot = a[left];

        int p = partitionIt(left, right, pivot);
        quickSort(left, p - 1);
        quickSort(p + 1, right);
        //this.show();
    }

    public int partitionIt(int left, int right, long pivot) {
        int leftPtr = left;
        int rightPtr = right + 1;
        while(true) {
            while(a[++leftPtr] < pivot) {
                if(leftPtr == right)break;
            }
            while(a[--rightPtr] > pivot) {
                if (rightPtr == left)break;
            }
            if(leftPtr >= rightPtr)break;
            swap(leftPtr, rightPtr);
        }
        swap(left, rightPtr);
        return rightPtr;
    }

    public static void main(String[] args) {
        Array a = new Array(100);
        for (int i = 0; i < 10; i++)
        {
            long temp = (long)(Math.random()*(15-1));
            a.insert(temp);
        }

        a.show();
        a.quickSort(0, a.nElems-1);
        a.show();
    }

}
