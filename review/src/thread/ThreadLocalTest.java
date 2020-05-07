package thread;

public class ThreadLocalTest {
    private static String commStr;
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        commStr = "main";
        threadLocal.set("main");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                commStr = "thread";
                threadLocal.set("thread");
            }
        });
        thread.start();
        thread.join();
        System.out.println(commStr);
        System.out.println(threadLocal.get());
    }
}
