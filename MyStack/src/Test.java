public class Test {
    public static void main(String[] args) {
        myStackTest();

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
}
