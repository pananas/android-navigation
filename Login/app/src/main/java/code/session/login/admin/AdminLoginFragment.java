package code.session.login.admin;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Objects;

import javax.inject.Inject;

import androidx.navigation.Navigation;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import code.session.login.R;
import dagger.android.support.DaggerFragment;
import code.session.login.main.MainActivity;

public class AdminLoginFragment extends DaggerFragment implements AdminScreenContract.View {

    @BindView(R.id.img_back)
    ImageView imgBack;

    @BindView(R.id.btn_admin_login)
    Button btnAdminLogin;

    @Inject
    AdminScreenPresenterImpl mPresenter;

    private Unbinder mUnbinder;

    public AdminLoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_login, container, false);

        mUnbinder = ButterKnife.bind(this, view);

        mPresenter.takeView(this);

        mPresenter.checkIsUserIsLogged();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        mPresenter.takeView(this);
    }

    @OnClick(R.id.img_back)
    public void onImgBackClick(View view) {
        Navigation.findNavController(view).navigateUp();
    }

    @SuppressLint("HardwareIds")
    @OnClick(R.id.btn_admin_login)
    public void onAdminLoginClicked() {
        if (btnAdminLogin.getText().equals(getString(R.string.text_logout))) {
            setKioskMode(false);
        } else {
            mPresenter.adminLogin(Settings.Secure.getString(Objects.requireNonNull(getContext()).getContentResolver(),
                    Settings.Secure.ANDROID_ID));
        }
    }

    @Override
    public void showMessage(String message) {
        ((MainActivity) Objects.requireNonNull(getActivity())).showMessage(message);
    }

    @Override
    public void setKioskMode(boolean userLogged) {
        ((MainActivity) Objects.requireNonNull(getActivity())).setKioskModeEnabled(userLogged);
    }

    @Override
    public void setBtnText(boolean isUserLogged) {
        if (isUserLogged) {
            btnAdminLogin.setText(getString(R.string.text_logout));
        } else {
            btnAdminLogin.setText(getString(R.string.text_login));
        }
    }

    @Override
    public void showGeneralError(Throwable exception) {
        ((MainActivity) Objects.requireNonNull(getActivity())).showGeneralError(exception);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mPresenter.dropView();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }
}
