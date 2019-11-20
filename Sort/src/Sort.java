import javax.swing.tree.TreeNode;
import java.util.Arrays;

public class Sort {

    //插入排序
    public static void insertSort(int[] array) {
        int bound = 1;
        for (bound = 1; bound < array.length; bound++) {
            int temp = array[bound];
            int cur = bound - 1;
            for (; cur >= 0; cur--) {
                if (array[cur] > temp) {
                    array[cur + 1] = array[cur];
                } else {
                    break;
                }
            }
            array[cur + 1] = temp;
        }
    }

    //希尔排序
    public static void shellSort(int[] array) {
        int gap = array.length;
        while(gap > 1) {
            shellSortGap(array, gap);
            gap /= 2;
        }
        shellSortGap(array, 1);
    }
    private static void shellSortGap(int[] array, int gap) {
        for(int bound = 1; bound < array.length; bound++) {
            int temp = array[bound];
            int cur = bound - gap;
            for(; cur >= 0; cur -= gap) {
                if(array[cur] > temp) {
                    array[cur + gap] = array[cur];
                } else {
                    break;
                }
            }
            array[cur + gap] = temp;
        }
    }

    //选择排序
    public static void selectSort(int[] array) {
        // 先创建一个 bound 变量, 表示边界
        // [0, bound) 已排序区间
        // [bound, size) 待排序区间
        for (int bound = 0; bound < array.length; bound++) {
            // 使用打擂台的方式找到待排序区间中的最小值
            // bound 位置的元素就是擂台
            for (int cur = bound + 1; cur < array.length; cur++) {
                if (array[cur] < array[bound]) {
                    // 打败擂主, 就和擂主交换
                    swap(array, cur, bound);
                }
            }
        }
    }

    //堆排序
    public static void heapSort(int[] array) {
        createHeap(array);
        for (int i = 0; i < array.length - 1; i++) {
            swap(array, 0, array.length - 1 - i);
            shiftDown(array, array.length - 1 - i, 0);
        }
    }
    private static void createHeap(int[] array) {
        for (int i = (array.length - 1) / 2; i >= 0; i--) {
            shiftDown(array, array.length, i);
        }
    }
    private static void shiftDown(int[] array,int size, int index) {
        int parent = index;
        int child = 2 * index + 1;
        while (child < size) {
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

    //冒泡排序
    public static void bubbleSort(int[] array) {
        boolean isSorted = true;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if(array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                    isSorted = false;
                }
            }
            if(isSorted) {
                break;
            }
        }
    }
    public static void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    public static void main(String[] args) {
        int[] array = {2, 4, 1, 6, 7, 9, 0, 5, 3, 8};
        //insertSort(array);
        //shellSort(array);
        //selectSort(array);
        //heapSort(array);
        bubbleSort(array);
        System.out.println(Arrays.toString(array));
    }
}

