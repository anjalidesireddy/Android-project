package com.grepthor.weforyoufoundation.helper;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;

import com.grepthor.weforyoufoundation.SplashActivity;


public class ConnectionDetector {
    private static ConnectionDetector connectionDetector = null;
    private Context context;

    private ConnectionDetector(Context context) {
        this.context = context;
    }

    public static ConnectionDetector getInstance(Context context) {
        if (connectionDetector == null) {
            connectionDetector = new ConnectionDetector(context);
        }
        return connectionDetector;
    }


    public static boolean isInternet(final Activity context) {
        ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        if (netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Connection Error");
            builder.setMessage("A network error occured while communicating with the server. Please check your internet connection!");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    context.startActivity(new Intent(context, SplashActivity.class));
                    context.finish();
                }
            });
            builder.show();
            return false;
        }
        return true;
    }

    public boolean isConnectionAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
//            NetworkInfo[] networkInfos = connectivityManager.getAllNetworkInfo();
            NetworkInfo networkInfos = connectivityManager.getActiveNetworkInfo();
//            if (networkInfos != null) {
//                for (NetworkInfo anInfo : networkInfos) {
//                    if (anInfo.getState() == NetworkInfo.State.CONNECTED) {
//                        return true;
//                    }
//                }
//            }

            if (networkInfos != null && networkInfos.isConnectedOrConnecting()) {
                        return true;
            }
        }
        return false;
    }

}
