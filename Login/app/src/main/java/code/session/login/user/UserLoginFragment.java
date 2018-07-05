package code.session.login.user;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

import javax.inject.Inject;

import androidx.navigation.Navigation;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dagger.android.support.DaggerFragment;
import code.session.login.R;
import code.session.login.main.MainActivity;

public class UserLoginFragment extends DaggerFragment implements UserScreenContract.View {

    @BindView(R.id.img_admin)
    ImageView imgAdmin;

    @BindView(R.id.tv_scan_rfid)
    TextView tvScanRfid;

    @BindView(R.id.tv_device_name)
    TextView tvDeviceName;

    @BindView(R.id.btn_logout)
    Button btnLogout;

    @Inject
    UserScreenContract.Presenter mPresenter;

    private Unbinder mUnbinder;

    public UserLoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_login, container, false);

        mUnbinder = ButterKnife.bind(this, view);

        mPresenter.takeView(this);

        mPresenter.initialise();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        mPresenter.takeView(this);
    }

    @OnClick(R.id.img_admin)
    public void go2AdminLogin(View view) {
        Navigation.findNavController(view).navigate(R.id.go2AdminLogin);
    }

    @Override
    public void showGeneralError(Throwable exception) {
        ((MainActivity) Objects.requireNonNull(getActivity())).showGeneralError(exception);
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
    public void initialiseLayout(boolean isUserLogged) {
        if (isUserLogged) {
            btnLogout.setVisibility(View.VISIBLE);
            tvDeviceName.setVisibility(View.GONE);
            tvScanRfid.setVisibility(View.GONE);
        } else {
            btnLogout.setVisibility(View.GONE);
            tvDeviceName.setVisibility(View.VISIBLE);
            tvScanRfid.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setDeviceName(String deviceName) {
        tvDeviceName.setText(deviceName);
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
