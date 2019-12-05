package Experiment_2;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MemoryAllocation mem = new MemoryAllocation();
        mem.display();

        while(true) {
            System.out.println("输入要处理作业的大小：");
            int dealSzie = scanner.nextInt();
            mem.menu();
            int choice = scanner.nextInt();
            if(choice == 0) {
                System.exit(0);
            } else if (choice == 1) {
                mem.BF(dealSzie);
                mem.display();

            } else if (choice == 2) {
                mem.display();

            } else if (choice == 3) {
                mem.display();

            } else if (choice == 4) {
                mem.display();
            } else {
                System.out.println("输入错误！");
            }
        }
    }



}
