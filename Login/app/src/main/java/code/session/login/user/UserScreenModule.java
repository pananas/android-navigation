package code.session.login.user;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import code.session.login.data.di.FragmentScoped;

@Module
public abstract class UserScreenModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract UserLoginFragment userLoginFragment();

    @FragmentScoped
    @Binds
    abstract UserScreenContract.Presenter userPresenter(UserScreenPresenterImpl presenter);
}
