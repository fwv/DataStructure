package com.fw.Tools;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * @Author fengwei
 * Created on 2016/9/11.
 */
public class LogUtils {

    private static PropertyConfigurator property;

    public static Logger log;

    static {
        property.configure( "log4j.properties");
        log = Logger.getLogger(LogUtils.class );
    }

    public static void main(String[] args) {
        LogUtils.log.info("hi, i am a INFO message");
    }

}
