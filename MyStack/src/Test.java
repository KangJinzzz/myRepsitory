public class Test {
    public static void main(String[] args) {
        myQueueTest();

    }

    public static void myStackTest() {
        MyStack myStack = new MyStack();
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        myStack.push(4);
        int size = myStack.size() - 1;
        for (int i = 0; i < size; i++) {
            int ret = myStack.pop();
            System.out.println(ret);
        }
        Integer ret = myStack.peek();
        System.out.println(ret);
    }

    public static void myQueueTest() {
        MyQueue myQueue = new MyQueue();
        myQueue.offer(1);
        myQueue.offer(2);
        myQueue.offer(3);
        myQueue.offer(4);
        int size = myQueue.size() ;
        for (int i = 0; i < size; i++) {
            int ret = myQueue.poll();
            System.out.println(ret);
        }
        Integer ret = myQueue.peek();
        System.out.println(ret);
    }
}
