package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {
    //初始化5个资源
    private static  final Semaphore avialable = new Semaphore(5);

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        Runnable target = new Runnable() {
            @Override
            public void run() {
                try {
                    avialable.acquire();    //获取一个资源，没获取到会阻塞在这行代码
                    Thread.sleep(10 * 1000);
                    System.out.println(Thread.currentThread().getName());
                    avialable.release();    //释放一个资源
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        //最多有5个线程在同一时间点获取到资源
        for (int i = 0; i < 10; i++) {
            pool.execute(target);
        }
        pool.shutdown();
    }
}
