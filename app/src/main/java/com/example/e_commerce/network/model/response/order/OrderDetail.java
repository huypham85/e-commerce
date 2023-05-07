package com.example.e_commerce.network.model.response.order;

import java.util.List;

public class OrderDetail {
    private Oder oder;
    private List<OderItem> oderItem;

    public Oder getOder() { return oder; }
    public void setOder(Oder value) { this.oder = value; }

    public List<OderItem> getOderItem() { return oderItem; }
    public void setOderItem(List<OderItem> value) { this.oderItem = value; }
}

