package thread;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        int num = 10;
        CountDownLatch cdl = new CountDownLatch(num);
        Thread[] threads = new Thread[num];
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            threads[i] = new Thread(() -> {
                System.out.println(finalI);
                cdl.countDown();
            });
            threads[i].start();
        }
        //等所有线程执行完后在执行  System.out.println("所有线程以执行完毕......");
        //方式1：
//        while (Thread.activeCount() > 1) {
//
//        }
        //方式2：join()
//        for (int i = 0; i < num; i++) {
//            threads[i].join();
//        }
        //方式3：CountDownLatch
        cdl.await();
        System.out.println("所有线程以执行完毕......");
    }
}
