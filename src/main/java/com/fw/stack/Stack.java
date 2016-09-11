package com.fw.stack;

import com.fw.Tools.LogUtils;
import sun.rmi.runtime.Log;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Administrator on 2016/8/11/0011.
 */
//@unThreadSafe
public class Stack<T> {

    private T[] stackArray;

    private int top;

    private int size;

    public Stack(int size, T[] array) {
        this.size = size;
        this.stackArray = array;
        this.top = -1;
    }

    public void push(T temp) {
        if(this.top <= size-2) {
            stackArray[top+1] = temp;
            top++;
        }else {
            LogUtils.log.info("the stack is pull!");
        }
    }

    public T pop() {
        if(!isEmpty()){
            T temp = stackArray[top];
            top--;
            return temp;
        }else {
            LogUtils.log.info("the stack is empty!");
            return null;
        }
    }

    public boolean isEmpty() {
        if(-1 == top) {
            return true;
        } else {
            return false;
        }
    }

    public void print() {
        if(!isEmpty()) {
            String str = "Stack : ";
            for(int out = 0; out <= top; out++) {
                str += " "+stackArray[out];
            }
            LogUtils.log.info(str);
        }else {
            LogUtils.log.info("the stack is empty!");
        }
    }


    public static void StrReverse(String str) {
        char[] chars = str.toCharArray();
        int size = chars.length;
        String[] strs = new String[size];
        Stack stack = new Stack(size, strs);
        for (int i = 0; i < size; i++) {
            strs[i] = String.valueOf(chars[i]);
        }
        for(int i = 0; i < strs.length; i++) {
            stack.push(strs[i]);
        }
        stack.print();
        String result = "Reverse: ";
        int len = stack.top;
        for(int i = 0; i <= len; i++) {
            result += " "+stack.pop();
        }
        LogUtils.log.info(result);
    }



    public static void main(String[] args) {
        Stack.StrReverse("fengwei hello");
    }
}
