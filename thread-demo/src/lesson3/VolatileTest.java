package lesson3;

public class VolatileTest {
    private static volatile int N;

    public static synchronized void increment() {
        N++;
    }
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 100; j++) {
                        N++;
                    }
                }
            }).start();
        }
    }
}
