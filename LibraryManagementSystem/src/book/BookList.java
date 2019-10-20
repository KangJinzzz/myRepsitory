package book;

public class BookList {
    Book[] books = new Book[100];
    private int size = 0;

    public BookList() {
        books[0] = new Book("雪中悍刀行", "001", "烽火戏诸侯", 1000, "仙侠", false);
        books[1] = new Book("剑来", "002", "烽火戏诸侯", 800, "武侠", false);
        books[2] = new Book("三国演义", "003", "罗贯中", 200, "古典名著", false);

        size = 3;
    }
}
