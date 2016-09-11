package com.fw.link;

import com.fw.Tools.LogUtils;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Administrator on 2016/8/11/0011.
 */
public class LinkList {
    private Link first;

    public LinkList() {
        first = null;
    }

    public boolean isEmpty() {
        return (null ==first);
    }

    public void insertFirst(int iData, double dData) {
        Link newLink = new Link(iData, dData);
        newLink.next = first;
        first = newLink;
    }

    // SortLinkList insert
    public void insertBySort(int iData, double dData) {
        Link newLink = new Link(iData, dData);
        Link current = first;
        Link pre = null;
        if(isEmpty()) {
            first = newLink;
        }else {
            while(current.getKey() < iData) {
                pre = current;
                current = current.next;
                if(null == current) {
                    pre.next = newLink;
                    return;
                }
            }
            if(pre == null) {
                newLink.next = first;
                first = newLink;
            }else {
                pre.next = newLink;
                newLink.next = current;
            }
        }
    }

    public Link deleteFirst() {
        Link temp = first;
        first = first.next;
        return temp;
    }

    public void displayLinkList() {
        LogUtils.log.info("List(first-->last): ");
        Link current = first;
        while(null != current) {
            current.displayLink();
            current = current.next;
        }
    }

    public static void main(String[] args) {
        LinkList list = new LinkList();
//        list.insertBySort(24, 1.23);
//        list.insertBySort(4, 14.53);
//        list.insertBySort(34, 61.03);
//        list.insertBySort(134, 61.03);
//        list.insertBySort(36, 61.03);
//        list.insertBySort(4, 61.03);
//        list.insertBySort(4, 61.03);
//        list.insertBySort(14, 6.3);

        int count = 0;
        while(true) {
            if(count > 1000) {
                LogUtils.log.info("insert count is : " + count);
                count = 0;
            }
            list.insertFirst(12,23.1);
            count++;

        }
    }

}
