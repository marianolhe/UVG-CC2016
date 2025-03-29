package uvg; 


public class Product implements Comparable<Product> {
    public String sku;
    public double priceRetail;
    public double priceCurrent;
    public String productName;
    public String category;

    public Product(String sku, double priceRetail, double priceCurrent, String productName, String category) {
        this.sku = sku;
        this.priceRetail = priceRetail;
        this.priceCurrent = priceCurrent;
        this.productName = productName;
        this.category = category;
    }

    @Override
    public int compareTo(Product other) {
        return this.sku.compareTo(other.sku);
    }

    @Override
    public String toString() {
        return "SKU: " + sku + ", Price: " + priceCurrent + ", Retail Price: " + priceRetail + ", Name: " + productName + ", Category: " + category;
    }
}
