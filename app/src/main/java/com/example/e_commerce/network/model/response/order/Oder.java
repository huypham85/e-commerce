package com.example.e_commerce.network.model.response.order;

public class Oder {
    private String telephoneNumber;
    private String deliveryAddress;
    private int statusPayment;
    private long id;
    private String oderCode;
    private int status;

    public String getTelephoneNumber() { return telephoneNumber; }
    public void setTelephoneNumber(String value) { this.telephoneNumber = value; }

    public String getDeliveryAddress() { return deliveryAddress; }
    public void setDeliveryAddress(String value) { this.deliveryAddress = value; }

    public int getStatusPayment() { return statusPayment; }
    public void setStatusPayment(int value) { this.statusPayment = value; }

    public long getid() { return id; }
    public void setid(long value) { this.id = value; }

    public String getOderCode() { return oderCode; }
    public void setOderCode(String value) { this.oderCode = value; }

    public int getStatus() { return status; }
    public void setStatus(int value) { this.status = value; }
}
