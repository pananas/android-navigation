package code.session.login.data.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetManager {
    private Context applicationContext;
    private Boolean status = false;

    public NetManager(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    public Boolean isConnectedToInternet() {
        ConnectivityManager conManager = (ConnectivityManager) applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conManager != null) {
            NetworkInfo ni = conManager.getActiveNetworkInfo();
            status = ni != null && ni.isConnected() ;
        }
        return status;
    }
}
