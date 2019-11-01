import java.util.ArrayList;
import java.util.List;

class Person {
    private List<Card> list = new ArrayList<>();

    public List<Card> getList() {
        return list;
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
    }
}

public class Player {
    private List<Person> personList = new ArrayList<>();


    public Player() {
        for (int i = 0; i < 3; i++) {
            personList.add(new Person());

        }
    }
    public void getPoker( Poker poker) {
        List<Card> list = poker.getList();
        int index = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < personList.size(); j++) {
                personList.get(j).getList().add(list.get(index));
                index++;
            }
        }
        System.out.println("发牌成功！");
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void display() {

        for (int i = 0; i < personList.size(); i++) {
            System.out.print("player" + i + ": ");
            personList.get(i).display();
        }
    }
}