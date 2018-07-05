package code.session.login.data.di;

import android.app.Application;

import dagger.Binds;
import dagger.Module;
import code.session.login.App;

@Module
public abstract class AppModule {

    @Binds
    abstract Application provideApplication(App app);
}
