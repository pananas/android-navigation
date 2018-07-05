package code.session.login;

import android.widget.Toast;

import code.session.login.data.net.error.BaseError;
import code.session.login.data.net.error.BaseHttpError;
import dagger.android.support.DaggerAppCompatActivity;

public class BaseActivity extends DaggerAppCompatActivity {

    private Toast mToast;

    public void showMessage(String msg) {
        if (mToast != null) {
            mToast.cancel();
            mToast = null;
        }

        mToast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        mToast.show();
    }

    public void showGeneralError(Throwable exception) {
        if (exception instanceof BaseHttpError) {
            showMessage(((BaseHttpError) exception).getErrorMsg());
            return;
        }
        if (exception instanceof BaseError) {
            showMessage(getString(((BaseError) exception).getStringResId()));
        } else {
            showMessage(getString(R.string.error_text_general));
        }
    }
}
