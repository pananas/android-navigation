package code.session.login.data.di;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import code.session.login.App;
import code.session.login.data.net.NetworkModule;
import code.session.login.main.ActivityBindingModule;

@Singleton
@Component(modules = {
        AppModule.class,
        ActivityBindingModule.class,
        NetworkModule.class,
        LocalStorageModule.class,
        AndroidSupportInjectionModule.class})
public interface AppComponent extends AndroidInjector<App> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<App> {
    }
}
