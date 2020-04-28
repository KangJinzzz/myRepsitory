package thread;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomcIntegerTest {
    private static AtomicInteger AI = new AtomicInteger();

    public static void test() {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    AI.incrementAndGet();
                }
            }).start();
        }
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
        System.out.println(AI.get());
    }
    public static void main(String[] args) {
        test();
    }

}
