package code.session.login.data.di;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class LocalStorageModule {
    private static final String PREF_NAME = "my_preferences";

    @Singleton
    @Provides
    SharedPreferences providePreferences(Application application) {
        return application.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }
}
