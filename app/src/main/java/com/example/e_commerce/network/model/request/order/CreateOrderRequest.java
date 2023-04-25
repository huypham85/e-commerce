// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

package com.example.e_commerce.network.model.request.order;

import java.util.List;

public class CreateOrderRequest {
    Integer id;
    private String telephoneNumber;
    private String deliveryAddress;
    private int statusPayment;
    private List<Long> listOderItem;
    public CreateOrderRequest(Integer id, String telephoneNumber, String deliveryAddress, int statusPayment, List<Long> listOderItem) {
        this.id = id;
        this.telephoneNumber = telephoneNumber;
        this.deliveryAddress = deliveryAddress;
        this.statusPayment = statusPayment;
        this.listOderItem = listOderItem;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String value) {
        this.telephoneNumber = value;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String value) {
        this.deliveryAddress = value;
    }

    public long getStatusPayment() {
        return statusPayment;
    }

    public void setStatusPayment(int value) {
        this.statusPayment = value;
    }

    public List<Long> getListOderItem() {
        return listOderItem;
    }

    public void setListOderItem(List<Long> value) {
        this.listOderItem = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
