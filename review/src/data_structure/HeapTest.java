package data_structure;


import java.util.Arrays;
//大堆的所有节点的孩子节点都小于该节点
//父节点的下标为：i ,  则左孩子下标为：2 * i + 1, 右孩子下标为：2 * i + 2
//知道孩子节点小标为 i， 父节点下标为：(i - 1) / 2
public class HeapTest {

    public static void main(String[] args) {
        int[] arr = new int[] {1,5,3,8,7,6};
        buildHeap(arr, arr.length);
        System.out.println(Arrays.toString(arr));
    }

    private static void buildHeap(int[] arr, int size) {
        //第一个不是叶节点的节点
        int i = (size - 1) / 2;
        for (; i >= 0; i--) {
            shiftDown(arr, arr.length, i);
        }
    }

    public static void shiftDown(int[] arr, int size, int index) {
        int parent = index;
        if (parent < 0 || parent >= arr.length) return;
        //左孩子
        int child = parent * 2 + 1;
        while (child < size) {
            if (child + 1 < size && arr[child] < arr[child + 1]) {
                child = child + 1;
            }

            if (arr[parent] < arr[child]) {
                swap(arr, parent, child);
            } else {
                break;
            }
            parent = child;
            child = 2 * parent + 1;
        }
    }



    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
