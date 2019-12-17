package CurriculumDesign;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Banker banker = new Banker();
        banker.display();

        while (true) {
            menu();
            System.out.println("选择功能");
            int choice = scanner.nextInt();

            if (choice == 1) {
                banker.bankerAlgorithm();
                banker.display();
            } else if (choice == 2) {
                banker.safeCheck(banker.list);
            } else if (choice == 0){
                System.exit(0);
            } else {
                System.out.println("输入错误, 重新输入！");
            }
        }
    }

    public static void menu() {
        System.out.println();
        System.out.println("==========");
        System.out.println("1.资源分配");
        System.out.println("2.安全检测");
        System.out.println("0.exit");
        System.out.println("==========");

    }
}
