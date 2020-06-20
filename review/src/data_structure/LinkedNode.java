package data_structure;

class Node {
    int val;
    Node next;
    
    public Node (int val) {
        this.val = val;
    } 
    
}

public class LinkedNode {
    
    private Node head;
    
    int size;
    
    

    //头插法
    public void addFirst(int data) {
        if (head == null) {
            head = new Node(data);
        } else {
            Node node = new Node(data);
            node.next = head;
            head = node;
        }
        size++;
    }
    //尾插法
    public void addLast(int data) {
        if (head == null) {
            head= new Node(data);
            size++;
            return;
        } 
        Node node = new Node(data);
        Node tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
        size++;
    }
    //任意位置插入,第一个数据节点为0号下标
    public boolean addIndex(int index,int data) {
        if (index < 0 || index > size) {
            return false;
        }
        if (index == 0)  {
            addFirst(data);
            return true;
        }
        if (index == size)  {
            addLast(data);
            return true;
        }
        Node node = new Node(data);
        Node prev = head;
        for (int i = 0; i < index - 1; i++) {
            prev = prev.next;
        }
        node.next = prev.next;
        prev.next = node;
        size++;
        return true;
    }
    //查找是否包含关键字key是否在单链表当中
    public boolean contains(int key) {
        for (Node node = head; node != null; node = node.next) {
            if (node.val == key) {
                return true;
            }
        }
        return false;
    }
    //删除第一次出现关键字为key的节点
    public void remove(int key) {
        if (head.val == key) {
            head = head.next;
            return;
        }
        for (Node node = head, prev = null; node != null; node = node.next) {
            if (node.val == key) {
                prev.next = node.next;
                size--;
                return;
            }
            prev = node;
        }
    }
    //删除所有值为key的节点
    public void removeAllKey(int key) {
        while (contains(key)) {
            remove(key);
        }
    }
    //得到单链表的长度
    public int size() {
        return size;
    }
    public void display() {
        Node node = head;
        while(node != null) {
            if (node .next == null) {
                System.out.print(node.val);
            } else {
                System.out.print(node.val + " -> ");
            }
            node = node.next;
        }
        System.out.println();
    }
    public void clear() {
        head = null;
        size = 0;
    }
}

class Main1 {
    public static void main(String[] args) {
        LinkedNode list = new LinkedNode();
        list.addLast(2);
        list.addFirst(1);
        list.addIndex(2, 3);
        list.addIndex(0, 0);
        list.addIndex(2, 6);
        list.addLast(6);
        list.removeAllKey(0);
        list.display();
        System.out.println(list.size);
    }
}
