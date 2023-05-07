package com.example.e_commerce.network.model.response.order;

public class Oder {
    private String telephoneNumber;
    private String deliveryAddress;
    private long statusPayment;
    private long id;
    private String oderCode;
    private long status;

    public String getTelephoneNumber() { return telephoneNumber; }
    public void setTelephoneNumber(String value) { this.telephoneNumber = value; }

    public String getDeliveryAddress() { return deliveryAddress; }
    public void setDeliveryAddress(String value) { this.deliveryAddress = value; }

    public long getStatusPayment() { return statusPayment; }
    public void setStatusPayment(long value) { this.statusPayment = value; }

    public long getid() { return id; }
    public void setid(long value) { this.id = value; }

    public String getOderCode() { return oderCode; }
    public void setOderCode(String value) { this.oderCode = value; }

    public long getStatus() { return status; }
    public void setStatus(long value) { this.status = value; }
}
