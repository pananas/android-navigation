package code.session.login.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeviceNameResponse extends BaseReponse{
    @SerializedName("deviceName")
    @Expose
    private String deviceName;

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }
}
