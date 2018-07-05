package code.session.login.user;

import code.session.login.BasePresenter;
import code.session.login.BaseView;

public interface UserScreenContract {
    interface Presenter extends BasePresenter<UserScreenContract.View> {
        void userLogin(String rfid);

        void initialise();
    }

    interface View extends BaseView {

        void showMessage(String message);

        void setKioskMode(boolean enabled);

        void initialiseLayout(boolean isUserLogged);

        void setDeviceName(String deviceName);
    }
}
