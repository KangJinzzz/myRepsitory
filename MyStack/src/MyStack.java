public class MyStack {
    private int[] arr = new int[100];
    private int size = 0;

    //入栈
    public void push(int x) {
        if(size == arr.length) {
            malloc();
        }
        arr[size] = x;
        size++;
    }
    //出栈
    public Integer pop () {
        if(size == 0) {
            return null;
        }
        int ret = arr[size - 1];
        size--;
        return ret;
    }
    //取栈顶元素
    public Integer peek() {
        if(size == 0) {
            return null;
        }
        return arr[size - 1];
    }
    //判空
    public boolean isEmpty() {
        return size == 0;
    }
    //扩容
    private int[] malloc() {
        int newLength = arr.length * 2;
        int[] newArr = new int[newLength];
        newArr = arr.clone();
        return newArr;
    }


    public int getSize() {
        return size;
    }

    public int size() {
        return this.size;
    }
}
