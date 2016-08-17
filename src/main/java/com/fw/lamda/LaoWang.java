package com.fw.lamda;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by Administrator on 2016/8/16/0016.
 */
public class LaoWang {

    public static void sayName() {
        Person p = ()->{
            System.out.print("hi, I am laowang");
        };
        p.sayName();
    }

    Function<String, String> stringConsumer = (String s) -> {
        return s;
    };

    public static void main(String[] args) {
        LaoWang laowang = new LaoWang();
        String s =laowang.stringConsumer.apply("haha");
        System.out.println(s);
    }

}
