import java.util.Arrays;

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

    //插入新元素
    public static void put(int key, int value) {
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
                    return;
                } else {
                    cur = cur.next;
                }
            }
        }
        newNode.next = cur;
        hashMap[index] = newNode;
        size++;

    }

    //删除元素
    public static void remove(int key) {
        if(!contains(key)) {
            return;
        }
        int index = key % hashMap.length;
        if(hashMap[index].key == key) {
            hashMap[index] = hashMap[index].next;
            size--;
            return;
        }
        Node prev = hashMap[index];
        while (prev.next != null) {
            if(prev.next.key == key) {
                prev.next = prev.next.next;
                size--;
                return;
            }
        }
    }


    //查找元素
    public static Integer get(int key) {
        int index = key % hashMap.length;
        for (Node cur = hashMap[index]; cur != null; cur = cur.next) {
            if(cur.key == key) {
                return cur.value;
            }
        }
        return null;
    }


    //查看key是否包含
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

    private static void malloc() {
        Node[] newMap = new Node[2 * hashMap.length + 1];
        for (int i = 0; i < hashMap.length; i++) {
            for (Node cur = hashMap[i]; cur != null; cur = cur.next) {
                int index = cur.key % newMap.length;
                Node newNode = new Node(cur.key, cur.value);
                newNode.next = newMap[index];
                newMap[index] = newNode;
            }
        }
        hashMap = newMap;
    }



    public static void main(String[] args) {
        put(1,11);
        put(2,11);
        put(3,11);
        put(102,11);
        remove(2);
        put(1,11);

    }
}
