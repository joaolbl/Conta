package com.conta.saci.conta.ws;

import android.content.res.Resources;
import android.os.AsyncTask;

import com.conta.saci.conta.R;

import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by JZLA on 30/05/2017.
 */

public class SharedPurchaseService extends AsyncTask<String, Void, Void> {


    @Override
    protected Void doInBackground(String[] params) {
        if (params.length != 4) {
            throw new RuntimeException("Missing parameters");
        }
        String wsUrl = Resources.getSystem().getString(R.string.shared_purchase_ws_url);
        try {
            URL sharedPurchaseUrl = new URL(wsUrl);
            HttpsURLConnection connection = (HttpsURLConnection) sharedPurchaseUrl.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            String wsParams = "?personId=" + params[0];
            wsParams += ",value=" + params[1];
            wsParams += ",comments=" + params[2];
            wsParams += ",purchaseDate=" + params[3];
            connection.getOutputStream().write(wsParams.getBytes());
        } catch (Exception e) {
            throw new RuntimeException("There has been an error while calling the web service", e);
        }
        return null;
    }
}
