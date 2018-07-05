package code.session.login.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserLoginRequest {
    @SerializedName("cardId")
    @Expose
    private String cardId;
    @SerializedName("serialNumber")
    @Expose
    private String serialNumber;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;

    public UserLoginRequest(String cardId) {
        this.cardId = cardId;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
