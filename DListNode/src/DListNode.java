//有环双向链表
class DLinkedNode {
    public int val = 0;
    public DLinkedNode prev = null;
    public DLinkedNode next = null;

    public DLinkedNode(int val) {
        this.val = val;
    }
}

public class DListNode {
    DLinkedNode head = new DLinkedNode(-1);

    public DListNode() {

        head.next = head;
        head.prev = head;
    }

    //头插
    public void addFirst(int data) {
        DLinkedNode newNode = new DLinkedNode(data);
        DLinkedNode next = head.next;

        head.next = newNode;
        newNode.prev = head;
        next.prev = newNode;
        newNode.next = next;
    }
    //尾插
    public void addLast(int data) {
        DLinkedNode newNode = new DLinkedNode(data);
        DLinkedNode prev = head.prev;

        newNode.next = head;
        head.prev = newNode;
        prev.next = newNode;
        newNode.prev = prev;


    }

    //插入新节点在index位置
    public void addIndex(int index, int data) {
        int size = size();
        if(index < 0 || index > size) {
            return;
        }
        if(index == 0) {
            addFirst(data);
            return;
        }
        if(index == size) {
            addLast(data);
            return;
        }
        DLinkedNode newNode = new DLinkedNode(data);
        DLinkedNode next = findNode(index);
        DLinkedNode prev = next.prev;

        prev.next = newNode;
        newNode.prev = prev;
        newNode.next = next;
        next.prev = newNode;

    }
    //判断关键字key是否在链表中
    public boolean contains(int key) {
        DLinkedNode node = head.next;
        for(; node != head; node= node.next) {
            if(node.val == key) {
                return true;
            }
        }
        return false;
    }

    //删除第一次出现key的结点
    public void remove(int key) {
        if(!contains(key)) {
            return;
        }
        DLinkedNode toRemove = head.next;
        for( ; toRemove != head; toRemove = toRemove.next) {
            if(toRemove.val == key) {
                DLinkedNode prev = toRemove.prev;
                DLinkedNode next = toRemove.next;
                prev.next = next;
                next.prev = prev;
                return;
            }
        }

    }

    //删除所有值为key的结点
    public void removeAll(int key) {
        DLinkedNode toRemove = head.next;

        while(true) {
            if(!contains(key)) {
                return;
            }
            for( ; toRemove != head; toRemove = toRemove.next) {
                if(toRemove.val == key) {
                    DLinkedNode prev = toRemove.prev;
                    DLinkedNode next = toRemove.next;
                    prev.next = next;
                    next.prev = prev;
                    toRemove = next;
                }
            }
        }
    }

    //清空链表
    public void claer() {
        head.next = head;
        head.prev = head;
    }

    //找到下标为index的结点
    public DLinkedNode findNode(int index) {
        DLinkedNode node = head.next;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }
    //求链表长度
    public int size() {
        int size = 0;
        DLinkedNode cur = head.next;
        for(; cur != head; cur = cur.next) {
            size++;
        }
        return size;
    }
    //打印
    public void display() {
        DLinkedNode cur = head.next;
        System.out.print("正向：[");
        for(cur = head.next; cur != head; cur = cur.next){
            System.out.print(cur.val);
            if(cur.next != head) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
        System.out.print("反向：[");
        for(cur = head.prev; cur != head; cur = cur.prev){
            System.out.print(cur.val);
            if(cur.prev != head) {
                System.out.print(", ");
            }
        }
        System.out.println("]");

    }
}