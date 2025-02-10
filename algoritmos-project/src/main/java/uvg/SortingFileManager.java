package uvg;

class SortingFileManager {
    private final String filename;

    public SortingFileManager(String filename) {
        this.filename = filename;
    }

    public void writeToFile(int[] data) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int num : data) {
                writer.write(num + "\n");
            }
        }
    }

    public int[] readFromFile() throws IOException {
        List<Integer> numbers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                numbers.add(Integer.parseInt(line));
            }
        }
        return numbers.stream().mapToInt(i -> i).toArray();
    }
}

