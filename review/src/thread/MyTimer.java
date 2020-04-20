package thread;




public class MyTimer {

    /**
     * 定时任务
     * @param task 任务类
     * @param delay 延迟时间
     * @param period 间隔时间
     */
    public void schedule(Runnable task, long delay, long period) {
        new Thread(new MyTimerTask(task, delay, period)).start();
    }

    public static class MyTimerTask implements Runnable{

        //下次的执行时间
        private long next;
        private Runnable task;
        //间隔时间
        private long period;

        public MyTimerTask(Runnable task, long delay, long period) {
            this.task = task;
            this.next = System.currentTimeMillis() + delay;
            this.period = period;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    long current = System.currentTimeMillis();
                    if (current < next) {
                        //等待 next - current
                        Thread.sleep(next - current);
                    }
                    task.run();
                    if (period > 0) {
                        next += period;
                    } else {
                        break;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        MyTimer timer = new MyTimer();
        timer.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("MyTimer");
            }
        }, 3000, 1000);
    }
}
