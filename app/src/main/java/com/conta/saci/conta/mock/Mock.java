package com.conta.saci.conta.mock;

import android.content.Context;

import com.conta.saci.conta.entity.Gender;
import com.conta.saci.conta.entity.House;
import com.conta.saci.conta.entity.Person;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * Created by JZLA on 02/06/2017.
 */

public class Mock {

    public Mock(Context context){
        this.context = context;
    }

    private Context context;

    public void mockHouse() {
        House h = new House();
        h.setId(new Long(1));
        h.setHouseName("Test House");
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(h);
            FileOutputStream fos = context.openFileOutput("house.ser", Context.MODE_PRIVATE);
            fos.write(bos.toByteArray());
            fos.close();
            oos.close();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("There has been a problem while serializing house", e);
        }
    }

    public void mockUser(){
        Person p = new Person();
        p.setId(new Long(1));
        p.setName("Saci");
        p.setGender(Gender.MALE);
        p.setEmail("saci@email.com");
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(p);
            FileOutputStream fos = context.openFileOutput("user.ser", Context.MODE_PRIVATE);
            fos.write(bos.toByteArray());
            fos.close();
            oos.close();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("There has been a problem while serializing user", e);
        }
    }
}
