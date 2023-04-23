//YApi QuickType插件生成，具体参考文档:https://github.com/RmondJone/YapiQuickType

package com.example.e_commerce.network.model.response.profile;

public class CurrentUserResponse {
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

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelephoneNumber() { return telephoneNumber; }
    public void setTelephoneNumber(String telephoneNumber) { this.telephoneNumber = telephoneNumber; }

    public String getDeliveryAddress() { return deliveryAddress; }

    public void setDeliveryAddress(String deliveryAddress) { this.deliveryAddress = deliveryAddress; }
}
