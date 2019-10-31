public class Test {
    public static void main(String[] args) {
        Poker poker = new Poker();
        poker.shuffle();
        Player players = new Player();
        players.getPoker(poker);
        players.getPersonList().get(0).display();
    }
}
