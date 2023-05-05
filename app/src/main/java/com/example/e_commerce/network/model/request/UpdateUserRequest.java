package com.example.e_commerce.network.model.request;

public class UpdateUserRequest {
    private String telephoneNumber;
    private String deliveryAddress;
    public UpdateUserRequest(String telephoneNumber, String deliveryAddress) {
        this.telephoneNumber = telephoneNumber;
        this.deliveryAddress = deliveryAddress;
    }
    public String getTelephoneNumber() { return telephoneNumber; }

    public void setTelephoneNumber(String telephoneNumber) { this.telephoneNumber = telephoneNumber; }

    public String getDeliveryAddress() { return deliveryAddress; }

    public void setDeliveryAddress(String deliveryAddress) { this.deliveryAddress = deliveryAddress; }
}
