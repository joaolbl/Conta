package com.conta.saci.conta.ws;

import android.content.res.Resources;
import android.os.AsyncTask;

import com.conta.saci.conta.R;
import com.conta.saci.conta.entity.Person;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by JZLA on 31/05/2017.
 */

public class HouseService {

    class InhabiantsList extends AsyncTask<String, Void, List<Person>>{

        @Override
        protected List<Person> doInBackground(String... params) {
            String houseUrl = Resources.getSystem().getString(R.string.house_ws_url);
            try{
                URL houseWsUrl = new URL(houseUrl);
                HttpsURLConnection connection = (HttpsURLConnection) houseWsUrl.openConnection();
                connection.setDoOutput(true);
                String wsParam = "?houseId=" + params[0];
                connection.getOutputStream().write(wsParam.getBytes());
            } catch (Exception e) {
                throw new RuntimeException("There has been an error while calling the webservice", e);
            }
            return null;
        }
    }
}
