package code.session.login.admin;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import code.session.login.data.di.FragmentScoped;

@Module
public abstract class AdminScreenModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract AdminLoginFragment adminScreenFragment();

    @FragmentScoped
    @Binds
    abstract AdminScreenContract.Presenter adminScreenPresenter(AdminScreenPresenterImpl presenter);

}
