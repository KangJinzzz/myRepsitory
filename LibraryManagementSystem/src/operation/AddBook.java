package operation;

import book.Book;
import book.BookList;

public class AddBook implements IOperation {
    @Override
    public void work(BookList bookList) {
        System.out.println("请输入书名：");
        System.out.println("请输入书号：");
        System.out.println("请输入作者：");
        System.out.println("请输入价格：");
        System.out.println("请输入类型：");

    }
}
