public class testDListNode {
    public static void main(String[] args) {
        testAddFirst();
        testAddLast();
        testAddIndex();
        testRemove();
        testRemoveAll();
        testClear();
    }

    public static void testAddFirst() {
        System.out.println("测试头插：");
        DListNode list = new DListNode();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);
        list.display();
    }

    public static void testAddLast() {
        System.out.println("测试尾插");
        DListNode list = new DListNode();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.display();
    }

    public static void testAddIndex() {
        System.out.println("测试addIndex");
        DListNode list = new DListNode();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(4);
        list.addLast(4);

        list.addIndex(4,6);
        list.remove(6);

        list.display();
    }

    public static void testRemove() {
        System.out.println("测试remove");
        DListNode list = new DListNode();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(4);
        list.addLast(4);

        list.remove(1);

        list.display();
    }

    public static void testRemoveAll() {
        System.out.println("测试removeAll");
        DListNode list = new DListNode();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(4);
        list.addLast(4);

        list.removeAll(4);

        list.display();
    }

    public static void testClear() {
        System.out.println("测试clear");
        DListNode list = new DListNode();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(4);
        list.addLast(4);

        list.claer();

        list.display();
    }



}
