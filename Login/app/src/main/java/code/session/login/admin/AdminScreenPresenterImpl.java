package code.session.login.admin;

import android.content.SharedPreferences;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;
import code.session.login.data.di.ActivityScoped;
import code.session.login.data.model.AdminLoginRequest;
import code.session.login.data.model.BaseReponse;
import code.session.login.data.repository.RemoteService;
import code.session.login.data.utils.AppConstants;

@ActivityScoped
public class AdminScreenPresenterImpl implements AdminScreenContract.Presenter {

    private AdminScreenContract.View mView;

    private final RemoteService mRemoteService;

    private CompositeDisposable mCompositeDisposable;

    private SharedPreferences mSharedPreferences;

    @Inject
    public AdminScreenPresenterImpl(RemoteService remoteService, SharedPreferences sharedPreferences) {
        mRemoteService = remoteService;
        mSharedPreferences = sharedPreferences;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void adminLogin(String adminPassword) {

        AdminLoginRequest adminLoginRequest = new AdminLoginRequest(adminPassword);

        mCompositeDisposable.add(mRemoteService
                .adminLogin(adminLoginRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<BaseReponse>() {
                    @Override
                    public void onSuccess(BaseReponse response) {
                        if (response.getError()) {
                            mView.showMessage(response.getMessage());
                        } else {
                            Timber.d("Admin login done");
                            mSharedPreferences.edit().putBoolean(AppConstants.ADMIN_LOGGED, true).apply();
                            mView.setKioskMode(true);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e);
                        mView.showGeneralError(e);
                    }
                }));
    }

    @Override
    public void checkIsUserIsLogged() {
        mView.setBtnText(mSharedPreferences.getBoolean(AppConstants.USER_LOGGED, false));
    }

    @Override
    public void takeView(AdminScreenContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
        mCompositeDisposable.clear();
    }
}
