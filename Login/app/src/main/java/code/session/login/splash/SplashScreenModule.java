package code.session.login.splash;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import code.session.login.data.di.FragmentScoped;

@Module
public abstract class SplashScreenModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract SplashScreenFragment splashScreenFragment();

    @FragmentScoped
    @Binds
    abstract SplashScreenContract.Presenter splashScreenPresenter(SplashScreenPresenterImpl presenter);
}
