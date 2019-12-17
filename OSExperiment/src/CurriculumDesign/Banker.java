package CurriculumDesign;

import java.util.*;




public class Banker {
    public static int SIZE = 3;

    class Process {
        //该进程对各类资源需要的最大数目
        public int[] max = new int[SIZE];
        //该进程各类资源已分配的资源数目
        public int[] allocation = new int[SIZE];
        //该进程各类资源尚需的资源数目
        public int[] need = new int[SIZE];

        public boolean finish = false;

        public Process() {
            Scanner scanner = new Scanner(System.in);
            for (int i = 0; i < max.length; i++) {
                System.out.printf("allocation[%d]:", i);
                allocation[i] = scanner.nextInt();
            }
            for (int i = 0; i < max.length; i++) {
                System.out.printf("need[%d]:", i);
                need[i] = scanner.nextInt();
            }
            for (int i = 0; i < max.length; i++) {
                max[i] = allocation[i] + need[i];
            }
        }

        public Process(Process process) {
            max = Arrays.copyOf(process.max, process.max.length);
            allocation = Arrays.copyOf(process.allocation, process.allocation.length);
            need = Arrays.copyOf(process.need, process.need.length);

        }


        @Override
        public String toString() {
            return "Process{" +
                    "max=" + Arrays.toString(max) +
                    ", allocation=" + Arrays.toString(allocation) +
                    ", need=" + Arrays.toString(need) +
                    '}';
        }
    }




    public List<Process> list = new ArrayList<Process>();

    //可用资源数
    public int[] Available = new int[SIZE];

    public Banker() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("创建的进程个数：");
        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.println("Process[" + i + "]:");
            list.add(new Process());
        }

        System.out.println("资源初始化：");
        for (int i = 0; i < Available.length; i++) {
            System.out.printf("Available[%d]:", i);
            Available[i] = scanner.nextInt();
        }
    }


    public void display() {
        System.out.println(Arrays.toString(Available));
        for (Process process : list) {
            System.out.println(process.toString());
        }
    }

    public void bankerAlgorithm() {
        //先拷贝一份list，newList
        List<Process> newList = new ArrayList<Process>();
        for (Process process : list) {
            newList.add(new Process(process));
        }

        int[] request = new int[SIZE];
        Scanner scanner = new Scanner(System.in);
        System.out.print("输入进行资源分配的Process：");
        int n = scanner.nextInt();
        for (int i = 0; i < Available.length; i++) {
            System.out.printf("source[%d]", i);
            request[i] = scanner.nextInt();
        }

        //对newList进行试分配，成功进行安全算法，不成功直接返回
        boolean flag0 = true;
        for (int i = 0; i < request.length; i++) {
            if (request[i] > Available[i]) {
                flag0 = false;
                break;
            }
        }
        if (flag0) {
            for (int i = 0; i < Available.length; i++) {
                Available[i] -= request[i];
            }

            //更新已分配资源
            for (int i = 0; i < Available.length; i++) {
                newList.get(n).allocation[i] += request[i];
            }
            //更新尚需资源
            for (int i = 0; i < Available.length; i++) {
                newList.get(n).need[i] -= request[i];
            }

        } else {
            System.out.println("分配失败！");
            return;
        }
        //进行安全检测
        boolean flag = safeCheck(newList);
        if(flag) {
            for (int i = 0; i < Available.length; i++) {
                list.get(n).allocation[i] += request[i];
                list.get(n).need[i] -= request[i];
            }
            System.out.println("分配成功！");
        } else {
            for (int i = 0; i < Available.length; i++) {
                Available[i] += request[i];
            }
        }
    }

    public boolean safeCheck(List<Process> newList) {
        int[] work = new int[SIZE];
        work = Arrays.copyOf(Available, Available.length);
        while (true) {
            Process process = find(newList, work);
            if(process != null) {
                for (int i = 0; i < work.length; i++) {
                    work[i] += process.allocation[i];
                }
                process.finish = true;
            } else {
                break;
            }
        }
        boolean flag = true;
        for (Process process : newList) {
            if(!process.finish) {
                flag = false;
                break;
            }
        }
        for (Process process : newList) {
            process.finish = false;
        }
        if (!flag) {
            System.out.println("没有通过安全性算法， 分配失败！");
        } else {
            System.out.println("安全检测通过！");
        }
        return flag;
    }

    private Process find(List<Process> newList, int[] work) {
        for (int j = 0; j < newList.size(); j++) {
            Process process = newList.get(j);
            boolean flag = true;
            for (int i = 0; i < work.length; i++) {
                if (process.need[i] > work[i]) {
                    flag = false;
                }
            }
            if (flag && !process.finish) {
                System.out.printf("P[%d] ", j);
                return process;
            }

        }
        return null;
    }

}
