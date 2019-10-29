import java.util.ArrayList;
import java.util.List;

class Card {
    private String suit;
    private String points;

    public Card(String suit, String points) {
        this.suit = suit;
        this.points = points;
    }

//    public String myToString() {
//        return "[" + suit + " " + points + ","
//    }


}

public class Poker {
    public List<Card> list = new ArrayList<>();

    public Poker() {
        String[] suit = {"♥", "♦", "♠", "♣"};
        String[] points = {"A", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                list.add(new Card(suit[i], points[j]));
            }
        }
        System.out.println(list);
    }


}
