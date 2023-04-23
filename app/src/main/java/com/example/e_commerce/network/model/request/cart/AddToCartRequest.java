package com.example.e_commerce.network.model.request.cart;

public class AddToCartRequest {
    Long oderItemId;
    long productId, quantity;

    public AddToCartRequest(Long oderItemId, long productId, long quantity) {
        this.oderItemId = oderItemId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getOderItemId() {
        return oderItemId;
    }

    public void setOderItemId(Long oderItemId) {
        this.oderItemId = oderItemId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
