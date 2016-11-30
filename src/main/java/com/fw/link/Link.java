package com.fw.link;

import com.fw.Tools.LogUtils;
import sun.rmi.runtime.Log;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Author fengwei
 * Created on 2016/8/11/0011.
 */
public class Link {
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
        LogUtils.log.info( "{" + iData + "," +  dData +"}");
    }

}
