import javax.swing.tree.TreeNode;
import java.util.Arrays;
import java.util.Stack;

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
        // [bound, array.length) 待排序区间
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

    //快速排序
    //递归版本
    public static void quickSort(int[] array) {
        quickSortInternal(array, 0, array.length - 1);
    }
    private static void quickSortInternal(int[] array, int left, int right) {
        if(left >= right) {
            return;
        }
        int pivotIndex = partition(array, left, right);
        quickSortInternal(array, left, pivotIndex - 1);
        quickSortInternal(array, pivotIndex + 1, right);
    }
    private static int partition(int[] array, int left, int right) {
        int i = left;
        int j = right;
        int pivot = array[j];
        while(i < j) {
            while (i < j && array[i] <= pivot) {
                i++;
            }
            while (i < j && array[j] >= pivot) {
                j--;
            }
            swap(array, i, j);
        }
        swap(array, i, right);
        return i;
    }
    //非递归版
    public static void quickSort2(int[] array) {
        Stack<Integer> stack = new Stack<>();
        stack.push(array.length - 1);
        stack.push(0);
        while(!stack.isEmpty()) {
            int left = stack.pop();
            int right = stack.pop();
            if(left >= right) {
                continue;
            }
            int pivotIndex = partition(array, left, right);

            stack.push(right);
            stack.push(pivotIndex + 1);

            stack.push(pivotIndex - 1);
            stack.push(left);
        }
    }

    //归并排序
    public static void mergeSort(int[] array) {
        // 后两个参数表示要进行归并排序的区间.
        // [0, array.length)
        // new 足够大的数组, 把这个数组作为缓冲区传给
        // 递归函数
        mergeSortHelper(array, 0, array.length);
    }

    private static void mergeSortHelper(int[] array, int left, int right) {
        // [left, right) 构成了要去进行归并排序的区间
        // 如果区间为空区间, 或者只有一个元素, 都不用排序
        if (left >= right || right -  left == 1) {
            // 空区间或者区间只有一个元素, 都不需要进行归并排序
            return;
        }
        // 使用类似后序遍历的方式.
        // 先把当前的待排序区间拆成两半,
        // 递归的对这两个子区间进行归并排序, 保证两个区间有序之后
        // 再进行合并
        int mid = (left + right) / 2;
        // [left, mid)
        // [mid, right)
        mergeSortHelper(array, left, mid);
        mergeSortHelper(array, mid, right);
        merge(array, left, mid, right);
    }

    private static void merge(int[] array, int left,
                              int mid, int right) {
        // 创建一段临时空间辅助进行归并
        // 这个临时空间的长度应该是两个待归并区间的长度之和
        int length = right - left;
        int[] output = new int[length];
        // 这个变量保存着当前 output 中的末尾元素的下标
        int outputIndex = 0;
        // i 和 j 是用来遍历两个区间的辅助变量
        // [left, mid)
        // [mid, right)
        int i = left;
        int j = mid;
        while (i < mid && j < right) {
            // 此处的 if 条件必须要 <= , 否则没法保证稳定性
            if (array[i] <= array[j]) {
                // i 对应的元素比 j 小
                // 就把 i 对应的元素插入到 output 末尾
                output[outputIndex++] = array[i++];
            } else {
                output[outputIndex++] = array[j++];
            }
        }
        // 上面的循环结束之后, 两个区间至少有一个是遍历完了的.
        // 就把剩下的区间的内容直接拷贝到 output 中即可.
        while (i < mid) {
            output[outputIndex++] = array[i++];
        }
        while (j < right) {
            output[outputIndex++] = array[j++];
        }

        // 最后一步, 把 output 中的元素拷贝回原来的区间
        for (int k = 0; k < length; k++) {
            array[left + k] = output[k];
        }
    }
    //非递归版本
    public static void mergeSort2(int[] array) {
        // 借助下标相关的规律来进行分组.
        // 初始情况下, 每个元素单独作为一组
        // [0] [1]    [2] [3]     [4] [5]
        // [0, 1] 和 [2, 3] 合并. [4, 5]  和 [6, 7] 区间合并
        // [0, 1, 2, 3]  [4, 5, 6, 7]
        for (int gap = 1; gap < array.length; gap *= 2) {
            for (int i = 0; i < array.length; i += 2 * gap) {
                // 这个循环负责在 gap 为指定值的情况下
                // 把所有的区间进行归并
                // 针对当前的 i, 也能划分出两个需要进行归并的区间
                // [beg, mid)
                // [mid, end)
                int beg = i;
                int mid = i + gap;
                int end = i + 2 * gap;
                if (mid > array.length) {
                    mid = array.length;
                }
                if (end > array.length) {
                    end = array.length;
                }
                merge(array, beg, mid, end);
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
        int[] arr2 = {0, 1, 2, 3};
        //insertSort(array);
        //shellSort(array);
        //selectSort(array);
        //heapSort(array);
        //bubbleSort(array);
        //quickSort2(array);
        quickSort(arr2);
        System.out.println(Arrays.toString(arr2));
    }
}

