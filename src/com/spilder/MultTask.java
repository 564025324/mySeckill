package com.spilder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 创建一个多线程的类，通过Main方法运行一组数组的多线程打印
 *
 * @Author: Lijingwen
 */
public class MultTask extends Thread {

    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");
    static Map<Integer, String> map = new HashMap<>();

    // 创建一个10000个数字的数组
    static {
        for (int i = 0; i < 10; i++) {
            String num = String.valueOf(i);
            map.put(i, num);
            map.put(1, "1");
            map.put(2, "2");
            map.put(6, "8");
            map.put(6, "7");
            map.put(3, "4");
        }
    }

    public MultTask(String name,Integer num) {
        super(name);
        map.remove(num);
    }


    @Override
    public void run() {
        System.out.println(sdf.format(new Date()) + "线程" + getName() + "开始执行");

    }


    public static void main(String[] args) throws InterruptedException {
        MultTask[] tasks = new MultTask[5];
        for (int i = 0; i < tasks.length; i++) {
            tasks[i] = new MultTask("线程" + i,i);
            tasks[i].start();
        }
        System.out.println(map);
    }
}

