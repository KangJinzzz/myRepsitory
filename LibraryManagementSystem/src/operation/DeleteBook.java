package operation;

import book.BookList;

import java.util.Scanner;

public class DeleteBook implements IOperation {
    @Override
    public void work(BookList bookList) {
        System.out.println("请输入要删除书籍的名字：");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.next();
        int size = bookList.getSize();
        for (int i = 0; i < size; i++) {
            if(name.equals(bookList.getBooks()[i].getName())) {
                bookList.getBooks()[i] = bookList.getBooks()[size - 1];
                bookList.setSize(size - 1);
                return;
            }
        }
    }
}
