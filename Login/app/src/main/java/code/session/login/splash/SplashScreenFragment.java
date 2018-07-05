package code.session.login.splash;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;

import javax.inject.Inject;

import androidx.navigation.Navigation;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.DaggerFragment;
import code.session.login.main.MainActivity;
import code.session.login.R;

public class SplashScreenFragment extends DaggerFragment implements SplashScreenContract.View {

    private Unbinder mUnbinder;

    @Inject
    SplashScreenContract.Presenter mPresenter;

    @SuppressLint("HardwareIds")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash_screen, container, false);

        mUnbinder = ButterKnife.bind(this, view);

        mPresenter.takeView(this);

        mPresenter.getDeviceName(Settings.Secure.getString(Objects.requireNonNull(getContext()).getContentResolver(),
                Settings.Secure.ANDROID_ID));

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        mPresenter.takeView(this);
    }

    @Override
    public void navigateToLogin() {
        Navigation.findNavController(Objects.requireNonNull(getView())).navigate(R.id.go2UserLogin);
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
