package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Count {
    static int count = 0;
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        List<Future<?>> futureList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Future<?> task = pool.submit(new MyRunnable());
        }

    }

    static class MyRunnable implements Runnable {

        @Override
        public void run() {
            count++;
        }
    }
}
