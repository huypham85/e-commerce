package com.example.e_commerce.network.model.response.product;


import com.example.e_commerce.network.model.response.TypeProduct;

import java.util.List;

public class ProductResponse {
    private long id;
    private String productName;
    private long productStatus;
    private long productCost;
    private String productDescription;
    private String productFilePath;
    private List<TypeProduct> typeProduct;

    public ProductResponse(long id, String productName, long productStatus, long productCost, String productDescription, String productFilePath, List<TypeProduct> typeProduct) {
        this.id = id;
        this.productName = productName;
        this.productStatus = productStatus;
        this.productCost = productCost;
        this.productDescription = productDescription;
        this.productFilePath = productFilePath;
        this.typeProduct = typeProduct;
    }

    public long getID() {
        return id;
    }

    public void setID(long value) {
        this.id = value;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String value) {
        this.productName = value;
    }

    public long getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(long value) {
        this.productStatus = value;
    }

    public long getProductCost() {
        return productCost;
    }

    public void setProductCost(long value) {
        this.productCost = value;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String value) {
        this.productDescription = value;
    }

    public String getProductFilePath() {
        return productFilePath;
    }

    public void setProductFilePath(String value) {
        this.productFilePath = value;
    }

    public List<TypeProduct> getTypeProduct() {
        return typeProduct;
    }

    public void setTypeProduct(List<TypeProduct> value) {
        this.typeProduct = value;
    }
}
