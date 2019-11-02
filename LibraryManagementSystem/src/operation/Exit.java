package operation;

import book.BookList;

public class Exit implements  IOperation{
    @Override
    public void work(BookList booklist) {
        System.exit(0);
    }
}
