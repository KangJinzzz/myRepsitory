package operation;


import book.BookList;

import java.util.Scanner;

public class BorrowBook implements IOperation {
    @Override
    public void work(BookList bookList) {
        System.out.println("请输入要借的书的名字：");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.next();
        for (int i = 0; i < bookList.getSize(); i++) {
            if(name.equals(bookList.getBooks()[i].getName()) && !bookList.getBooks()[i].isBorrowed()) {
                bookList.getBooks()[i].setBorrowed(true);
                System.out.println("借书成功！");
                System.out.println(bookList.getBooks()[i].toString());
                return;
            }
        }
        System.out.println("借书失败！");
    }
}
