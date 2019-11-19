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


    public static void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }




    public static void main(String[] args) {
        int[] array = {2, 4, 1, 6, 7, 9, 0, 5, 3, 8};
        //insertSort(array);
        shellSort(array);
        System.out.println(Arrays.toString(array));
    }
}

