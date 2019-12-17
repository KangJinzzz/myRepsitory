package CurriculumDesign;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


class Process {
    //该进程对各类资源需要的最大数目
    public int[] max = new int[3];
    //该进程各类资源已分配的资源数目
    public int[] allocation = new int[3];
    //该进程各类资源尚需的资源数目
    public int[] need = new int[3];

    public boolean finish = false;

    public Process() {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < max.length; i++) {
            System.out.printf("max[%d]:", i);
            max[i] = scanner.nextInt();
            need[i] = max[i];
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

public class Banker {
    public List<Process> list = new ArrayList<Process>();

    public int[] Max = new int[3];
    //可用资源数
    public int[] Available = new int[3];

    public Banker() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("创建的进程个数：");
        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.println("Process[" + i + "]:");
            list.add(new Process());
        }

        System.out.println("资源初始化：");
        for (int i = 0; i < Max.length; i++) {
            System.out.printf("Max[%d]:", i);
            Max[i] = scanner.nextInt();
            Available[i] = Max[i];
        }
    }


    public void display() {
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

        int[] request = new int[3];
        Scanner scanner = new Scanner(System.in);
        System.out.print("输入进行资源分配的Process：");
        int n = scanner.nextInt();
        for (int i = 0; i < 3; i++) {
            System.out.printf("source[%d]", i);
            request[i] = scanner.nextInt();
        }

        //对newList进行试分配，成功进行安全算法，不成功直接返回
        if (request[0] <= Available[0] && request[1] <= Available[1] && request[2] <= Available[2]) {
            Available[0] -= request[0];
            Available[1] -= request[1];
            Available[2] -= request[2];
            //更新尚需资源
            newList.get(n).allocation[0] += request[0];
            newList.get(n).allocation[1] += request[1];
            newList.get(n).allocation[2] += request[2];
            //更新已分配资源
            newList.get(n).need[0] -= request[0];
            newList.get(n).need[1] -= request[1];
            newList.get(n).need[2] -= request[2];

        } else {
            System.out.println("分配失败！");
            return;
        }
        boolean flag = safeCheck(newList);
        if(flag) {
            System.out.println("分配成功！");
        } else {
            System.out.println("没有通过安全性算法， 分配失败！");
        }
    }

    public boolean safeCheck(List<Process> newList) {
        
    }



}
