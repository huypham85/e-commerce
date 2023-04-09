package com.example.e_commerce.network.model.response;


public class TypeProduct {
    private long id;
    private String typeProductName;

    public TypeProduct(long id, String typeProductName) {
        this.id = id;
        this.typeProductName = typeProductName;
    }

    public long getID() {
        return id;
    }

    public void setID(long value) {
        this.id = value;
    }

    public String getTypeProductName() {
        return typeProductName;
    }

    public void setTypeProductName(String value) {
        this.typeProductName = value;
    }
}
