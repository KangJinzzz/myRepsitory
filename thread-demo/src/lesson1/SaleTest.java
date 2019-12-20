package lesson1;

public class SaleTest {

    public static int I = 0;

    public static synchronized void increment() {
        for (int i = 0; i < 10000; i++) {
            I++;
        }
    }
    public static void main(String[] args) {
        Object o = new Object();
        for (int i = 0; i < 20; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
//                    for (int i = 0; i < 10000; i++) {
//                        //同步互斥, 线程加锁
//                        synchronized (o) {
//                            I++
//                        }
//                    }
                    increment();
                }
            }).start();
        }
        //>1使用debug方式启动
        while (Thread.activeCount() > 1) {
            //线程让步，从运行态转变为就绪态
            Thread.yield();
        }
        System.out.println(I);
    }
}
