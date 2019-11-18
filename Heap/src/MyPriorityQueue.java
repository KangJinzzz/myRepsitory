public class MyPriorityQueue {
    private int[] array = new int[100];
    private int size = 0;

    public void offer(int val) {
        if(size >= array.length) {
            return;
        }
        array[size] = val;
        size++;
        shiftUp(array, size - 1);
    }

    public Integer poll() {
        if(size == 0) {
            return null;
        }
        int ret = array[0];
        array[0] = array[size - 1];
        size--;
        shiftDown(array, size, 0);
        return ret;
    }

    public Integer peek() {
        if(size == 0) {
            return null;
        }
        return array[0];
    }



    //向上调整
    public static void shiftUp(int[] array, int index) {
        int parent = (index - 1) / 2;
        int child = index;

        while (parent >= 0) {
            if(array[parent] < array[child]) {
                swap(array, parent ,child);
            } else {
                break;
            }
            child = parent;
            parent = (child - 1) / 2;
        }
    }

    public void shiftDown(int[] array, int size, int index) {
        int parent = index;
        int child = 2 * index + 1;
        while(child < size) {
            if(child + 1 < size && array[child + 1] > array[child]) {
                child++;
            }
            if(array[parent] < array[child]) {
                swap(array, parent, child);
            } else {
                break;
            }
            parent = child;
            child = 2 * parent + 1;
        }

    }

    private static void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    public void display() {
        System.out.print("[");
        for (int i = 0; i < size; i++) {
            System.out.print(array[i]);
            if(i != size - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}
