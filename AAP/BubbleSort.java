import java.io.*;

class BubbleSort {

<<<<<<< HEAD
    static void bubbleSort(int[] array, int size) {
        boolean swapped;
        for (int i = 0; i < size - 1; i++) {
            swapped = false;
            for (int j = 0; j < size - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }

            if (!swapped)
                break;
        }
    }

    static void printArray(int[] array, int size) {
        for (int i = 0; i < size; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] array = {64, 34, 25, 12, 22, 11, 90};
        int size = array.length;
        bubbleSort(array, size);
        System.out.println("Array ordenado: ");
        printArray(array, size);
    }
}
=======
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
>>>>>>> f90195abb6ca886e62d570c70cc7fa51b5333cba
