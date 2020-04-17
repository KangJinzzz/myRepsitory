package thread;

public class Thread6 {
    static class MyRunnable implements Runnable {
        int ticket = 1000;
        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                synchronized (this) {
                    if (ticket > 0) {
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName() + "还有" + ticket-- + "张票....");
                    }
                }
            }
        }
    }
    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        Thread t1 = new Thread(myRunnable, "黄牛1");
        Thread t2 = new Thread(myRunnable, "黄牛2");
        Thread t3 = new Thread(myRunnable, "黄牛3");
        t1.start();
        t2.start();
        t3.start();
    }
}
