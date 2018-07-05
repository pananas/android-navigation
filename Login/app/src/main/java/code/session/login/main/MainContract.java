package code.session.login.main;

import code.session.login.BasePresenter;
import code.session.login.BaseView;

public interface MainContract {
    interface Presenter extends BasePresenter<MainContract.View> {
        void listenForInternetChanges();

        void checkIfUserIsLogged();

        void setUserLogged(boolean userLogged);
    }

    interface View extends BaseView {
        void showInternetChanges(boolean isConnected);

        void goToUserScreen();
    }
}
