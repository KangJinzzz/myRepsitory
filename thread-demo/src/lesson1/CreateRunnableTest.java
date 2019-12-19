package lesson1;

public class CreateRunnableTest {
    public static void main(String[] args) {
        Thread t = new Thread(new MyRunnable(), "MyRunnable");
        t.start();
        System.out.println(Thread.currentThread().getName());

    }
}


class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}