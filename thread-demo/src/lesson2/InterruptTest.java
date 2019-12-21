package lesson2;

public class InterruptTest {
    public static void main(String[] args) throws InterruptedException {
        Interrupt1();
        Interrupt2();


    }
    public static void Interrupt1() throws InterruptedException {
                Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(!Thread.currentThread().isInterrupted()) {
                        System.out.println(Thread.currentThread().getName());
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
        Thread.sleep(3000);
        t.interrupt();
    }

    public static void Interrupt2() throws InterruptedException {
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while(!Thread.currentThread().isInterrupted()){
                    try {

                        System.out.println(Thread.currentThread().getName());
                        Thread.sleep(1000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t2.start();
        Thread.sleep(3000);
        t2.interrupt();
    }

}
