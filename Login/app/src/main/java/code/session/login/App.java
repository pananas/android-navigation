package code.session.login;

import android.support.annotation.NonNull;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import timber.log.Timber;
import code.session.login.data.di.DaggerAppComponent;

public class App extends DaggerApplication {

    private static App sInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;

        configureTimber();
    }

    public static App getApp() {
        return sInstance;
    }

    /**
     * Configure timber logger output line format.
     */
    private void configureTimber() {
        Timber.plant(new Timber.DebugTree() {
            @Override
            protected String createStackElementTag(@NonNull StackTraceElement element) {
                return super.createStackElementTag(element) + " [line " + element.getLineNumber() + "]";
            }
        });
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().create(this);
    }
}
