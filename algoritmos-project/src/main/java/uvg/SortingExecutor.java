package uvg;
import java.io.*;
import java.util.*;

public class SortingExecutor {
    private final int[] data;

    public SortingExecutor(int[] data) {
        this.data = data;
    }

    public void executeAllSorts() {
        executeSort("Insertion Sort", data.clone(), SortingUtils::insertionSort);
        executeSort("Merge Sort", data.clone(), SortingUtils::mergeSort);
        executeSort("Quick Sort", data.clone(), arr -> SortingUtils.quickSort(arr, 0, arr.length - 1));
        executeSort("Radix Sort", data.clone(), SortingUtils::radixSort);
        executeSort("Bucket Sort", data.clone(), SortingUtils::bucketSort);
        executeSort("Heap Sort", data.clone(), SortingUtils::heapSort);
    }

    private void executeSort(String sortName, int[] array, SortingAlgorithm algorithm) {
        System.out.println("Ordenando con " + sortName + "...");
        algorithm.sort(array);
        System.out.println(sortName + " completado.");
    }
}

