package thread;

public class Wait {
    public static void main(String[] args) throws InterruptedException {
        Object object = new Object();
        synchronized (object) {
            System.out.println("等待中.....");
            object.wait();
            System.out.println("等待结束。");
        }

        System.out.println("main方法已结束");
    }
}
