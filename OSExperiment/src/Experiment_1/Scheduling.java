package Experiment_1;
import java.util.*;


class Process implements Comparable<Process> {
    public String name = null;
    //到达时间
    public int TArrive = 0;
    //服务时间
    public int TService = 0;
    //完成时间
    public int TComplete = 0;
    //作业周转时间
    public int Ti = 0;
    //带权周转时间
    public double W = 0;
    //判断该进程是否被处理
    public boolean isDealed = false;
    public int TRemaining = 0;
    public Process(String name, int TArrive, int TService) {
        this.name = name;
        this.TArrive = TArrive;
        this.TService = TService;
        this.TRemaining = TService;
    }

    @Override
    public String toString() {
        return "Process{" +
                "name='" + name + '\'' +
                ", TArrive=" + TArrive +
                ", TService=" + TService +
                ", TComplete=" + TComplete +
                ", Ti=" + Ti +
                ", W=" + W +
                '}';
    }

    @Override
    public int compareTo(Process o) {
        return this.TArrive - o.TArrive;
    }
}

public class Scheduling {
    public List<Process> list = new ArrayList<>();

    public void add2() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入处理进程个数：");
        int n = scanner.nextInt();
        while (n > 0) {
            System.out.println("请输入进程名：");
            String name = scanner.next();
            System.out.println("请输入到达时间：");

            int TArrive = scanner.nextInt();
            System.out.println("请输入服务时间：");

            int TService = scanner.nextInt();
            list.add(new Process(name, TArrive, TService));
            n--;
        }
        for (Process process : list) {
            process.isDealed = false;
        }
    }



    //先到先服务
    public void firstCome() {
        initialize();
        Collections.sort(list);
        int time = 0;
        Queue<Process> queue = new LinkedList<>(list);
        while (!queue.isEmpty()) {
            Process deal = queue.poll();
            assert deal != null;
            if(deal.TArrive > time) {
                time = deal.TArrive;
            }
            deal.TComplete = time + deal.TService;
            deal.Ti = deal.TComplete - deal.TArrive ;
            deal.W = (double)deal.Ti / deal.TService;
            time = deal.TComplete;
        }
    }


    //短作业优先 SJF
    public void shortService() {
        initialize();
        Collections.sort(list);
        LinkedList<Process> deal = new LinkedList<>(list);
        int t = 0;
        while (!deal.isEmpty()) {
            int TServiceMin = deal.peekFirst().TService;
            Process toRemove = null;

            for (Process goal : deal) {
                if (goal.TArrive <= t && TServiceMin >= goal.TService) {
                    TServiceMin = goal.TService;
                    toRemove = goal;
                }
            }

            if (toRemove == null) {
                toRemove = deal.peekFirst();
                assert toRemove != null;
                t = toRemove.TArrive;
                for (Process goal : deal) {
                    if (goal.TArrive <= t && TServiceMin >= goal.TService) {
                        TServiceMin = goal.TService;
                        toRemove = goal;
                        t = toRemove.TArrive;
                    }
                }
            }
            Process process = toRemove;
            process.TComplete = t + TServiceMin;
            process.Ti = process.TComplete - process.TArrive ;
            process.W = (double)process.Ti / process.TService;
            t = process.TComplete;
            deal.remove(toRemove);
        }
    }

    //时间片轮转
    public void timeRotation() {
        initialize();
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入时间片q：");
        //时间片
        int q = scanner.nextInt();
        int time = 0;
        Collections.sort(list);
        LinkedList<Process> nPerformed = new LinkedList<>(list);
        LinkedList<Process> queue = new LinkedList<>();
        queue.offer(list.get(0));
        list.get(0).isDealed = true;
        time = list.get(0).TArrive;

        while(!nPerformed.isEmpty()) {
            while (!queue.isEmpty()) {
                Process deal = queue.pollFirst();
                if(!nPerformed.isEmpty() && nPerformed.peekFirst() == deal) {
                    nPerformed.pollFirst();

                }
                assert deal != null;
                if(deal.TRemaining == q) {
                    deal.TComplete = time + q;
                    deal.TRemaining = 0;
                    time += q;

                } else if (deal.TRemaining < q) {
                    deal.TComplete = time + deal.TRemaining;
                    time += deal.TRemaining;
                    deal.TRemaining = 0;
                } else {
                    deal.TComplete = time + q;
                    deal.TRemaining -= q;
                    time += q;
                }
                for (Process process: list) {
                    if(!process.isDealed && process.TArrive <= time) {
                        queue.offer(process);
                        process.isDealed = true;
                    }
                }
                if(deal.TRemaining > 0) {
                    queue.addLast(deal);
                }
            }
            if(!nPerformed.isEmpty()) {
                Process nDeal = nPerformed.peekFirst();
                assert nDeal != null;

                time = nDeal.TArrive;

                queue.offer(nDeal);
            }
        }

        for (Process process : list) {
            process.Ti = process.TComplete - process.TArrive;
            process.W = (double)process.Ti / process.TService;
        }
    }


    public void display() {
        for (Process aList : list) {
            System.out.println(aList.toString());
        }
        double sumW = 0;
        double w = 0;
        for (Process process : list) {
            sumW += process.W;
        }
        w = sumW / list.size();
        System.out.println("w = " + w);
    }

    public void menu() {
        System.out.println("================");
        System.out.println("请选择功能 ：");
        System.out.println("1.先到先服务算法");
        System.out.println("2.短作业优先算法");
        System.out.println("3.时间片轮转算法");
        System.out.println("0.退出");
        System.out.println("================");
    }

    private void initialize () {
        for (Process process : list) {
            process.TRemaining = process.TService;
            process.isDealed = false;
        }
    }

}
