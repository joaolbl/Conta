package com.conta.saci.conta.entity;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by JZLA on 30/05/2017.
 */

public class PersonSerializeTest {

    @Test
    public void serializeTest(){
        Person p = new Person();
        p.setId(new Long(1));
        p.setEmail("joao@joao.com");
        p.setGender(Gender.MALE);
        p.setName("Joao");
        ObjectOutputStream bw;
        FileOutputStream os;
        File f;
        try {
            f = new File("C:/Users/jzla/personTest.txt");
            os = new FileOutputStream(f);
            bw = new ObjectOutputStream(os);
            bw.writeObject(p);
            bw.close();
            os.close();
        } catch (Exception e) {
            System.out.print("Fudeu");
            e.printStackTrace();
        }
    }

    @Test
    public void deserializeTest(){
        Person p = new Person();
        File f = new File("C:/Users/jzla/personTest.txt");
        FileInputStream fi;
        ObjectInputStream oi;
        try {
            fi = new FileInputStream(f);
            oi = new ObjectInputStream(fi);
            p = (Person) oi.readObject();
            oi.close();
            fi.close();
        } catch (Exception e) {
            System.err.print("Fudeu!");
            e.printStackTrace();
        }
        System.out.print(p.getId());
        System.out.println(p.getEmail());
        System.out.println(p.getGender());
        System.out.println(p.getName());
    }
}
