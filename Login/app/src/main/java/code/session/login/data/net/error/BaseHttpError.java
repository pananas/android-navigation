package code.session.login.data.net.error;

public class BaseHttpError extends Exception{

    private String errorMsg;

    public BaseHttpError( String errorMsg) {
        super();
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}