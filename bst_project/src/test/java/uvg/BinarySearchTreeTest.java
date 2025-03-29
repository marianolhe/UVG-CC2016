package uvg;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BinarySearchTreeTest {
    private BinarySearchTree<Product> bst;

    @BeforeEach
    void setUp() {
        bst = new BinarySearchTree<>();
        bst.insert(new Product("A123", 100.0, 80.0, "Laptop", "Electronics"));
        bst.insert(new Product("B456", 50.0, 40.0, "Mouse", "Accessories"));
        bst.insert(new Product("C789", 30.0, 25.0, "Keyboard", "Accessories"));
    }

    @Test
    void testInsertAndSearch() {
        Product searchKey = new Product("B456", 0, 0, "", "");
        Product found = bst.search(searchKey);
        assertNotNull(found);
        assertEquals("B456", found.sku);
    }

    @Test
    void testSearchNotFound() {
        Product searchKey = new Product("Z999", 0, 0, "", "");
        Product found = bst.search(searchKey);
        assertNull(found);
    }
}
