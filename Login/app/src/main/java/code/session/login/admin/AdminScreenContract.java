package code.session.login.admin;

import code.session.login.BasePresenter;
import code.session.login.BaseView;

public interface AdminScreenContract {

    interface Presenter extends BasePresenter<AdminScreenContract.View> {
        void adminLogin(String adminPassword);

        void checkIsUserIsLogged();
    }


    interface View extends BaseView {

        void showMessage(String message);

        void setKioskMode(boolean userLogged);

        void setBtnText(boolean isUserLogged);
    }
}
