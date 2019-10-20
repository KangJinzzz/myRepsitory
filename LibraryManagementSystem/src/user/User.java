package user;

import book.BookList;
import operation.IOperation;

abstract public class User {
    protected String name;
    protected IOperation[] operations;

    public User(String name) {
        this.name = name;
    }
    //目录
    abstract public void menu();

    public void doOperation(int choice, BookList bookList) {
        operations[choice].work(bookList);
    }

}
