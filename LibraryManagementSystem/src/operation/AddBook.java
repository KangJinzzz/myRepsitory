package operation;

import book.Book;
import book.BookList;

import java.util.Scanner;

public class AddBook implements IOperation {
    @Override
    public void work(BookList bookList) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入书名：");
        String name = scanner.next();
        System.out.println("请输入书号：");
        String id = scanner.next();
        System.out.println("请输入作者：");
        String author = scanner.next();
        System.out.println("请输入价格：");
        int price = scanner.nextInt();
        System.out.println("请输入类型：");
        String type = scanner.next();
        Book book = new Book(name, id, author, price, type, false);
        int size = bookList.getSize();
        bookList.getBooks()[size] = book;
        bookList.setSize(size + 1);

    }
}
