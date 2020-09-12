package wangyi;

import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String[] man = sc.nextLine().split(" ");
            sc.next();
            String[] woman = sc.nextLine().split(" ");
            sc.next();
            int num = sc.nextInt();
            for (int i = 0; i < num; i++) {
                sc.nextInt();
                sc.nextInt();
            }
            System.out.println(man.length);
        }
    }
}
