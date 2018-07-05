package code.session.login.main;

import android.content.SharedPreferences;
import android.net.NetworkInfo;

import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;
import code.session.login.App;
import code.session.login.data.utils.AppConstants;

public class MainPresenterImpl implements MainContract.Presenter{

    @Inject
    SharedPreferences mSharedPreferences;

    private MainContract.View mView;

    private Disposable networkDisposable;

    @Inject
    public MainPresenterImpl(SharedPreferences sharedPreferences) {
        mSharedPreferences = sharedPreferences;
    }

    @Override
    public void listenForInternetChanges() {
        networkDisposable = ReactiveNetwork.observeNetworkConnectivity(App.getApp().getApplicationContext())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(connectivity -> {
                    if (connectivity.getState() == NetworkInfo.State.CONNECTED) {
                        mView.showInternetChanges(true);
                        Timber.d("Connected");
                    } else if (connectivity.getState() == NetworkInfo.State.DISCONNECTED) {
                        mView.showInternetChanges(false);
                        Timber.d("Disconnected");
                    }
                });
    }

    @Override
    public void checkIfUserIsLogged() {
        if (mSharedPreferences.getBoolean(AppConstants.USER_LOGGED, false)) {
            mView.goToUserScreen();
        }
    }

    @Override
    public void setUserLogged(boolean userLogged) {
        mSharedPreferences.edit().putBoolean(AppConstants.USER_LOGGED, userLogged).apply();
    }

    @Override
    public void takeView(MainContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        networkDisposable.dispose();
        mView = null;
    }
}
