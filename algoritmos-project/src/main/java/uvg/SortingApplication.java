package uvg;
import java.io.*;
import java.util.*;

public class SortingApplication {
    public static void main(String[] args) throws IOException {
        SortingFileManager fileManager = new SortingFileManager("numbers.txt");
        int[] numbers = SortingDataGenerator.generateRandomNumbers(3000);

        fileManager.writeToFile(numbers);
        int[] data = fileManager.readFromFile();

        SortingExecutor executor = new SortingExecutor(data);
        executor.executeAllSorts();
    }
}
