package thread;

public class Thread3 {
    private static class MyRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "开始工作了");
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "工作ing......");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        MyRunnable target = new MyRunnable();
        Thread t1 = new Thread(target, "张三");
        Thread t2 = new Thread(target, "李四");
        System.out.println("让张三开始工作！");
        t1.start();
        t1.join(4000);
        System.out.println("张三工作完了，李四开始工作！");
        t2.start();
    }
}
