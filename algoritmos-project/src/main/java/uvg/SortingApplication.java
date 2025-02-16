package uvg;
import java.io.*;

public class SortingApplication {
    public static void main(String[] args) throws IOException {
        SortingFileManager fileManager = new SortingFileManager("numbers.txt");
        int[] numbers = SortingDataGenerator.generateRandomNumbers(3000);

        fileManager.writeToFile(numbers);
        int[] data = fileManager.readFromFile();

        // Uncomment the desired sort test
        // testInsertionSort(data);
        // testMergeSort(data);
        // testQuickSort(data);
        // testRadixSort(data);
        // testBucketSort(data);
        // testHeapSort(data);
    }

    /*private static void testInsertionSort(int[] data) {
        int[] copy = data.clone();
        System.out.println("Ejecutando Insertion Sort...");
        SortingUtils.insertionSort(copy);
        System.out.println("Insertion Sort completado.");
    }

    private static void testMergeSort(int[] data) {
        int[] copy = data.clone();
        System.out.println("Ejecutando Merge Sort...");
        SortingUtils.mergeSort(copy);
        System.out.println("Merge Sort completado.");
    }

    private static void testQuickSort(int[] data) {
        int[] copy = data.clone();
        System.out.println("Ejecutando Quick Sort...");
        SortingUtils.quickSort(copy, 0, copy.length - 1);
        System.out.println("Quick Sort completado.");
    }*/

    /*private static void testRadixSort(int[] data) {
       int[] copy = data.clone();
        System.out.println("Ejecutando Radix Sort...");
        SortingUtils.radixSort(copy);
        System.out.println("Radix Sort completado.");
    }

   private static void testBucketSort(int[] data) {
        int[] copy = data.clone();
        System.out.println("Ejecutando Bucket Sort...");
        SortingUtils.bucketSort(copy);
        System.out.println("Bucket Sort completado.");

        */
    private static void testHeapSort(int[] data) {
        int[] copy = data.clone();
        System.out.println("Ejecutando Heap Sort...");
        SortingUtils.heapSort(copy);
        System.out.println("Heap Sort completado.");
    }
}