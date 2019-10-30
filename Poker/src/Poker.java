import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Card {
    private String suit;
    private String points;

    public Card(String suit, String points) {
        this.suit = suit;
        this.points = points;
    }

    public String myToString() {
        return suit + " " + points;
    }


}

public class Poker {
    private List<Card> list = new ArrayList<>();

    public Poker() {
        String[] suit = {"♥", "♦", "♠", "♣"};
        String[] points = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                list.add(new Card(suit[i], points[j]));
            }
        }
    }

    //洗牌
    public void shuffle() {
        Collections.shuffle(list);
    }


    public void display() {
        System.out.print("[");

        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i).myToString());
            if(i != list.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
        System.out.println(list.size());
    }
}


