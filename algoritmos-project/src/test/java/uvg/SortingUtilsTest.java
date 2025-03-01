package uvg;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        Assertions.assertArrayEquals(sortedArray, result);
    }

    @Test
    void testMergeSort() {
        int[] result = testArray.clone();
        SortingUtils.mergeSort(result);
        Assertions.assertArrayEquals(sortedArray, result);
    }

    @Test
    void testQuickSort() {
        int[] result = testArray.clone();
        SortingUtils.quickSort(result, 0, result.length - 1);
        Assertions.assertArrayEquals(sortedArray, result);
    }

    @Test
    void testRadixSort() {
        int[] result = testArray.clone();
        SortingUtils.radixSort(result);
        Assertions.assertArrayEquals(sortedArray, result);
    }

    @Test
    void testBucketSort() {
        int[] result = testArray.clone();
        SortingUtils.bucketSort(result);
        Assertions.assertArrayEquals(sortedArray, result);
    }

    @Test
    void testHeapSort() {
        int[] result = testArray.clone();
        SortingUtils.heapSort(result);
        Assertions.assertArrayEquals(sortedArray, result);
    }
}


