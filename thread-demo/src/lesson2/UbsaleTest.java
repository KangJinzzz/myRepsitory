package lesson2;

//初始化一个值I = 0；
//创建20 个线程， 每个线程执行10000次并线程对I进行++
public class UbsaleTest {
    public static int I = 0;
    public static void main(String[] args) {
        Object o = new Object();
        for (int i = 0; i < 20; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        //同步互斥, 线程加锁
                        synchronized (o) {
                            I++;
                        }
                    }
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