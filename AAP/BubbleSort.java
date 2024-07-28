import java.io.*;

class BubbleSort {

    public static void main(String[] args) {
        int[] array = {64, 34, 25, 12, 22, 11, 90};
        sortArrayUsingBubbleSort(array);
        printSortedArray(array);
    }

    static void sortArrayUsingBubbleSort (int[] array) {
        boolean isSorted;
        for (int pass = 0; pass < array.length - 1; pass++) {
            isSorted = true;
            for (int current = 0; current < array.length - pass - 1; current++) {
                if (array[current] > array[current + 1]) {
                    swapElements(array, current, current + 1);
                    isSorted = false;
                }
            }
            if (isSorted) break;
        }
    }

    static void swapElements(int[] array, int firstIndex, int secondIndex) {
        int temporary = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temporary;
    }

    static void printSortedArray(int[] array) {
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}
