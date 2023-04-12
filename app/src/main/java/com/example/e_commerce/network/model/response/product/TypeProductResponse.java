package com.example.e_commerce.network.model.response.product;

public class TypeProductResponse {
    long id;
    String typeProductName;

    public TypeProductResponse(long id, String typeProductName) {
        this.id = id;
        this.typeProductName = typeProductName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTypeProductName() {
        return typeProductName;
    }

    public void setTypeProductName(String typeProductName) {
        this.typeProductName = typeProductName;
    }
}
