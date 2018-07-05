package code.session.login.data.repository;

import io.reactivex.Single;
import code.session.login.data.model.AdminLoginRequest;
import code.session.login.data.model.BaseReponse;
import code.session.login.data.model.DeviceNameRequest;
import code.session.login.data.model.DeviceNameResponse;
import code.session.login.data.model.UserLoginRequest;
import code.session.login.data.model.UserLoginResponse;

public interface RemoteService {
    Single<DeviceNameResponse> getDeviceName(DeviceNameRequest deviceNameRequest);
    Single<BaseReponse> adminLogin(AdminLoginRequest adminLoginRequest);
    Single<UserLoginResponse> userLogin(UserLoginRequest userLoginRequest);
}
