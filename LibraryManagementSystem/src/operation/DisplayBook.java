package operation;

import book.BookList;

public class DisplayBook implements IOperation {
    @Override
    public void work(BookList bookList) {
        for (int i = 0; i < bookList.getSize(); i++) {
            System.out.println(bookList.getBooks()[i].toString());

        }
    }
}
