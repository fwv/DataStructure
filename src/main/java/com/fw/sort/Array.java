package com.fw.sort;

import com.fw.Tools.LogUtils;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Array Sort: kinds of sort methods based on array
 */
public class Array {

    private long[] a;

    private int length;

    private int nElems;

    public Array(int length) {
        this.length = length;
        a = new long[this.length];
        this.nElems = 0;
    }

    public void insert(long temp) {
        this.a[nElems++] = temp;
    }

    public void show() {
        LogUtils.log.info("==========Show Array==========");
        String str = "Array: ";
        for(int i = 0; i < nElems; i++) {
            str += " "+a[i];
        }
        LogUtils.log.info(str);
        LogUtils.log.info("==========Show Array==========");
    }

    public void swap(int i, int j) {
        a[i] = a[i] ^ a[j];
        a[j] = a[i] ^ a[j];
        a[i] = a[i] ^ a[j];
    }

    /**
     * left directional bubble sort
     */
    public void bubbleFromRightSort() {
       for (int out = nElems-1; out > 1; out--) {
           for (int in = 0; in < out; in++) {
               if (a[in] > a[in+1])
                   swap(in, in+1);
           }
       }
    }

    /**
     * right directional bubble sort
     */
    public void bubbleFromLeftSort() {
        for(int i = 0; i < nElems-2; i++) {
            for(int j = nElems-1; j > i; j--) {
                if(a[j] < a[j-1]) {
                 swap(j, j-1);
                }
            }
        }
    }

    /**
     * selection sort
     */
    public void selectionSort() {
       for(int i = 0; i < nElems-1; i++) {
           for(int j = i+1; j < nElems; j++) {
                if(a[j] < a[i]) {
                    swap(j, i);
                }
           }
       }
    }

    /**
     * double directional bubble sort
     */
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

    /**
     * insert sort
     */
    public void insertSort() {
//        for(int out = 1; out < nElems; out++) {
//            int in = out;
//            long temp = a[out];
//            while(in > 0 && a[in-1] > temp) {
//                a[in] = a[in-1];
//                in--;
//            }
//            a[in] = temp;
//        }

        for (int i = 1; i < nElems; i++) {
            for (int j = i; j > 0 && a[j] < a[j-1]; j--) {
                swap(j, j-1);
            }
        }
     }

    /**
     * shell sort
     */
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

    /**
     * quick sort
     * @param left
     * @param right
     */
    public void quickSort(int left, int right) {
       if (right - left <= 0)return;
        long pivot = a[left];
        int newpivot = partitionIt(left, right, pivot);
        quickSort(left , newpivot-1);
        quickSort(newpivot+1, right);
    }

    /**
     * quick sort choose pivot
     * @param left
     * @param right
     * @param pivot
     * @return
     */
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

    /**
     * merge sort: O(nlogn) from top to down
     */
    public void mergeSortFromTop2Down() {
        long[] aux = new long[nElems];
        sort(aux, 0, nElems-1);
    }

    public void sort(long[] aux, int lo, int hi) {
        if (lo == hi)return;
        int mid = lo + (hi - lo) / 2;
        sort(aux, lo, mid);
        sort(aux, mid+1, hi);
        merge(aux, lo, mid, hi);
    }

    public void merge(long[] aux, int lo, int mid, int hi) {

        for (int i = lo; i <= hi; i++) {
            aux[i] = a[i];
        }
        int left = lo;
        int right = mid+1;
        for (int k = lo; k <= hi; k++) {
            if (left > mid) {
                a[k] = aux[right++];
            } else if (right > hi) {
                a[k] = aux[left++];
            } else if (aux[left] < aux[right]) {
                a[k] = aux[left++];
            } else if (aux[right] <= aux[left]) {
                a[k] = aux[right++];
            } else {
                LogUtils.log.error("merge error !");
            }
        }
        ArrayList<String> str = new ArrayList<>();
        for (int k = lo; k <= hi; k++) {
            str.add("" + a[k]);
        }
        LogUtils.log.info(str);
    }

    /**
     * heap sort : heap construction sink()
     * scan starts halfway back through a[] could
     * skip subheap of size 1.
     * @param k
     * @param n
     */
    public void sink(int k, int n) {
        while(2*k <= n) {
            int j = 2 * k;
            if (j < n && a[j-1] < a[j])j++;
            if (a[k-1] >= a[j-1])break;
            swap(k-1, j-1);
            k = j;
        }
    }

    /**
     * heap sort
     * for consistence with a[0] through a[n-1],
     * decrement the a[]'s indices in swap() and compare value.
     */
    public void heapSort() {
        int n = nElems;
        for (int i = n/2; i > 0; i--) {
            sink(i, n);
        }
        while (n > 1) {
            swap(0, n-1);
            sink(1, --n);
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        Array a = new Array(100);
/*        for (int i = 0; i < 5; i++)
        {
            long temp = (long)(Math.random()*(15-1));
            a.insert(temp);
        }*/
        a.insert(4);
        a.insert(5);
        a.insert(1);
        a.insert(11);
        a.insert(8);
        a.insert(9);
        a.insert(19);
        a.insert(191);
        a.show();
        a.mergeSortFromTop2Down();
        a.show();
    }

}
