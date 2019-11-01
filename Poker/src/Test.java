public class Test {
    public static void main(String[] args) {
        Poker poker = new Poker();
        //洗牌
        poker.shuffle();
        poker.display();
        //创建玩家
        Player players = new Player();
        //发牌
        players.getPoker(poker);
        //打印手牌
        players.display();
    }
}
