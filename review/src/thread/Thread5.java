package thread;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class Thread5 {
    static class Counter {

        long count = 0;

        public void increment() {
            count++;
        }
        public void decrement() {
            count--;
        }
        public long ret() {
            return count;
        }
    }
    public static void main(String[] args) throws InterruptedException {
        final int COUNT = 10000000;
        Counter counter = new Counter();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < COUNT; i++) {
                counter.increment();
            }
        }, "李四");

        t1.start();
        for (int i = 0; i < COUNT; i++) {
            counter.decrement();
        }
        t1.join();

        System.out.println(counter.ret());
    }
}



