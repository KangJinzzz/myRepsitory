package thread;

public class ThreadInterrupt {
    private static class MyRunnable implements Runnable {
        public volatile boolean isQuit = false;
        @Override
        public void run() {
            while (!isQuit) {
                System.out.println("李四正在转账！");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("险些误了大事！");
        }
    }
    public static void main(String[] args) throws InterruptedException {
        MyRunnable myRunnable = new MyRunnable();
        Thread t = new Thread(myRunnable, "李四");
        t.start();
        Thread.sleep(10 * 1000);
        System.out.println(Thread.currentThread().getName() + "别转账了，那人是骗子！");
        myRunnable.isQuit = true;
    }
}
