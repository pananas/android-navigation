package code.session.login.main;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import code.session.login.data.di.ActivityScoped;
import code.session.login.admin.AdminScreenModule;
import code.session.login.splash.SplashScreenModule;
import code.session.login.user.UserScreenModule;

@Module
public abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = {SplashScreenModule.class, UserScreenModule.class, AdminScreenModule.class})
    abstract MainActivity mainActivity();

    @ActivityScoped
    @Binds
    abstract MainContract.Presenter mainPresenter(MainPresenterImpl presenter);

}
