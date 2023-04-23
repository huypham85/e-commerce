// CartItem.java

//YApi QuickType插件生成，具体参考文档:https://github.com/RmondJone/YapiQuickType

package com.example.e_commerce.network.model.response.cart;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class CartItem implements Parcelable {
    private long quantity;
    private long productCost;
    private long productId;
    private long id;
    private String productName;
    private String productDescription;
    private long status;
    private String productFilePath;

    public CartItem(long quantity, long productCost, long productId, long id, String productName, String productDescription, long status, String productFilePath) {
        this.quantity = quantity;
        this.productCost = productCost;
        this.productId = productId;
        this.id = id;
        this.productName = productName;
        this.productDescription = productDescription;
        this.status = status;
        this.productFilePath = productFilePath;
    }

    public String getProductFilePath() {
        return productFilePath;
    }

    public void setProductFilePath(String productFilePath) {
        this.productFilePath = productFilePath;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long value) {
        this.quantity = value;
    }

    public long getProductCost() {
        return productCost;
    }

    public void setProductCost(long value) {
        this.productCost = value;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long value) {
        this.productId = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long value) {
        this.id = value;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String value) {
        this.productName = value;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String value) {
        this.productDescription = value;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long value) {
        this.status = value;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {

    }
}
