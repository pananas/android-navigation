package code.session.login.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeviceNameRequest {
    @SerializedName("serialNumber")
    @Expose
    private String serialNumber;

    public DeviceNameRequest(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
