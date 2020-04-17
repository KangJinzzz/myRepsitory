package thread;

import java.util.Random;

//阻塞队列
public class MessageQueue {
    public int putIndex;
    public int takeIndex;
    public int[] queue;
    public static volatile int size;

    public MessageQueue(int size) {
        queue = new int[size];
        putIndex = 0;
        takeIndex = 0;
        size = 0;
    }

    public void put(int value) throws InterruptedException {
        while (true) {
            synchronized (this) {
                if(size < queue.length) {
                    queue[putIndex] = value;
                    putIndex = (putIndex + 1) % queue.length;
                    size++;
                    notifyAll();
                    return;
                }
            }

            if (size == queue.length) {
                synchronized (this) {
                    if (size == queue.length) {
                        wait();
                    }
                }
            }
        }
    }

    public int take() throws InterruptedException {
        while (true) {
            synchronized (this) {
                if (size > 0) {
                    int ret = queue[takeIndex];
                    takeIndex = (takeIndex + 1) % queue.length;
                    size--;
                    notifyAll();
                    return ret;
                }
            }

            if (size == 0) {
                synchronized (this) {
                    if (size == 0) {
                        wait();
                    }
                }
            }
        }
    }

    public int getSize() {
        return size;
    }

    public static void main(String[] args) {
        MessageQueue messageQueue = new MessageQueue(10);

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    Random random = new Random();
                    try {
                        messageQueue.put(random.nextInt(10));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "生产者");
        thread1.start();

        Thread thread2 = new Thread(() -> {
            while (true) {
                try {
                    System.out.print(messageQueue.take() + " ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "消费者");
        thread2.start();


    }


}


