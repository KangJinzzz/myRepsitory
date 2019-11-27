package Experiment_1;

public class Test {
    public static void main(String[] args) {
        Scheduling sc = new Scheduling();
        sc.add("A", 0, 4);
        sc.add("B", 1, 3);
        sc.add("C", 2, 4);
        sc.add("D", 3, 2);
        sc.add("E", 4, 4);

        sc.shortService();
        //sc.timeRotation();
        sc.display();

    }

}
