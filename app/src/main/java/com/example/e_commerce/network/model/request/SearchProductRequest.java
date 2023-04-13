package com.example.e_commerce.network.model.request;

public class SearchProductRequest {
    private String textSearch;
    private String costDescription;
    private int productStatus;
    private String productTypeName;

    public SearchProductRequest(String textSearch, String costDescription, int productStatus, String productTypeName) {
        this.textSearch = textSearch;
        this.costDescription = costDescription;
        this.productStatus = productStatus;
        this.productTypeName = productTypeName;
    }

    public String getTextSearch() {
        return textSearch;
    }

    public void setTextSearch(String textSearch) {
        this.textSearch = textSearch;
    }

    public String getCostDescription() {
        return costDescription;
    }

    public void setCostDescription(String costDescription) {
        this.costDescription = costDescription;
    }

    public int getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(int productStatus) {
        this.productStatus = productStatus;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }
}
