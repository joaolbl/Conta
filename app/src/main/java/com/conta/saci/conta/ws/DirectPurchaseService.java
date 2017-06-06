package com.conta.saci.conta.ws;

import android.content.Context;
import android.os.AsyncTask;

import com.conta.saci.conta.R;
import com.conta.saci.conta.entity.Person;

import java.io.DataOutputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by JZLA on 06/06/2017.
 */

public class DirectPurchaseService {

    private Context context;

    DirectPurchaseService(Context context) {
        this.context = context;
    }

    public void saveDirectPurchase(Person buyer, Person requester, BigDecimal price, String comment, Date purchaseDate, String item) {
        //Convert date to correct format;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String purchaseDateString = sdf.format(purchaseDate);

        //Call web service
        AsyncTask<String, Void, Void> addDirectPurchase = new AddDirectPurchase();
        addDirectPurchase.execute(buyer.getId().toString(),
                requester.getId().toString(),
                price.toString(),
                comment,
                purchaseDateString,
                item);
    }


    /**
     * Call the web service that adds a new direct purchase for the user calling it and the requester. The order of the parameters passed in the execute method must be:
     * buyerId
     * requesterId
     * purchaseValue
     * comment
     * purchaseDate
     * item
     */

    class AddDirectPurchase extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {
            String urlString = context.getString(R.string.direct_purchase_ws_url_add);
            try {
                URL url = new URL(urlString);
                HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();


                //get parameters
                String buyerId = params[0];
                String requesterId = params[1];
                String price = params[2];
                String comment = params[3];
                String purchaseDate = params[4];
                String item = params[5];

                String wsParams = "buyerId=" + buyerId;
                wsParams += "&requesterId=" + requesterId;
                wsParams += "&purchaseValue=" + price;
                wsParams += "&comment=" + comment;
                wsParams += "&purchaseDate=" + purchaseDate;
                wsParams += "&item=" + item;

                DataOutputStream dos = new DataOutputStream(connection.getOutputStream());
                dos.writeBytes(wsParams);
                dos.flush();
                dos.close();

                if (connection.getResponseCode() != 200) {
                    throw new Exception("The web service call was unsuccessful. The returned code was: " + connection.getResponseCode());
                }

            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("There has been a problem while calling the web service", e);
            }
            return null;
        }
    }
}
