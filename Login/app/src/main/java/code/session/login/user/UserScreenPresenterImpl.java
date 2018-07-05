package code.session.login.user;

import android.content.SharedPreferences;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;
import code.session.login.data.di.ActivityScoped;
import code.session.login.data.model.UserLoginRequest;
import code.session.login.data.model.UserLoginResponse;
import code.session.login.data.repository.RemoteService;
import code.session.login.data.utils.AppConstants;

@ActivityScoped
public class UserScreenPresenterImpl implements UserScreenContract.Presenter {

    private UserScreenContract.View mView;

    private final RemoteService mRemoteService;

    private CompositeDisposable mCompositeDisposable;

    private SharedPreferences mSharedPreferences;

    @Inject
    public UserScreenPresenterImpl(RemoteService remoteService, SharedPreferences sharedPreferences) {
        mRemoteService = remoteService;
        mSharedPreferences = sharedPreferences;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void userLogin(String rfid) {

        UserLoginRequest userLoginRequest = new UserLoginRequest(rfid);

        mCompositeDisposable.add(mRemoteService
                .userLogin(userLoginRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<UserLoginResponse>() {
                    @Override
                    public void onSuccess(UserLoginResponse response) {
                        if (response.getError()) {
                            mView.showMessage(response.getMessage());
                        } else {
                            Timber.d("User logged successfully");
                            mSharedPreferences.edit().putBoolean(AppConstants.USER_LOGGED, true).apply();
                            mView.setKioskMode(false);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showGeneralError(e);
                        Timber.e(e);
                    }
                }));
    }

    @Override
    public void initialise() {
        mView.setDeviceName(mSharedPreferences.getString(AppConstants.DEVICE_NAME, ""));

        mView.initialiseLayout(mSharedPreferences.getBoolean(AppConstants.USER_LOGGED, false));
    }

    @Override
    public void takeView(UserScreenContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
        mCompositeDisposable.clear();
    }
}
