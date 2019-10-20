import book.BookList;
import user.Manager;
import user.NormalUser;
import user.User;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BookList bookList = new BookList();

        User user = login();
        while(true) {
            user.menu();
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            user.doOperation(choice, bookList);
        }

    }
    public static User login() {
        while (true) {
            System.out.println("请输入您的姓名：");
            Scanner scanner = new Scanner(System.in);
            String name = scanner.next();
            System.out.println("请输入您的身份(1 管理员/2 学生)：");
            int role = scanner.nextInt();
            if (role == 1) {
                return new Manager(name);
            } else if (role == 2) {
                return new NormalUser(name);
            } else {
                System.out.println("输入信息有误请重新输入：");
            }
        }
    }

}
