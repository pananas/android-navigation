package code.session.login.data.net.error;


import code.session.login.R;

public class NoInternetConnectionError  extends BaseError {

    public boolean isNeedsToCloseApp() {
        return needsToCloseApp;
    }

    public void setNeedsToCloseApp(boolean needsToCloseApp) {
        this.needsToCloseApp = needsToCloseApp;
    }

    private boolean needsToCloseApp = false;

    public NoInternetConnectionError() {
        super(R.string.text_internet_off,0);
    }

    public NoInternetConnectionError(boolean needsToCloseApp) {
        super(R.string.text_internet_off,0);
        this.needsToCloseApp = needsToCloseApp;
    }
}
