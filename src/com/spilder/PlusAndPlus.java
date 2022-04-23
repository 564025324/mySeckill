package com.spilder;

public class PlusAndPlus {

    public static int var = 0;

    // 由于var++不是原子操作，所以需要synchronized同步代码块保持原子性
    public synchronized static void plusAndPlus() {
        var++;
    }

    public static void main(String[] args) {
        Thread threads[] = new Thread[20];
        for (int i = 0; i < 20; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        plusAndPlus();
                    }
                }
            });
            threads[i].start();
            System.out.println("[i]=" + i + "var=>" + var);
        }
        System.out.println("Thread.activeCount()=" + Thread.activeCount());
        while (Thread.activeCount() > 1) {
            // 等待所有线程执行完毕
            Thread.yield();
        }
        System.out.println("var=>" + var);
    }


}
