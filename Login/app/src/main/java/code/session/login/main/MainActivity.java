package code.session.login.main;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.jakewharton.processphoenix.ProcessPhoenix;

import javax.inject.Inject;

import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import butterknife.BindView;
import butterknife.ButterKnife;
import code.session.login.BaseActivity;
import code.session.login.R;

public class MainActivity extends BaseActivity implements MainContract.View {

    private static final String KIOSK_ENABLE = "enable";
    private static final String MODIFY_KIOSK_MODE_ACTION = "com.symbol.enterprisehomescreen.actions.MODIFY_KIOSK_MODE";
    private static final String LABEL_USER_LOGIN_FRAGMENT = "fragment_user_login";

    @Inject
    MainContract.Presenter mPresenter;

    @BindView(R.id.tv_internet_connection)
    TextView tvInternetConnection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mPresenter.takeView(this);

        mPresenter.listenForInternetChanges();

        mPresenter.checkIfUserIsLogged();
    }

    @Override
    protected void onResume() {
        super.onResume();

        mPresenter.takeView(this);
    }

    @Override
    public void onBackPressed() {
        NavDestination des = Navigation.findNavController(this, R.id.nav_host_fragment).getCurrentDestination();
        // If user taps back on login screen, exit app and don't show splash screen
        if (des != null && des.getLabel() != null && des.getLabel().equals(LABEL_USER_LOGIN_FRAGMENT)) {
            moveTaskToBack(true);
        } else {
            // Otherwise do natural up behaviour
            super.onBackPressed();
        }
    }

    /**
     * Enable/disable kiosk mode using reflection based on user logged data. Broadcast that will
     * be send will reconfigure device.
     *
     * @param userLogged boolean flag that indicates if user is logged
     */
    public void setKioskModeEnabled(boolean userLogged) {
        Intent intent = new Intent(MODIFY_KIOSK_MODE_ACTION);
        intent.putExtra(KIOSK_ENABLE, !userLogged);
        sendBroadcast(intent);

        mPresenter.setUserLogged(userLogged);

        if (userLogged) {
            finish();
        } else {
            ProcessPhoenix.triggerRebirth(MainActivity.this);
        }
    }

    @Override
    public void showInternetChanges(boolean isConnected) {
        int bckgColor;
        int txtColor;
        int txt;
        if (isConnected) {
            bckgColor = Color.TRANSPARENT;
            txtColor = ContextCompat.getColor(MainActivity.this, R.color.green);
            txt = R.string.text_internet_on;
        } else {
            bckgColor = ContextCompat.getColor(MainActivity.this, R.color.red);
            txtColor = ContextCompat.getColor(MainActivity.this, R.color.white);
            txt = R.string.text_internet_off;
        }
        tvInternetConnection.setBackgroundColor(bckgColor);
        tvInternetConnection.setTextColor(txtColor);
        tvInternetConnection.setText(txt);
    }

    @Override
    public void goToUserScreen() {
        Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.go2UserLogin);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mPresenter.dropView();
    }
}
