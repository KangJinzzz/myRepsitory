package sort;

import java.util.Arrays;

public class SortTest {
    public static void main(String[] args) {
        int[] arr = new int[] {4, 6, 13, 1, 9, 6, 8, 21, 32, 17, -6, 0};
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    //插入排序
    public static void insertSort(int[] arr) {
        //有序区间：[0, i)
        //无序区间：[i, arr.length)
        for (int i = 1; i < arr.length; i++) {
            //每次从无序区间拿一个数放到有序区间的合适位置
            int t = arr[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (t <arr[j]) {
                    arr[j + 1] = arr[j];
                } else {
                    break;
                }
            }
            arr[j + 1] = t;
        }
    }

    //希尔排序
    public static void shellSort(int[] arr) {
        int gap = arr.length;
        while (gap > 1) {
            insertSortByGap(arr, gap);
            gap /= 2;
        }
        insertSortByGap(arr, 1);
    }

    public static void insertSortByGap(int[] arr, int gap) {
        for (int i = 1; i < arr.length; i++) {
            int t = arr[i];
            int j = i - gap;
            for (; j >= 0; j -= gap) {
                if (t < arr[j]) {
                    arr[j + gap] = arr[j];
                } else {
                    break;
                }
            }
            arr[j + gap] = t;
        }
    }

    //选择排序
    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            swap(arr, i, min);
        }
    }

    //堆排序
    public static void heapSort(int[] arr) {
        createHeap(arr);
        for (int i = arr.length - 1; i > 0; i--) {
            swap(arr, 0, i);
            shiftDown(arr, i - 1, 0);
        }
    }

    //排升序，建大堆
    private static void createHeap(int[] arr) {
        int index = (arr.length - 2) / 2;
        for (int i = index; i >= 0; i--) {
            shiftDown(arr, arr.length - 1, i);
        }
    }

    private static void shiftDown(int[] arr, int size, int index) {
        int parent = index;
        int child = index * 2 + 1;
        int max = 0;
        while (child <= size) {
            //让child总是指向值比较大的孩子节点
            if (child + 1 <= size && arr[child + 1] > arr[child]) {
                child = child + 1;
            }
            if (arr[parent] < arr[child])  {
                swap(arr, parent, child);
                parent = child;
                child = 2 * parent + 1;
            } else {
                break;
            }
        }
    }

    //冒泡排序
    public static void bubbleSort(int[] arr) {
        boolean flag = true;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = false;
                    swap(arr, j, j + 1);
                }
            }
            if (flag) break;
        }
    }

    //快速排序
    public static void quickSort(int[] arr) {
        partion(arr, 0, arr.length - 1);
    }

    private static void partion(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int i = left;
        int j = right;
        int pivot = arr[left];
        while (i < j) {
            while (i < j && arr[j] >= pivot) {
                j--;
            }
            while (i < j && arr[i] <= pivot) {
                i++;
            }
            swap(arr, i, j);
        }
        swap(arr, i, left);
        partion(arr, left, i - 1);
        partion(arr, i + 1, right);
    }

    //归并排序
    public static void mergeSort(int[] arr) {
        mergeSortHelper(arr, 0, arr.length - 1);
    }

    private static void mergeSortHelper(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        mergeSortHelper(arr, left, mid);
        mergeSortHelper(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int[] tmp = new int[right - left + 1];
        int i = left;
        int m = mid + 1;
        int index = 0;
        while (i <= mid && m <= right) {
            if (arr[i] <= arr[m]) {
                tmp[index++] = arr[i++];
            } else {
                tmp[index++] = arr[m++];
            }
        }
        while (i <= mid) tmp[index++] = arr[i++];
        while (m <= right) tmp[index++] = arr[m++];
        for (int x : tmp) {
            arr[left++] = x;
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
