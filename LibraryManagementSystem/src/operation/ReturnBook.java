package operation;

import book.BookList;

import java.util.Scanner;

public class ReturnBook implements IOperation {
    @Override
    public void work(BookList bookList) {
        System.out.println("请输入要归还的书的名字：");
        Scanner scanner = new Scanner(System.in);
        String bookName = scanner.next();
        for (int i = 0; i < bookList.getSize(); i++) {
            if(bookName.equals(bookList.getBooks()[i].getName()) && bookList.getBooks()[i].isBorrowed()) {
                bookList.getBooks()[i].setBorrowed(false);
                System.out.println("还书成功！");
                System.out.println(bookList.getBooks()[i].toString());
                return;
            }
        }
        System.out.println("还书失败！");
    }
}
