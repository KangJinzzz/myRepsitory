package thread;

public class Thread4 {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + "工作中........");
                    Thread.yield();
                }
            }
        }, "张三");
        Thread t2 = new Thread(() -> {
            while (true) {
                System.out.println(Thread.currentThread().getName() + "工作中！！！！！！！");
            }
        }, "李四");
        t1.start();
        t2.start();
    }
}
