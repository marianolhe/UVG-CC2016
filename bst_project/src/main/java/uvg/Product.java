package uvg;

public class Product implements Comparable<Product> {
    private String sku;
    private double priceRetail;
    private double priceCurrent;
    private String productName;
    private String category;
    
    public Product(String sku, double priceRetail, double priceCurrent, String productName, String category) {
        this.sku = sku;
        this.priceRetail = priceRetail;
        this.priceCurrent = priceCurrent;
        this.productName = productName;
        this.category = category;
    }
    
    // Getters
    public String getSku() { return sku; }
    public double getPriceRetail() { return priceRetail; }
    public double getPriceCurrent() { return priceCurrent; }
    public String getProductName() { return productName; }
    public String getCategory() { return category; }
    
    @Override
    public int compareTo(Product other) {
        return this.sku.compareTo(other.sku);  // Comparar SOLO por SKU
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Product product = (Product) obj;
        return sku.equals(product.sku);  // Comparar SOLO por SKU
    }
    
    @Override
    public int hashCode() {
        return sku.hashCode();  // Consistente con equals
    }
    
    @Override
    public String toString() {
        return String.format("SKU: %s | %s | Precio: $%.2f | Retail: $%.2f | Categoría: %s", 
                            sku, productName, priceCurrent, priceRetail, category);
    }
}