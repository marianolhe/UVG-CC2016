package uvg;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
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


