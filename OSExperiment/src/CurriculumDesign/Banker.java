package CurriculumDesign;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


class Process {
    //该进程对各类资源需要的最大数目
    public int[] max = new int[3];
    //该进程各类资源已分配的资源数目
    public int[] allocation = new int[3];
    //该进程各类资源尚需的资源数目
    public int[] need = new int[3];

    public Process() {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < max.length; i++) {
            System.out.printf("max[%d]:", i);
            max[i] = scanner.nextInt();
            need[i] = max[i];
        }
    }
}

public class Banker {
    public List<Process> list = new ArrayList<Process>();

    public int[] Max = new int[3];
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


}
