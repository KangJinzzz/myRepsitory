package thread;

public class ThreadStateTransfer {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            for (int i = 0; i < 1000_000; i++) {
            }
        }, "李四");
        System.out.println(t.getName() + t.getState());
        System.out.println(t.getName() + t.isAlive());
        t.start();
        while (t.isAlive()) {
            System.out.println(t.getName() + t.getState());
        }
        System.out.println("李四死了");
        System.out.println(t.getName() + t.getState());
    }
}
