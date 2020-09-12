package wangyi;

import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int num = sc.nextInt();
            int n = sc.nextInt();
            int[] arr = new int[num];
            for (int i = 1; i < num; i++) {
                arr[i] = sc.nextInt();
            }
            System.out.println(n);
        }
    }
}
