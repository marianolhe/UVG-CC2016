package uvg;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class BinarySearchTreeTest {
    
    // Variables para las pruebas
    private BinarySearchTree<Product> bst;
    
    // Productos para testInsert
    private Product freezerStainless;
    private Product freezerBlack;
    private Product freezerBenchmark;
    
    // Productos para testSearch
    private Product hotLogicCooker;
    private Product brentwoodCooker;
    private Product aromaCooker;
    private Product roadProCooker;
    
    // Productos para testCollectInOrder
    private Product lgWasherDryer;
    private Product samsungWasherDryer;
    private Product WhirlpoolWasherDryer;
    private Product GEWasherDryer;
    
    // Productos para testMultipleProductsWithSameSKU
    private Product windowAir1;
    private Product windowAir2;
    
    @BeforeEach
    public void setUp() {
        bst = new BinarySearchTree<>();
        
        // Inicializar productos para testInsert
        freezerStainless = new Product("3735231", 669.00, 669.00, 
                "3-cu ft Freezerless Refrigerator (Stainless Steel)", "Refrigerators");
        freezerBlack = new Product("5000164291", 179.95, 179.95, 
                "1.7-cu ft Freezerless Refrigerator (Black)", "Refrigerators");
        freezerBenchmark = new Product("5001544531", 6928.90, 6928.90, 
                "Benchmark Series 16.8-cu ft Freezerless Refrigerator (Multi) ENERGY STAR", "Refrigerators");
        
        // Inicializar productos para testSearch
        lgWasherDryer = new Product("5013197989", 1249.00, 1249.00, 
                "EasyLoad Smart Wi-Fi Enabled 7.3-cu ft Gas Dryer (Graphite Steel) ENERGY STAR", "Washers & Dryers");
        samsungWasherDryer = new Product("5013150269", 1249.00, 1124.00, 
                "22.8-cu ft 4-Door Counter-Depth French Door Refrigerator with Dual Ice Maker and Door within Door (Fingerprint Resistant Stainless Steel) ENERGY STAR", "Washers & Dryers");
        WhirlpoolWasherDryer = new Product("1000704970", 1149.00, 1149.00, 
                "7.4-cu ft Front Load Stackable Long Vented Gas Dryer - White", "Washers & Dryers");
        GEWasherDryer = new Product("999922472", 799.00, 629.00, 
                "7.2-cu ft Electric Dryer (White)", "Washers & Dryers");
        
        // Inicializar productos para testCollectInOrder
        hotLogicCooker = new Product("5000025803", 49.95, 49.95, 
                "1.5-Quart Purple Rectangle Slow Cooker", "Small Appliances");
        brentwoodCooker = new Product("1001574822", 48.57, 48.57, 
                "8-Cup Commercial/Residential Rice Cooker", "Small Appliances");
        aromaCooker = new Product("1000876406", 48.39, 48.39, 
                "20-Cup Programmable Residential Rice Cooker", "Small Appliances");
        roadProCooker = new Product("1002683240", 39.99, 39.99, 
                "RoadPro 12-Volt 1.5 Quart Slow Cooker, Black 1.5-Quart Black Round Slow Cooker", "Small Appliances");
        
        // Inicializar productos para testMultipleProductsWithSameSKU
        windowAir1 = new Product("1003252106", 289.00, 289.00, 
                "250-sq ft Window Air Conditioner (115-Volt; 6000-BTU) ENERGY STAR", "Air Conditioners & Fans");
        windowAir2 = new Product("1003252106", 289.00, 289.00, 
                "250-sq ft Window Air Conditioner (115-Volt; 6000-BTU) ENERGY STAR", "Air Conditioners & Fans");
    }

    @Test
    public void testInsert() {
        // Insertar productos
        bst.insert(freezerStainless);
        bst.insert(freezerBlack);
        bst.insert(freezerBenchmark);
        
        // Verificar que los productos fueron insertados correctamente
        Product found1 = bst.search(new Product("3735231", 0, 0, "", ""));
        Product found2 = bst.search(new Product("5000164291", 0, 0, "", ""));
        Product found3 = bst.search(new Product("5001544531", 0, 0, "", ""));
        
        assertNotNull(found1, "3-cu ft Freezerless Refrigerator (Stainless Steel) no fue encontrado después de insertar");
        assertNotNull(found2, "1.7-cu ft Freezerless Refrigerator (Black) no fue encontrado después de insertar");
        assertNotNull(found3, "Benchmark Series 16.8-cu ft Freezerless Refrigerator (Multi) ENERGY STAR no fue encontrado después de insertar");
        
        assertEquals("3735231", found1.getSku(), "SKU del 3-cu ft Freezerless Refrigerator (Stainless Steel) no coincide");
        assertEquals("5000164291", found2.getSku(), "SKU del 1.7-cu ft Freezerless Refrigerator (Black) no coincide");
        assertEquals("5001544531", found3.getSku(), "SKU del Benchmark Series 16.8-cu ft Freezerless Refrigerator (Multi) ENERGY STAR no coincide");
        
        // Verificar que los detalles del producto también son correctos
        assertEquals(669.00, found1.getPriceCurrent(), "Precio actual del 3-cu ft Freezerless Refrigerator (Stainless Steel) incorrecto");
        assertEquals(179.95, found2.getPriceCurrent(), "Precio actual del 1.7-cu ft Freezerless Refrigerator (Black) incorrecto"); 
        assertEquals(6928.90, found3.getPriceCurrent(), "Precio actual del Benchmark Series 16.8-cu ft Freezerless Refrigerator (Multi) ENERGY STAR incorrecto");
    }

    @Test
    public void testSearch() {
        // Insertar los productos para la prueba
        bst.insert(lgWasherDryer);
        bst.insert(samsungWasherDryer);
        bst.insert(WhirlpoolWasherDryer);
        bst.insert(GEWasherDryer);
        
        // Buscar productos existentes por SKU
        Product foundLG = bst.search(new Product("5013197989", 0, 0, "", ""));
        Product foundSamsung = bst.search(new Product("5013150269", 0, 0, "", ""));
        
        // Verificaciones del LG Dryer
        assertNotNull(foundLG, "REasyLoad Smart Wi-Fi Enabled 7.3-cu ft Gas Dryer (Graphite Steel) ENERGY STAR no fue encontrado");
        assertEquals("5013197989", foundLG.getSku(), "SKU del EasyLoad Smart Wi-Fi Enabled 7.3-cu ft Gas Dryer (Graphite Steel) ENERGY STAR no coincide");
        assertEquals(1249.00, foundLG.getPriceCurrent(), "Precio actual del EasyLoad Smart Wi-Fi Enabled 7.3-cu ft Gas Dryer (Graphite Steel) ENERGY STAR no coincide");
        assertEquals("Washers & Dryers", foundLG.getCategory(), "Categoría del refrigerador LG Counter-Depth no coincide");
        
        // Verificaciones del Samsung Refrigerator
        assertNotNull(foundSamsung, "22.8-cu ft 4-Door Counter-Depth French Door Refrigerator with Dual Ice Maker and Door within Door (Fingerprint Resistant Stainless Steel) ENERGY STAR no fue encontrado");
        assertEquals("5013150269", foundSamsung.getSku(), "SKU del 22.8-cu ft 4-Door Counter-Depth French Door Refrigerator with Dual Ice Maker and Door within Door (Fingerprint Resistant Stainless Steel) ENERGY STAR no coincide");
        assertEquals(1124.00, foundSamsung.getPriceCurrent(), "Precio actual del 22.8-cu ft 4-Door Counter-Depth French Door Refrigerator with Dual Ice Maker and Door within Door (Fingerprint Resistant Stainless Steel) ENERGY STAR no coincide");
        
        // Buscar producto no existente
        Product notFound = bst.search(new Product("NON EXISTENT SKU", 0, 0, "", ""));
        assertNull(notFound, "No debería encontrarse un producto con SKU inexistente");
    }
    
    @Test
    public void testCollectInOrder() {
        // Insertar productos en orden aleatorio
        bst.insert(hotLogicCooker);  // 5000025803
        bst.insert(brentwoodCooker);  // 1001574822
        bst.insert(aromaCooker);  // 1000876406
        bst.insert(roadProCooker);  // 1002683240
        
        // Recolectar productos en orden
        List<Product> products = new ArrayList<>();
        bst.collectInOrder(products);
        
        // Verificar que están ordenados por SKU (orden lexicográfico)
        assertEquals(4, products.size(), "Debe haber 4 productos recolectados");
        assertEquals("5000025803", products.get(3).getSku(), "Cuarto producto debe ser Hot Logic Slow Cooker");
        assertEquals("1001574822", products.get(1).getSku(), "Segundo producto debe Brentwood Rice Cooker");
        assertEquals("1000876406", products.get(0).getSku(), "Primer producto debe ser Aroma Rice Cooker");
        assertEquals("1002683240", products.get(2).getSku(), "Tercer producto debe ser Road Pro Slow Cooker");
        
        // Verificar que los precios son correctos
        assertEquals(49.95, products.get(3).getPriceCurrent(), "Precio del Hot Logic Slow Cooker incorrecto");
        assertEquals(48.57, products.get(1).getPriceCurrent(), "Precio del Brentwood Rice Cooker incorrecto");
        assertEquals(48.39, products.get(0).getPriceCurrent(), "Precio del Aroma Rice Cooker incorrecto");
        assertEquals(39.99, products.get(2).getPriceCurrent(), "Precio del Road Pro Slow Cooker incorrecto");
    }
    
    @Test
    public void testMultipleProductsWithSameSKU() {
        // Insertar los productos con mismo SKU
        bst.insert(windowAir1);
        bst.insert(windowAir2);
        
        // Al buscar, debe devolver el primer producto insertado
        Product found = bst.search(new Product("1003252106", 0, 0, "", ""));
        
        assertNotNull(found, "Producto con SKU repetido no fue encontrado");
        assertEquals("1003252106", found.getSku(), "SKU del producto encontrado no coincide");
        assertEquals(289.00, found.getPriceCurrent(), "Precio actual del producto encontrado no coincide");
    }
}