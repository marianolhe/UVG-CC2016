package uvg;
import java.io.*;
import java.util.*;

public class SortingDataGenerator {
    public static int[] generateRandomNumbers(int count) {
        Random rand = new Random();
        int[] numbers = new int[count];
        for (int i = 0; i < count; i++) {
            numbers[i] = rand.nextInt(10000);
        }
        return numbers;
    }
}
