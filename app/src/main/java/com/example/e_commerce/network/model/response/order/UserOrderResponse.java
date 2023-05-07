package com.example.e_commerce.network.model.response.order;

public class UserOrderResponse {
    private long id, status;
    private String oderCode;

    public UserOrderResponse(long id, String oderCode, long status) {
        this.id = id;
        this.oderCode = oderCode;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public String getOderCode() {
        return oderCode;
    }

    public void setOderCode(String oderCode) {
        this.oderCode = oderCode;
    }
}
