class Node {
    public int key = 0;
    public int value = 0;
    public Node next = null;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}


public class MyHashMap {
    public static Node[] hashMap = new Node[101];
    public static int size = 0;
    public static final double FACTOR = 0.75;


    public static boolean put(int key, int value) {
        if(size / hashMap.length > FACTOR) {
            malloc();
        }
        Node newNode = new Node(key, value);
        int index = key % hashMap.length;
        Node cur = hashMap[index];
        //如果key已存在，则直接覆盖
        if(contains(key)) {
            while(cur != null) {
                if(cur.key == key) {
                    cur.value = value;
                    return true;
                } else {
                    cur = cur.next;
                }
            }
        }
        newNode.next = cur;
        hashMap[index] = newNode;
        size++;
    }

    public static boolean contains(int key) {
        int index = key % hashMap.length;
        Node cur = hashMap[index];
        while(cur != null) {
            if(cur.key == key) {
                return true;
            } else {
                cur = cur.next;
            }
        }
        return false;
    }

    public static void malloc() {
        Node[] newMap = new Node[2 * hashMap.length + 1];
    }
}
