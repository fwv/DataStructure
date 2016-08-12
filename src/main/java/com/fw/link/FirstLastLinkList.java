package com.fw.link;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Administrator on 2016/8/11/0011.
 */
public class FirstLastLinkList {
    public static Logger log = Logger.getLogger(FirstLastLinkList.class.getName());
    private Link first;
    private Link last;

    public FirstLastLinkList() {
        first = null;
        last = null;
    }

    public boolean isEmpty() {return (null == first);}

    public void insertFirst(int iData, double dData) {
        Link newLink = new Link(iData, dData);
        if(isEmpty()){
            last = newLink;
        }
        newLink.next = first;
        first = newLink;
    }

    public void insertLast(int iData, double dData) {
        Link newLink = new Link(iData, dData);
        if(isEmpty())
            first = newLink;
        last.next = newLink;
        last = newLink;
    }

    public Link deleteFirst() {
        Link temp = first;
        if(first.next == null)
            last = null;
        first = first.next;
        return temp;
    }

    public void displayLinkList() {
        log.log(Level.INFO, "List(first-->last): ");
        Link current = first;
        while(null != current) {
            current.displayLink();
            current = current.next;
        }
    }

}
