package sort;


import java.util.Arrays;

public class TopK {

    public static void main(String[] args) {
        int[] arr = new int[] {5, -1, 9, 99, 44, -78, 25, 43, 3, 1, -11};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void heapSort(int[] arr) {
        int size = arr.length - 1;
        buildHeap(arr);
        for (int i = arr.length - 1; i > 0; i--) {
            int t = arr[0];
            arr[0] = arr[i];
            arr[i] = t;
            shiftDown(arr, i - 1, 0);
        }
    }

    public static void buildHeap(int[] arr) {
        int child = arr.length - 1;
        int parent = (child - 1) / 2;
        while (parent >= 0) {
            shiftDown(arr, arr.length - 1, parent);
            parent--;
        }
    }

    public static void shiftDown(int[] arr, int size, int index) {
         int parent = index;
         int child = 2 * parent + 1;
         while (child <= size) {
             if (child + 1 <= size && arr[child + 1] > arr[child]) {
                 child += 1;
             }
             if (arr[child] > arr[parent]) {
                 int t = arr[child];
                 arr[child] = arr[parent];
                 arr[parent] = t;
                 parent = child;
                 child = 2 * parent + 1;
             } else {
                 break;
             }
         }
    }
}
