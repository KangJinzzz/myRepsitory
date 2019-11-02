class Node {
    public int val = 0;
    public Node next;

    public Node(int val) {
        this.val = val;
    }
}
public class myQueue {
    private Node head = null;
    private Node tail = null;
    private int size = 0;

    //入队
    public void offer(int x) {
        Node node = new Node(x);

        if(head == null) {
            head = node;
            tail = head;
            size++;
            return;
        }
        tail.next = node;
        tail = tail.next;
        size++;
        return;
    }
    //出队
    public Integer poll() {
        if(size == 0) {
            return null;
        }
        Integer ret = head.val;
        head = head.next;
        size--;
        return ret;
    }
    //取队首元素
    public Integer peek() {
        if(size == 0) {
            return null;
        }
        return head.val;
    }
    //判空
    public boolean isEmpty() {
        return size == 0;
    }
    public int size() {
        return size;
    }

}
