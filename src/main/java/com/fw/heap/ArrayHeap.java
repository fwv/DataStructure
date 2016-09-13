package com.fw.heap;

import java.util.function.Function;

/**
 * @Author fengwei
 * Created on 2016/8/17/0017.
 */
public class ArrayHeap<T extends Comparable> {

    private T[] pq;

    private int N = 0;

    public ArrayHeap(int size) {
        pq = (T[]) new Comparable[size+1];
    }

    public boolean isEmpty() {return 0 == N;}

    public int getSize() {return N;}

    public void swap(int i, int j) {
        Comparable temp;
        temp = pq[i];
        pq[i] = pq[j];
        pq[j] = (T) temp;
    }

    public boolean less(int i, int j) {
        if(pq[i].compareTo(pq[j]) < 0)
            return true;
        else return false;
    }

    public void insert(T v) {
        pq[++N] = v;
        swim(N);
    }

    public T delMax() {
        T max = pq[1];
        swap(1, N--);
        pq[N+1] = null;
        sink(1);
        return  max;
    }

    private void swim(int k) {
        int h = k / 2;
        while(k > 1 && less(h, k)) {
            swap(h, k);
            k = h;
            h = k / 2;
        }
    }

    private void sink(int k) {
        while(2 * k < N) {
            int j = 2 * k;
            if (j < N && less(j, j+1)) j++;
            if (less(j, k)) break;
            swap(k, j);
            k = j;
        }
    }

    public void display() {
        String s = "PQ: ";
        for(int i = 1; i < N+1; i ++) {
             s += " " + pq[i];
        }
        System.out.println(s);
    }

    public static void main(String[] args) {
        ArrayHeap<Integer> ah = new ArrayHeap<Integer>(15);
        for (int i = 0; i < 10; i++) {
            ah.insert((int)(Math.random()*100));
        }
        ah.display();
        ah.insert(100);
        ah.display();
        ah.delMax();
        ah.display();

        while(!ah.isEmpty()) {
            System.out.print(ah.delMax() + " ");
        }
    }

}
