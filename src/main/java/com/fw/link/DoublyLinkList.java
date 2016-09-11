package com.fw.link;

import com.fw.Tools.LogUtils;
import sun.rmi.runtime.Log;

import java.util.logging.Logger;

/**
 * Created by Administrator on 2016/8/12/0012.
 */
public class DoublyLinkList {
    private Link first;
    private Link last;

    public DoublyLinkList() {
        first = null;
        last = null;
    }

    public boolean isEmpty() {
        return (null == first);
    }

    public void insertFirst(int dd) {
        Link newLink = new Link(dd);
        if(isEmpty()) {
            first = newLink;
            return;
        }
        first.previous = newLink;
        newLink.next = first;
        first = newLink;
    }

    public void insertLast(int dd) {
        Link newLink = new Link(dd);
        if(isEmpty()) {
            first = newLink;
            last = newLink;
            return;
        }
        last.next = newLink;
        newLink.previous = last;
        last = newLink;
    }

    public boolean insertAfter(int key, int dd) {
        Link current = first;
        while(current.getKey() != key) {
            current = current.next;
            if(null == current)return false;
        }
        Link newLink = new Link(dd);
        if(current == last) {
            newLink.next = null;
            last.next = newLink;
            newLink.previous = last;
            last = newLink;
            return true;
        }else {
            newLink.next = current.next;
            current.next.previous = newLink;
            current.next = newLink;
            newLink.previous = current;
            return true;
        }
    }

    public void display() {
        if(isEmpty()) {
            LogUtils.log.info("the doublyLinkList is empty!");
        }
        Link cur = first;
        LogUtils.log.info("display doublyLinkList head -----> tail :");
        while(null != cur.next) {
            LogUtils.log.info("link : "+cur.getKey());
            cur = cur.next;
        }
    }

    public static void main(String[] args) {
        DoublyLinkList dl = new DoublyLinkList();
        dl.insertFirst(2);
        dl.insertFirst(245);
        dl.insertFirst(12);
        dl.insertFirst(23);
        dl.insertFirst(45);
        dl.insertFirst(32);
        dl.insertFirst(4);
        dl.display();

        dl.insertAfter(23, 3);
        dl.display();
    }
}
