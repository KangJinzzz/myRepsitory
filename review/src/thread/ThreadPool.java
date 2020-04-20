package thread;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

//自己实现的newFixedThreadPool()
public class ThreadPool {
    //线程池中的线程
    private Thread[] threads;
    //线程池的容量
    private int capacity;
    //阻塞队列
    private BlockingQueue<Runnable> workQueue;

    //size为阻塞队列的容量
    public ThreadPool(int capacity, int size) {
        threads = new MyThread[capacity];
        workQueue = new ArrayBlockingQueue<>(size);
        //创建capacity个线程
        for (int i = 0; i < capacity; i++) {
            threads[i] = new MyThread(workQueue);
            threads[i].start();
        }
    }

    //将任务task存入阻塞队列中
    public void execute(Runnable task) {
        try {
            workQueue.put(task);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static class MyThread extends Thread {
        private BlockingQueue<Runnable> workQueue;

        public MyThread(BlockingQueue<Runnable> workQueue) {
            this.workQueue = workQueue;
        }

        @Override
        public void run() {
            //线程从创建开始会一直从则阻塞队列中拿任务
            while (true) {
                try {
                    //从阻塞队列中拿任务
                    Runnable task = workQueue.take();
                    task.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        ThreadPool pool = new ThreadPool(
                5, 1000);
        Runnable task = new Runnable() {
            @Override
            public void run() {
                System.out.println("这是一个任务");
            }
        };
        for(int i=0; i<10; i++){
            pool.execute(task);
        }
    }

}
