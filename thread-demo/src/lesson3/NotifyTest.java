package lesson3;

public class NotifyTest {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (NotifyTest.class) {
                    try {
                        System.out.println("t1");
                        Thread.sleep(1000);
                        //t1线程阻塞，释放对象锁
                        NotifyTest.class.wait();
                        System.out.println("t1 wait finish");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t1.start();
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (NotifyTest.class) {
                    try {
                        NotifyTest.class.notify();
                        System.out.println("t2");
                        Thread.sleep(1000);
                        System.out.println("t2 wait finish");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }
}
