package thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    private static int SUM = 0;

    public static void test() {
        for (int i = 0; i < 10; i++) {
            new Thread(()-> {
                for (int j = 0; j < 10000; j++) {
                    synchronized (ReentrantLockTest.class) {
                        System.out.println(SUM++);
                    }
                }
            }).start();
        }
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
        System.out.println(SUM);
    }

    public static void test2() {
        Lock lock = new ReentrantLock();
        for (int i = 0; i < 20; i++) {
            new Thread(()-> {
                for (int j = 0; j < 10000; j++) {
                    try {
                        lock.lock();
                        System.out.println(SUM++);
                    } finally {
                        lock.unlock();
                    }

                }
            }).start();
        }
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
        System.out.println(SUM);
    }
    public static void main(String[] args) {
        test2();
    }
}
