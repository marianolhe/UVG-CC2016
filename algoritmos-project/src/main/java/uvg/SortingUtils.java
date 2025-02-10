package uvg;
import java.io.*;
import java.util.*;

public class SortingUtils {
    public static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }

    public static void mergeSort(int[] array) {
        if (array.length < 2) return;
        int mid = array.length / 2;
        int[] left = Arrays.copyOfRange(array, 0, mid);
        int[] right = Arrays.copyOfRange(array, mid, array.length);

        mergeSort(left);
        mergeSort(right);
        merge(array, left, right);
    }

    private static void merge(int[] array, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            array[k++] = left[i] <= right[j] ? left[i++] : right[j++];
        }
        while (i < left.length) {
            array[k++] = left[i++];
        }
        while (j < right.length) {
            array[k++] = right[j++];
        }
    }

        public static void quickSort(int[] array, int low, int high) {
            if (low < high) {
                int pi = partition(array, low, high);
                quickSort(array, low, pi - 1);
                quickSort(array, pi + 1, high);
            }
        }
    
        private static int partition(int[] array, int low, int high) {
            int pivot = array[high];
            int i = low - 1;
            for (int j = low; j < high; j++) {
                if (array[j] < pivot) {
                    i++;
                    swap(array, i, j);
                }
            }
            swap(array, i + 1, high);
            return i + 1;
        }
    
        private static void swap(int[] array, int i, int j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    
        public static void radixSort(int[] array) {
            int max = Arrays.stream(array).max().orElse(0);
            for (int exp = 1; max / exp > 0; exp *= 10) {
                countSort(array, exp);
            }
        }
    
        private static void countSort(int[] array, int exp) {
            int n = array.length;
            int[] output = new int[n];
            int[] count = new int[10];
    
            for (int num : array) {
                count[(num / exp) % 10]++;
            }
    
            for (int i = 1; i < 10; i++) {
                count[i] += count[i - 1];
            }
    
            for (int i = n - 1; i >= 0; i--) {
                output[count[(array[i] / exp) % 10] - 1] = array[i];
                count[(array[i] / exp) % 10]--;
            }
    
            System.arraycopy(output, 0, array, 0, n);
        }
    
        public static void bucketSort(int[] array) {
            int maxValue = Arrays.stream(array).max().orElse(0);
            int bucketCount = (int) Math.sqrt(array.length);
            List<Integer>[] buckets = new List[bucketCount];
    
            for (int i = 0; i < bucketCount; i++) {
                buckets[i] = new ArrayList<>();
            }
    
            for (int num : array) {
                int bucketIndex = (num * bucketCount) / (maxValue + 1);
                buckets[bucketIndex].add(num);
            }
    
            for (List<Integer> bucket : buckets) {
                Collections.sort(bucket);
            }
    
            int index = 0;
            for (List<Integer> bucket : buckets) {
                for (int num : bucket) {
                    array[index++] = num;
                }
            }
        }
    
        public static void heapSort(int[] array) {
            int n = array.length;
    
            for (int i = n / 2 - 1; i >= 0; i--) {
                heapify(array, n, i);
            }
    
            for (int i = n - 1; i > 0; i--) {
                swap(array, 0, i);
                heapify(array, i, 0);
            }
        }
    
        private static void heapify(int[] array, int n, int i) {
            int largest = i;
            int left = 2 * i + 1;
            int right = 2 * i + 2;
    
            if (left < n && array[left] > array[largest]) {
                largest = left;
            }
    
            if (right < n && array[right] > array[largest]) {
                largest = right;
            }
    
            if (largest != i) {
                swap(array, i, largest);
                heapify(array, n, largest);
            }
        }
    
    }
