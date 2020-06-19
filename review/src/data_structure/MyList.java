package data_structure;


//动态顺序表，可自行扩招大小
public class MyList {
    private int[] arr;
    //顺序表可容纳元素的大小
    private int capacity;
    //顺序表当前存储元素的个数
    private int size;
    //扩展因子，当当前元素的数量达到总容量的 FACTOR 时，进行扩容
    private static final double FACTOR = 0.75;
    //扩容时，在当前容量的基础 * 扩容比率
    private static final double EXTEND_RATIO = 1.5;

    public MyList() {
        arr = new int[20];
        capacity = 20;
    }

    public MyList(int capacity) {
        arr = new int[capacity];
        this.capacity = capacity;
    }

    public void add(int index, int num) {
        if (index < 0 || index > size) {
            throw new RuntimeException("下标越界，添加失败");
        }
        //判断是否需要扩容
        if ((double)size / capacity >= FACTOR) {
            realloc();
        }
        if (index == size) {
            arr[size++] = num;
        } else {
            for (int i = size; i > index; i--) {
                arr[i] = arr[i - 1];
            }
            arr[index] = num;
            size++;
        }
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new RuntimeException("下标越界，删除失败");
        }
        for (int i = index; i < size - 1; i++) {
            arr[i] = arr[i + 1];
        }
        size--;
    }

    public int search(int vaule) {
        for (int i = 0; i < size; i++) {
            if (arr[i] == vaule) {
                return i;
            }
        }
        return -1;
    }

    public int getPos(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return arr[index];
    }

    public void setPos(int index, int value) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        arr[index] = value;
    }

    public void add(int num) {
        //判断是否需要扩容
        if ((double)size / capacity >= FACTOR) {
            realloc();
        }
        arr[size++] = num;
    }

    public int getSize() {
        return size;
    }


    public void display() {
        System.out.print("[");
        for (int i = 0; i < size; i++) {
            if (i == size - 1) {
                System.out.print(arr[i]);
            } else {
                System.out.print(arr[i] + ", ");
            }
        }
        System.out.println("]");
    }

    private void realloc() {
        capacity += (capacity * EXTEND_RATIO);
        int[] newArr = new int[capacity];
        System.arraycopy(arr, 0, newArr, 0, size);
        arr = newArr;
    }
}

class Main {
    public static void main(String[] args) {
        MyList list = new MyList(1);
        list.add(1);
        list.add(0, 2);
        list.add(3);
        list.remove(1);
        System.out.println(list.getSize());
        list.display();
    }
}
