package com.fw.link;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Administrator on 2016/8/11/0011.
 */
public class Link {
    public static Logger log = Logger.getLogger(Link.class.getName());
    private int iData;
    private double dData;
    public Link next;
    public Link previous;

    public Link(int iData, double dData) {
        this.iData = iData;
        this.dData = dData;
    }

    public Link(int iData) {
        this.iData = iData;
    }

    public int getKey(){return iData;}

    public void displayLink() {
        log.log(Level.INFO, "{" + iData + "," +  dData +"}");
    }
}
