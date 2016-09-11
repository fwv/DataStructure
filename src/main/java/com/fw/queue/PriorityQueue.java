package com.fw.queue;

import com.fw.Tools.LogUtils;

import java.util.Comparator;
import java.util.Objects;
import java.util.logging.Logger;


/**
 * PriorityQueue based on heap
 */
public class PriorityQueue<T> {

    private int capacity = 0;

    private int size = 0;

    private static final int INITIAL_CAPACITY = 20;

    private Object[] queue;

    private Comparator<T> comparator;

    public PriorityQueue() {
        this.queue = new Object[INITIAL_CAPACITY];
    }

    public PriorityQueue(int capacity) {
        this.capacity = capacity;
        this.queue = new Object[capacity];
    }

    public PriorityQueue(Comparator<T> comparator) {
        this.capacity = INITIAL_CAPACITY;
        this.comparator = comparator;
        this.queue = new Object[capacity];
    }

    public PriorityQueue(int capacity, Comparator<T> comparator) {
        this.capacity = capacity;
        this.comparator = comparator;
        this.queue = new Object[capacity];
    }

    public boolean add(T t) {
        if (capacity == size)return  false;
        queue[++size] = t;
        swim(size);
        return  true;
    }

    public Object poll() {
        if (0 == size)return null;
        Object first = this.queue[1];
        queue[1] = queue[size];
        queue[size--] = null;
        sink(1, size);
        return first;
    }

    public void swim(int k) {
        while (k > 1) {
           int parent = k >>> 1;
            if (this.comparator.compare((T)queue[k], (T)queue[parent]) <= 0)break;
            swap(k, parent);
            k = parent;
        }
    }

    public void sink(int k, int n) {
        while(k <= n / 2) {
            int child = k << 1;
            if (child < n && comparator.compare((T)queue[child], (T)queue[child+1]) < 0) child++;
            if (comparator.compare((T)queue[k], (T)queue[child]) > 0) break;
            swap(k, child);
            k = child;
        }
    }

    public void swap(int i, int j) {
        Object temp = queue[i];
        queue[i] = queue[j];
        queue[j] = temp;
    }

    public void show() {
        String s = "queue : ";
        for (int i = 1; i < size+1; i++) {
            s += " "+this.queue[i];
        }
        LogUtils.log.info(s);
    }

    public void showPoll() {
        int size = this.size;
        String s = "queue poll: ";
        for (int i = 1; i < size+1; i++) {
            s += " "+poll();
        }
        LogUtils.log.info(s);
    }

    public static void main(String[] args) {
        Comparator<Integer> com = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        };

        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(20, com);
        queue.add(5);
        queue.add(2);
        queue.add(6);
        queue.add(5);
        queue.add(2);
        queue.add(6);
        queue.add(5);
        queue.add(2);
        queue.add(6);
        queue.show();
        queue.showPoll();
    }
}
