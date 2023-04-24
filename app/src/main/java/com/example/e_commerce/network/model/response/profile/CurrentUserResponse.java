//YApi QuickType插件生成，具体参考文档:https://github.com/RmondJone/YapiQuickType

package com.example.e_commerce.network.model.response.profile;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class CurrentUserResponse implements Parcelable {
    public static final Creator<CurrentUserResponse> CREATOR = new Creator<CurrentUserResponse>() {
        @Override
        public CurrentUserResponse createFromParcel(Parcel in) {
            return new CurrentUserResponse(in);
        }

        @Override
        public CurrentUserResponse[] newArray(int size) {
            return new CurrentUserResponse[size];
        }
    };
    private String name;
    private String email;
    private String telephoneNumber;
    private String deliveryAddress;

    public CurrentUserResponse(String name, String email, String telephoneNumber, String deliveryAddress) {
        this.name = name;
        this.email = email;
        this.telephoneNumber = telephoneNumber;
        this.deliveryAddress = deliveryAddress;
    }

    protected CurrentUserResponse(Parcel in) {
        name = in.readString();
        email = in.readString();
        telephoneNumber = in.readString();
        deliveryAddress = in.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(telephoneNumber);
        dest.writeString(deliveryAddress);
    }
}
