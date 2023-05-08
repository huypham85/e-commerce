package com.example.e_commerce.network.model.request.order;

public class UpdateOrderRequest {
    private CreateOrderRequest request;
    private int status;

    public UpdateOrderRequest(CreateOrderRequest request, int status) {
        this.request = request;
        this.status = status;
    }

    public CreateOrderRequest getRequest() {
        return request;
    }

    public void setRequest(CreateOrderRequest request) {
        this.request = request;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
