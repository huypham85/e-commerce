package com.example.e_commerce.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.example.e_commerce.network.model.response.product.ProductResponse;

public class ProductModel implements Parcelable {
    public static final Creator<ProductModel> CREATOR = new Creator<ProductModel>() {
        @Override
        public ProductModel createFromParcel(Parcel in) {
            return new ProductModel(in);
        }

        @Override
        public ProductModel[] newArray(int size) {
            return new ProductModel[size];
        }
    };
    long id;
    String productName;
    long productPrice;
    String productStatus;
    String productImageURL;
    String productDesc;
    public ProductModel(String productName, long productPrice, String productStatus, String productImageURL, String productDesc) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productStatus = productStatus;
        this.productImageURL = productImageURL;
        this.productDesc = productDesc;
    }
    public ProductModel(ProductResponse productResponse) {
        this.id = productResponse.getID();
        this.productName = productResponse.getProductName();
        this.productPrice = productResponse.getProductCost();
        this.productStatus = String.valueOf(productResponse.getProductStatus());
        this.productImageURL = "https://ih1.redbubble.net/image.4646407321.9310/ssrco,classic_tee,mens,fafafa:ca443f4786,front_alt,square_product,600x600.jpg";
        this.productDesc = productResponse.getProductDescription();
    }

    protected ProductModel(Parcel in) {
        productName = in.readString();
        if (in.readByte() == 0) {
            productPrice = Long.parseLong(null);
        } else {
            productPrice = in.readLong();
        }
        productStatus = in.readString();
        productImageURL = in.readString();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public long getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(long productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    public String getProductImageURL() {
        return productImageURL;
    }

    public void setProductImageURL(String productImageURL) {
        this.productImageURL = productImageURL;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(productName);
        dest.writeByte((byte) 1);
        dest.writeLong(productPrice);
        dest.writeString(productStatus);
        dest.writeString(productImageURL);
        dest.writeString(productDesc);
    }
}
