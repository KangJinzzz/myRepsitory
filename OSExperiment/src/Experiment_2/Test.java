package Experiment_2;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MemoryAllocation mem = new MemoryAllocation();
        mem.initialize();
        mem.display();
        Node prev = null;
        while(true) {
            System.out.println("输入要处理作业的大小：");
            int dealSize = scanner.nextInt();
            mem.menu();
            int choice = scanner.nextInt();
            if(choice == 0) {
                System.exit(0);
            } else if (choice == 1) {
                prev = mem.BF(dealSize);
                mem.display();

            } else if (choice == 2) {
                prev = mem.WF(dealSize);
                mem.display();

            } else if (choice == 3) {
                prev = mem.FF(dealSize);
                mem.display();

            } else if (choice == 4) {
                mem.NF(dealSize, prev);
                mem.display();
            } else {
                System.out.println("输入错误！");
            }
        }
    }



}
