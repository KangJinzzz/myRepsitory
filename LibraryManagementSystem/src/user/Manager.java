package user;

import operation.*;

public class Manager extends User {
    public Manager(String name) {
        super(name);

        operations = new IOperation[] {
                new Exit(), new FindBook(), new AddBook(), new DeleteBook(), new DisplayBook()
        };
    }

    @Override
    public void menu() {
        System.out.println("============");
        System.out.println("hello " + name);
        System.out.println("1. 查找书籍");
        System.out.println("2. 增加书籍");
        System.out.println("3. 删除书籍");
        System.out.println("4. 打印书库");
        System.out.println("0.退出");
        System.out.println("============");
        System.out.println("请输入您的操作：");

    }
}
