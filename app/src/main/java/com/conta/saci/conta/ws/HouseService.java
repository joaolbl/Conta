package com.conta.saci.conta.ws;

import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.util.JsonReader;

import com.conta.saci.conta.R;
import com.conta.saci.conta.entity.Gender;
import com.conta.saci.conta.entity.House;
import com.conta.saci.conta.entity.Person;

import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by JZLA on 31/05/2017.
 */

public class HouseService {

    private Context context;

    public HouseService(Context ctx){
        context = ctx;
    }

    public List<Person> getInhabitants(House house) throws Exception {
        AsyncTask<String, Void, List<Person>> inhabitants = new InhabiantsList().execute(house.getId().toString());
        return inhabitants.get();
    }

    class InhabiantsList extends AsyncTask<String, Void, List<Person>>{

        @Override
        protected List<Person> doInBackground(String... params) {
            String houseUrl = context.getString(R.string.house_ws_url);
            try {
                URL houseWsUrl = new URL(houseUrl);
                HttpsURLConnection connection = (HttpsURLConnection) houseWsUrl.openConnection();
                connection.setRequestProperty("houseId", params[0]);
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                String wsParam = "?houseId=" + params[0];
                DataOutputStream dos = new DataOutputStream(connection.getOutputStream());
                dos.writeBytes(wsParam);
                dos.flush();
                dos.close();

                int responseCode = connection.getResponseCode();
                System.out.println("URL: " + connection.getURL());
                System.out.println("Response code: " + responseCode);

                if (connection.getResponseCode() == 200) {
                    InputStreamReader isr = new InputStreamReader(connection.getInputStream(), "UTF-8");
                    JsonReader jreader = new JsonReader(isr);
                    List<Person> inhabitants = readInhabitantsList(jreader);
                    return inhabitants;
                }
            } catch (Exception e) {
                throw new RuntimeException("There has been an error while calling the webservice", e);
            }
            return null;
        }

        private List<Person> readInhabitantsList(JsonReader jreader) throws IOException {
            List<Person> persons = new ArrayList<Person>();

            jreader.beginArray();
            while (jreader.hasNext()) {
                Person p = readPerson(jreader);
                persons.add(p);
            }
            jreader.endArray();
            jreader.close();
            return persons;
        }

        private Person readPerson(JsonReader jreader) throws IOException {
            Person p = new Person();

            jreader.beginObject();
            while (jreader.hasNext()) {
                String fieldsName = jreader.nextName();
                switch (fieldsName) {
                    case "id":
                        p.setId(jreader.nextLong());
                        break;
                    case "name":
                        p.setName(jreader.nextString());
                        break;
                    case "email":
                        p.setEmail(jreader.nextString());
                        break;
                    case "gender":
                        p.setGender(Gender.valueOf(jreader.nextString()));
                        break;
                    default:
                        jreader.skipValue();
                }
            }
            jreader.endObject();
            return p;
        }
    }
}
