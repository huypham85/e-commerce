package com.example.e_commerce.network.model.response;

public class UserOrderResponse {
    private long id, status;

    public UserOrderResponse(long id, long status) {
        this.id = id;
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
}
