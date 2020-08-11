package sort;

public class Heap {
    public int[] arr;
    public int size = 0;

    public Heap(int size) {
        arr = new int[size];
    }

    public void add(int val) {
        arr[size++] = val;
        shiftUp(arr, size - 1);
    }

    public int poll() {
        if (size <= 0) {
            return -1;
        }
        int res = arr[0];
        arr[0] = arr[size - 1];
        size--;
        shiftDown(arr, size - 1, 0);
        return res;
    }

    public int peek() {
        if (size <= 0) {
            return -1;
        }
        return arr[0];
    }

    public void shiftUp(int[] arr, int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (arr[index] > arr[parent]) {
                int t = arr[parent];
                arr[parent] = arr[index];
                arr[index] = t;
                index = parent;
            } else {
                break;
            }
        }
    }

    public void shiftDown(int[] arr, int size, int index) {
        int parent = index;
        int child = parent * 2 + 1;
        while (child <= size) {
            if (child + 1 <= size && arr[child + 1] > arr[child]) {
                child += 1;
            }
            if (arr[parent] < arr[child]) {
                int t = arr[parent];
                arr[parent] = arr[child];
                arr[child] = t;
                parent = child;
                child = parent * 2 + 1;
            } else {
                break;
            }
        }
    }
    public void display(){
        for (int i = 0; i < size; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.println();
    }


    public static void main(String[] args) {
        Heap heap = new Heap(10);
        heap.display();
        heap.add(9);
        heap.add(2);
        heap.add(16);
        heap.add(3);
        heap.add(45);
        heap.add(-8);
        heap.display();
        heap.add(99);
        heap.display();
        System.out.println(heap.poll());
        heap.display();
        System.out.println(heap.poll());
        heap.display();
        System.out.println(heap.poll());
        heap.display();
    }


}
