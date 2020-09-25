package sort;

import java.util.Arrays;

import static sort.Sort2.mergeSortHelper2;


public class Sort2 {
    public static void main(String[] args) {
        int[] arr = new int[] {4, 6, 13, 1, 9, 6, 8, 21, 32, 17, -6, 0};
        int[] arr1 = new int[] {1,3,5, 2, 4 ,6};
//        merge(arr1, 0, 3, 6);
//        System.out.println(Arrays.toString(arr1));
        heapSort2(arr);
        System.out.println(Arrays.toString(arr));
    }

    //每次从无序区间取一个数字插入有序区间对应的位置中
//    public static void insertSort(int[] arr) {
//        for (int i = 1; i < arr.length; i++) {
//            int tmp = arr[i];
//            int j = i - 1;
//            for (; j >= 0; j--) {
//                if (tmp < arr[j]) {
//                    arr[j + 1] = arr[j];
//                } else {
//                    break;
//                }
//            }
//            arr[j + 1] = tmp;
//        }
//    }

    public static void shellSort(int[] arr) {
        int gap = arr.length;
        while (gap > 1) {
            shellSortHelper(arr, gap);
            gap /= 2;
        }
        shellSortHelper(arr, 1);
    }
    public static void shellSortHelper(int[] arr, int gap) {
        for (int i = 1; i < arr.length; i++) {
            int tmp = arr[i];
            int j = i - gap;
            for (; j >= 0; j -= gap) {
                if (tmp < arr[j]) {
                    arr[j + gap] = arr[j];
                } else {
                    break;
                }
            }
            arr[j + gap] = tmp;
        }
    }

//    public static void selectSort(int[] arr) {
//        for (int i = 0; i < arr.length; i++) {
//            int min = i;
//            for (int j = i + 1; j < arr.length; j++) {
//                if (arr[j] < arr[min]) {
//                    min = j;
//                }
//            }
//            swap(arr, i, min);
//        }
//    }

    //堆排序
    public static void heapSort(int[] arr) {
        //先建堆
        createHeap(arr);
        for (int i = arr.length - 1; i >= 0; i--) {
            swap(arr, 0, i);
            shiftDown(arr, i, 0);
        }
    }

    private static void createHeap(int[] arr) {
        //从第一个非叶子节点开始向下调整
        int i = (arr.length - 1) / 2;
        for (; i >= 0; i--) {
            shiftDown(arr, arr.length, i);
        }
    }

    /**
     *
     * @param arr
     * @param size  代表数组中被视为堆数据的个数
     * @param index  代表要调整位置的下标
     */
    private static void shiftDown(int[] arr, int size, int index) {
        int parent = index;
        int child = 2 * parent + 1;
        while (child < size) {
            if (child + 1 < size && arr[child + 1] < arr[child]) {
                child++;
            }
            if (arr[parent] > arr[child]) {
                swap(arr, parent, child);
            } else {
                break;
            }
            parent = child;
            child = 2 * parent + 1;
        }
    }

    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            boolean flag = true;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    flag = false;
                }
            }
            if (flag) return;
        }
    }

    public static void quickSort(int[] arr) {
        partion(arr, 0, arr.length - 1);
    }

    private static void partion(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int flag = arr[left];
        int i = left;
        int j = right;
        while (i < j) {
            while (i < j && arr[j] >= flag) {
                j--;
            }
            while (i < j && arr[i] <= flag) {
                i++;
            }
            swap(arr, i, j);
        }
        swap(arr, left, i);
        partion(arr, left, i - 1);
        partion(arr, i + 1, right);
    }

    public static void mergeSort(int[] arr) {
        mergeSortHelper(arr, 0, arr.length);
    }

    public static void mergeSortHelper(int[] arr, int left, int right) {
        if (left >= right - 1) {
            return;
        }
        int mid = (left + right) / 2;
        mergeSortHelper(arr, left, mid);
        mergeSortHelper(arr, mid, right);
        merge(arr, left, mid, right);
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int[] tmp = new int[right - left];
        int index = 0;
        int i = left;
        int j = mid;
        while (i < mid && j < right) {
            if (arr[i] <= arr[j]) {
                tmp[index++] = arr[i++];
            } else {
                tmp[index++] = arr[j++];
            }
        }
        while (i < mid) {
            tmp[index++] = arr[i++];
        }
        while(j < right) {
            tmp[index++] = arr[j++];
        }
        for(index = 0; index < tmp.length; index++) {
            arr[left++]  = tmp[index];
        }
    }

    public static void mergeSort2(int[] arr) {
        if (arr.length == 0) {
            return;
        }
        mergeSortHelper2(arr, 0, arr.length);

    }

    public static void mergeSortHelper2(int[] arr, int left, int right) {
        if (left >= right - 1) {    //剩一个元素了，不用再分
            return;
        }
        int mid = (left + right) / 2;
        mergeSortHelper(arr, left, mid);
        mergeSortHelper(arr, mid, right);
        merge(arr, left, mid, right);
    }

    public static void merge2(int[] arr, int left, int mid, int right) {
        int[] tmp = new int[right - left];
        int index = 0;
        int i = left;
        int j = mid;
        while (i < mid && j < right) {
            if (arr[i] <= arr[j]) {
                tmp[index++] = arr[i++];
            } else {
                tmp[index++] = arr[j++];
            }
        }
        while (i < mid) {
            tmp[index++] = arr[i++];
        }
        while (j < right) {
            tmp[index++] = arr[j++];
        }
        for (index = 0; index < tmp.length; index++) {
            arr[left + index] = tmp[index];
        }

    }

    public static void shellSort2(int[] arr) {
        int gap = arr.length;
        while (gap > 1) {
            shellSortHelper2(arr, gap);
            gap /= 2;
        }
        shellSortHelper2(arr, 1);
    }

    public static void shellSortHelper2(int[] arr, int gap) {
        for (int i = 1; i < arr.length; i++) {
            int tmp = arr[i];
            int j = i - gap;
            for (; j >= 0; j -= gap) {
                if (arr[j] > tmp) {
                    arr[j + gap] = arr[j];
                } else {
                    break;
                }
            }
            arr[j + gap] = tmp;
        }
    }

    public static void heapSort2(int[] arr) {
        createHeap2(arr);
        for (int i = arr.length - 1; i > 0; i--) {
            swap(arr, i, 0);
            shiftDown2(arr, 0, i - 1);
        }
    }

    public static void createHeap2(int[] arr) {
        int parent = (arr.length - 2) / 2;
        for (; parent >= 0; parent--) {
            shiftDown2(arr, parent, arr.length - 1);
        }
    }

    public static void shiftDown2(int[] arr, int index, int size) {
        int parent = index;
        int child = parent * 2 + 1;
        while (child <= size) {
            if (child + 1 <= size && arr[child + 1] > arr[child]) {
                child += 1;
            }
            if (arr[parent] < arr[child]) {
                swap(arr, parent, child);
                parent = child;
                child = 2 * parent + 1;
            } else {
                break;
            }
        }
    }


    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
