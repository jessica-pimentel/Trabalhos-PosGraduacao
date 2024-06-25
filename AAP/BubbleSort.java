import java.io.*;

class BubbleSort {

    static void bubbleSort(int arr[], int n)
    {
        static void bubbleSort(int[] array) {
            boolean swapped;
            for (int pass = 0; pass < array.length - 1; pass++) {
                swapped = false;
                for (int index = 0; index < array.length - pass - 1; index++) {
                    if (array[index] > array[index + 1]) {
                        int temp = array[index];
                        array[index] = array[index + 1];
                        array[index + 1] = temp;
                        swapped = true;
                    }
                }

            if (!swapped) break;
        }
    }

    static void printArray(int arr[], int size)
    {
        for (int pass = 0; pass < size; pass++)
            System.out.print(arr[pass] + " ");
        System.out.println();
    }

    public static void main(String args[])
    {
        int arr[] = { 64, 34, 25, 12, 22, 11, 90 };
        int n = arr.length;
        bubbleSort(arr, n);
        System.out.println("Array ordenado: ");
        printArray(arr, n);
    }
}
