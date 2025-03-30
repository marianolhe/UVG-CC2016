package uvg;

import java.io.*;
import java.util.*;

public class ProductSearchApp {
    public static void main(String[] args) {
        BinarySearchTree<Product> bst = new BinarySearchTree<>();
        String filePath = "HAS.csv";
        
        // Carga de datos desde CSV
        loadProductsFromCSV(bst, filePath);
        
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        
        while (running) {
            System.out.println("\n=== SISTEMA DE BÚSQUEDA DE PRODUCTOS ===");
            System.out.println("1. Buscar producto por SKU");
            System.out.println("2. Listar productos con mismo SKU por precio (ascendente)");
            System.out.println("3. Listar productos con mismo SKU por precio (descendente)");
            System.out.println("4. Salir");
            System.out.print("\nSeleccione una opción: ");
            
            int option = 0;
            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingrese un número válido.");
                continue;}
            
            String sku = "";
            if (option >= 1 && option <= 3) {
                System.out.print("\nIngrese el SKU a buscar: ");
                sku = scanner.nextLine().trim();
            }

            switch (option) {
                case 1:
                    searchProductBySKU(bst, sku);
                    break;
                case 2:
                    displayProductsBySKUAndPriceOrder(bst, sku, true); // Ascendente
                    break;
                case 3:
                    displayProductsBySKUAndPriceOrder(bst, sku, false); // Descendente
                    break;
                case 4:
                    running = false;
                    System.out.println("\n¡Gracias por utilizar el sistema!");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
            }
            scanner.close();
            }
    
    private static void loadProductsFromCSV(BinarySearchTree<Product> bst, String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Omitir encabezado
            int count = 0;
            
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 19) { // Asegurar suficientes columnas
                    try {
                        String category = data[0].trim();       // CATEGORY
                        String sku = data[6].trim();            // SKU
                        String productName = data[18].trim();   // PRODUCT_NAME
                        
                        // Manejar valores vacíos
                        double priceRetail = 0.0;
                        if (!data[9].trim().isEmpty()) {
                            priceRetail = Double.parseDouble(data[9].trim());
                        }
                        
                        double priceCurrent = 0.0;
                        if (!data[10].trim().isEmpty()) {
                            priceCurrent = Double.parseDouble(data[10].trim());
                        }
                        
                        // Solo agregar productos con precio válido
                        if (priceCurrent > 0 || priceRetail > 0) {
                            Product product = new Product(sku, priceRetail, priceCurrent, productName, category);
                            bst.insert(product);
                            count++;
                        }
                    } catch (NumberFormatException e) {
                        // Mostrar parte de la línea para depuración
                        int maxLength = Math.min(50, line.length());
                        System.out.println("Error al procesar línea: " + 
                                          line.substring(0, maxLength) + 
                                          (line.length() > maxLength ? "..." : ""));
                    }
                }
            }
            
            System.out.println("Productos procesados correctamente: " + count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void searchProductBySKU(BinarySearchTree<Product> bst, String sku) {
        // Buscar todos los productos con el mismo SKU
        List<Product> matchingProducts = findProductsBySkuAll(bst, sku);
        
        if (!matchingProducts.isEmpty()) {
            System.out.println("\n=== PRODUCTO ENCONTRADO ===");
            
            // Mostrar el primero que encontramos
            Product firstProduct = matchingProducts.get(0);
            System.out.println("SKU: " + firstProduct.getSku());
            System.out.println("Nombre: " + firstProduct.getProductName());
            System.out.println("Precio más bajo: $" + firstProduct.getPriceCurrent());
            System.out.println("Precio retail: $" + firstProduct.getPriceRetail());
            System.out.println("Categoría: " + firstProduct.getCategory());
            
            // Indicar que hay más productos con el mismo SKU si es el caso
            if (matchingProducts.size() > 1) {
                System.out.println("\nHay " + matchingProducts.size() + " productos con este SKU.");
                System.out.println("Use las opciones 2 o 3 para ver todos ordenados por precio.");
            }
        } else {
            System.out.println("\nNo se encontró ningún producto con SKU: " + sku);
        }
    }
    
    private static void displayProductsBySKUAndPriceOrder(BinarySearchTree<Product> bst, String sku, boolean ascending) {
        // Buscar todos los productos con el mismo SKU
        List<Product> matchingProducts = findProductsBySkuAll(bst, sku);
        
        if (matchingProducts.isEmpty()) {
            System.out.println("\nNo se encontró ningún producto con SKU: " + sku);
            return;
        }
        
        // Ordenar por precio según lo solicitado
        if (ascending) {
            matchingProducts.sort(Comparator.comparingDouble(Product::getPriceCurrent));
            System.out.println("\n=== PRODUCTOS CON SKU '" + sku + "' ORDENADOS POR PRECIO (ASCENDENTE) ===");
        } else {
            matchingProducts.sort(Comparator.comparingDouble(Product::getPriceCurrent).reversed());
            System.out.println("\n=== PRODUCTOS CON SKU '" + sku + "' ORDENADOS POR PRECIO (DESCENDENTE) ===");
        }
        
        // Mostrar productos encontrados
        System.out.println("Total de coincidencias encontradas: " + matchingProducts.size());
        System.out.println("\nLista de productos:");
        
        for (int i = 0; i < matchingProducts.size(); i++) {
            System.out.println((i+1) + ". " + matchingProducts.get(i));
        }
    }

    // Método auxiliar para encontrar todos los productos con un SKU específico
    private static List<Product> findProductsBySkuAll(BinarySearchTree<Product> bst, String sku) {
        // Recolectar todos los productos
        List<Product> allProducts = new ArrayList<>();
        bst.collectInOrder(allProducts);
        
        // Filtrar solo los que coincidan con el SKU buscado
        List<Product> matchingProducts = new ArrayList<>();
        for (Product product : allProducts) {
            if (product.getSku().equals(sku)) {
                matchingProducts.add(product);
            }
        }
        
        return matchingProducts;
    }
}