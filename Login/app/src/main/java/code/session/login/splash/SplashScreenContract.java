package code.session.login.splash;

import code.session.login.BasePresenter;
import code.session.login.BaseView;

public interface SplashScreenContract {
    interface Presenter extends BasePresenter<SplashScreenContract.View> {
        void getDeviceName(String serialNumber);
    }

    interface View extends BaseView {
        void navigateToLogin();
    }
}
