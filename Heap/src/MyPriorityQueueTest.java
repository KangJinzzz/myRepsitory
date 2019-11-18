public class MyPriorityQueueTest {
    public static void main(String[] args) {
        MyPriorityQueue myPriorityQueue = new MyPriorityQueue();
        myPriorityQueue.offer(1);
        myPriorityQueue.offer(2);
        myPriorityQueue.offer(3);
        myPriorityQueue.offer(4);
        myPriorityQueue.offer(5);
        myPriorityQueue.offer(6);
        myPriorityQueue.poll();
        myPriorityQueue.display();
        System.out.println(myPriorityQueue.peek());
    }
}
