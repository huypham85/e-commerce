package com.example.e_commerce.network.model.response.order;

public class OderItem {
    private long quantity;
    private long productCost;
    private long productId;
    private long id;
    private String productName;
    private String productDescription;
    private long status;

    public long getQuantity() { return quantity; }
    public void setQuantity(long value) { this.quantity = value; }

    public long getProductCost() { return productCost; }
    public void setProductCost(long value) { this.productCost = value; }

    public long getProductId() { return productId; }
    public void setProductId(long value) { this.productId = value; }

    public long getid() { return id; }
    public void setid(long value) { this.id = value; }

    public String getProductName() { return productName; }
    public void setProductName(String value) { this.productName = value; }

    public String getProductDescription() { return productDescription; }
    public void setProductDescription(String value) { this.productDescription = value; }

    public long getStatus() { return status; }
    public void setStatus(long value) { this.status = value; }
}
