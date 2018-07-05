package code.session.login.splash;

import android.content.SharedPreferences;

import java.util.Date;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;
import code.session.login.data.di.ActivityScoped;
import code.session.login.data.model.DeviceNameRequest;
import code.session.login.data.model.DeviceNameResponse;
import code.session.login.data.repository.RemoteService;
import code.session.login.data.utils.AppConstants;

@ActivityScoped
public class SplashScreenPresenterImpl implements SplashScreenContract.Presenter {

    private static final int SPLASH_SCREEN_TIME = 2000;

    private SplashScreenContract.View mView;

    private CompositeDisposable mCompositeDisposable;

    private final RemoteService mRemoteService;
    private final SharedPreferences mSharedPreferences;

    @Inject
    public SplashScreenPresenterImpl(RemoteService remoteService, SharedPreferences sharedPreferences) {
        mRemoteService = remoteService;
        mSharedPreferences = sharedPreferences;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void getDeviceName(String serialNumber) {

        DeviceNameRequest deviceNameRequest = new DeviceNameRequest(serialNumber);

        Date splashScreenStartTime = new Date();

        mCompositeDisposable.add(mRemoteService
                .getDeviceName(deviceNameRequest)
                .subscribeOn(Schedulers.io())
                .map(deviceNameResponse -> {
                    Date date = new Date();
                    // Calculate if SPLASH_SCREEN_TIME has passed
                    long difference = date.getTime() - splashScreenStartTime.getTime();
                    if (difference < SPLASH_SCREEN_TIME) {
                        Thread.sleep(SPLASH_SCREEN_TIME - difference);
                    }
                    return deviceNameResponse;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<DeviceNameResponse>() {
                    @Override
                    public void onSuccess(DeviceNameResponse deviceNameResponse) {
                        Timber.d("Device name: %s", deviceNameResponse.getDeviceName());
                        mSharedPreferences.edit().putString(AppConstants.DEVICE_NAME, deviceNameResponse.getDeviceName()).apply();
                        mView.navigateToLogin();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showGeneralError(e);
                        mView.navigateToLogin();
                        Timber.e(e);
                    }
                }));
    }

    @Override
    public void takeView(SplashScreenContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
        mCompositeDisposable.clear();
    }
}
