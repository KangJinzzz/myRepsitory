package operation;

import book.BookList;

public class DisplayBook implements IOperation {
    @Override
    public void work(BookList bookList) {
        System.out.println("共有" + bookList.getSize() + "本书！");
        for (int i = 0; i < bookList.getSize(); i++) {
            System.out.println(bookList.getBooks()[i].toString());
        }
    }
}
