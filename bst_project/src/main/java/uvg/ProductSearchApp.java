package uvg;
import java.io.*;
import java.util.*;

public class ProductSearchApp {
    public static void main(String[] args) {
        BinarySearchTree<Product> bst = new BinarySearchTree<>();
        String filePath = "products.csv";
        
        try (BufferedReader br = new BufferedReader(new FileReader(HAS.csv))) {
            String line;
            br.readLine(); // Omitir encabezado
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 5) {
                    Product product = new Product(data[0], Double.parseDouble(data[1]), Double.parseDouble(data[2]), data[3], data[4]);
                    bst.insert(product);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese SKU a buscar: ");
        String sku = scanner.nextLine();
        scanner.close();
        Product searchKey = new Product(sku, 0, 0, "", "");
        Product found = bst.search(searchKey);
        
        if (found != null) {
            System.out.println("Producto encontrado: " + found);
        } else {
            System.out.println("Producto no encontrado.");
        }
        
        System.out.println("Productos ordenados por precio:");
        bst.inOrder();
    }
}
