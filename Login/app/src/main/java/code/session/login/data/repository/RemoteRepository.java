package code.session.login.data.repository;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import code.session.login.data.model.AdminLoginRequest;
import code.session.login.data.model.BaseReponse;
import code.session.login.data.model.DeviceNameRequest;
import code.session.login.data.model.DeviceNameResponse;
import code.session.login.data.model.UserLoginRequest;
import code.session.login.data.model.UserLoginResponse;
import code.session.login.data.net.NetManager;
import code.session.login.data.net.error.NoInternetConnectionError;
import code.session.login.service.ApiService;

public class RemoteRepository implements RemoteService {

    private ApiService mApiService;
    private NetManager mNetManager;

    public RemoteRepository(ApiService apiService, NetManager netManager) {
        mApiService = apiService;
        mNetManager = netManager;
    }

    @Override
    public Single<DeviceNameResponse> getDeviceName(DeviceNameRequest deviceNameRequest) {
        if (!mNetManager.isConnectedToInternet()) {
            return Single.error(new NoInternetConnectionError());
        }

        return mApiService
                .getDevice(deviceNameRequest)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Single<BaseReponse> adminLogin(AdminLoginRequest adminLoginRequest) {
        if (!mNetManager.isConnectedToInternet()) {
            return Single.error(new NoInternetConnectionError());
        }
        return mApiService
                .adminLogin(adminLoginRequest)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Single<UserLoginResponse> userLogin(UserLoginRequest userLoginRequest) {
        if (!mNetManager.isConnectedToInternet()) {
            return Single.error(new NoInternetConnectionError());
        }
        return mApiService
                .userLogin(userLoginRequest)
                .subscribeOn(Schedulers.io());
    }
}
