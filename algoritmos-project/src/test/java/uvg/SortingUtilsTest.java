package uvg;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

class SortingUtilsTest {

    private int[] testArray;
    private int[] sortedArray;

    @BeforeEach
    void setUp() {
        testArray = new int[]{5, 2, 9, 1, 5, 6};
        sortedArray = new int[]{1, 2, 5, 5, 6, 9};
    }

    @Test
    void testInsertionSort() {
        int[] result = testArray.clone();
        SortingUtils.insertionSort(result);
        assertArrayEquals(sortedArray, result, "Insertion Sort failed");
    }

    @Test
    void testMergeSort() {
        int[] result = testArray.clone();
        SortingUtils.mergeSort(result);
        assertArrayEquals(sortedArray, result, "Merge Sort failed");
    }

    @Test
    void testQuickSort() {
        int[] result = testArray.clone();
        SortingUtils.quickSort(result, 0, result.length - 1);
        assertArrayEquals(sortedArray, result, "Quick Sort failed");
    }

    @Test
    void testRadixSort() {
        int[] result = testArray.clone();
        SortingUtils.radixSort(result);
        assertArrayEquals(sortedArray, result, "Radix Sort failed");
    }

    @Test
    void testBucketSort() {
        int[] result = testArray.clone();
        SortingUtils.bucketSort(result);
        assertArrayEquals(sortedArray, result, "Bucket Sort failed");
    }

    @Test
    void testHeapSort() {
        int[] result = testArray.clone();
        SortingUtils.heapSort(result);
        assertArrayEquals(sortedArray, result, "Heap Sort failed");
    }
}


