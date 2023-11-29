import java.util.ArrayList;
/**
 * Sale
 * <p>
 * Helper Class for storing sale info for stores
 */
public class Sale {
    private String customerName;
    private String productName;
    private double revenue;
    public Sale(String customerName, String productName, double revenue) {
        this.customerName = customerName;
        this.productName = productName;
        this.revenue = revenue;
    }

    @Override
    public String toString()  {
        return customerName + "," + productName + "," + revenue;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getRevenue() {
        return this.revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }


}