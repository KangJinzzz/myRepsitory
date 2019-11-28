package Experiment_1;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Scheduling sc = new Scheduling();
        sc.add2();
        sc.display();

        while(true) {
            sc.menu();
            int choice = scanner.nextInt();
            if(choice == 0) {
                System.exit(0);
            } else if (choice == 1) {
                sc.firstCome();
                sc.display();

            } else if (choice == 2) {
                sc.shortService();
                sc.display();

            } else if (choice == 3) {
                sc.timeRotation();
                sc.display();

            } else {
                System.out.println("输入错误！");
            }
        }





    }




}
