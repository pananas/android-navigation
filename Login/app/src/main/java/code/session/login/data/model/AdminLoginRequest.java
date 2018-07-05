package code.session.login.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AdminLoginRequest {
    @SerializedName("adminPass")
    @Expose
    private String adminPass;

    public AdminLoginRequest(String adminPass) {
        this.adminPass = adminPass;
    }

    public String getAdminPass() {
        return adminPass;
    }

    public void setAdminPass(String adminPass) {
        this.adminPass = adminPass;
    }
}
