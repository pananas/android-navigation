package code.session.login.data.net.error;


import code.session.login.R;

public class BaseError extends Exception{
    private int stringResId = R.string.error_text_general;
    private int errorCode = 0;

    public BaseError(int stringResId, int errorCode) {
        this.stringResId = stringResId;
        this.errorCode = errorCode;
    }

    public BaseError() {
    }

    public int getStringResId() {
        return stringResId;
    }

    public int getErrorCode() {
        return errorCode;
    }
}