package code.session.login.service;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;
import code.session.login.data.model.AdminLoginRequest;
import code.session.login.data.model.BaseReponse;
import code.session.login.data.model.DeviceNameRequest;
import code.session.login.data.model.DeviceNameResponse;
import code.session.login.data.model.UserLoginRequest;
import code.session.login.data.model.UserLoginResponse;

public interface ApiService {

    @POST("/getDeviceName")
    Single<DeviceNameResponse> getDevice(@Body DeviceNameRequest deviceNameRequest);

    @POST("/adminLogin")
    Single<BaseReponse> adminLogin(@Body AdminLoginRequest adminLoginRequest);

    @POST("/login")
    Single<UserLoginResponse> userLogin(@Body UserLoginRequest userLoginRequest);
}
