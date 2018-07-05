package code.session.login.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserLoginResponse {
    @SerializedName("cardId")
    @Expose
    private String cardId;
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("fileVersion")
    @Expose
    private String fileVersion;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("sessionId")
    @Expose
    private Integer sessionId;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("userName")
    @Expose
    private String userName;

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getFileVersion() {
        return fileVersion;
    }

    public void setFileVersion(String fileVersion) {
        this.fileVersion = fileVersion;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
