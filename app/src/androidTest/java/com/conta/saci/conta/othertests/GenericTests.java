package com.conta.saci.conta.othertests;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.JsonReader;

import com.conta.saci.conta.entity.Gender;
import com.conta.saci.conta.entity.Person;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JZLA on 01/06/2017.
 */

@RunWith(AndroidJUnit4.class)
public class GenericTests {

    @Test
    public void jsonTest() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        String jsonString = "[{\"id\":1,\"name\":\"saci\",\"gender\":\"MALE\",\"email\":\"saci@mail.com\"},{\"id\":2,\"name\":\"iara\",\"gender\":\"FEMALE\",\"email\":\"iara@mail.com\"}]";
        InputStream isr = new ByteArrayInputStream(jsonString.getBytes(StandardCharsets.UTF_8));
        JsonReader jr = new JsonReader(new InputStreamReader(isr, "UTF-8"));
        List<Person> inhabitants = null;
        try {
            inhabitants = readPersonArray(jr);
        } catch (Exception e) {
            throw new Exception("Deu ruim!");
        }
        System.out.println("List size: " + inhabitants.size());
        System.out.println("Persons:");
        for (Person p : inhabitants) {
            System.out.println(p.getName());
            System.out.println(p.getId());
            System.out.println(p.getEmail());
            System.out.println(p.getGender());
        }
    }

    private List<Person> readPersonArray(JsonReader jr) throws Exception {
        List<Person> inhabitants = new ArrayList<Person>();

        jr.beginArray();
        while (jr.hasNext()) {
            Person p = readPerson(jr);
            inhabitants.add(p);
        }
        jr.endArray();
        jr.close();
        return inhabitants;
    }

    private Person readPerson(JsonReader jr) throws IOException {
        Person p = new Person();
        jr.beginObject();

        while (jr.hasNext()) {
            String fieldName = jr.nextName();
            switch (fieldName) {
                case "id":
                    p.setId(jr.nextLong());
                    break;
                case "name":
                    p.setName(jr.nextString());
                    break;
                case "gender":
                    p.setGender(Gender.valueOf(jr.nextString()));
                    break;
                case "email":
                    p.setEmail(jr.nextString());
                    break;
                default:
                    jr.skipValue();
            }
        }

        jr.endObject();
        return p;
    }
}
