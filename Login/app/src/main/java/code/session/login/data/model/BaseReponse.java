package code.session.login.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaseReponse {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("error")
    @Expose
    private Boolean error;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }
}
