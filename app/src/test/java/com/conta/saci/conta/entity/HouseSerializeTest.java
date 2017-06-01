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

public class HouseSerializeTest {

    @Test
    public void serializeTest() {
        House h = new House();
        h.setHouseName("Test House");
        h.setId(new Long(1));
        ObjectOutputStream bw;
        FileOutputStream os;
        File f;
        try {
            f = new File("C:/Users/jzla/lala.txt");
            os = new FileOutputStream(f);
            bw = new ObjectOutputStream(os);
            bw.writeObject(h);
            bw.close();
            os.close();
        } catch (Exception e) {
            System.out.print("Fudeu");
            e.printStackTrace();
        }
    }

    @Test
    public void deserializeTest() {
        House h = new House();
        File f = new File("C:/Users/jzla/lala.txt");
        FileInputStream fi;
        ObjectInputStream oi;
        try {
            fi = new FileInputStream(f);
            oi = new ObjectInputStream(fi);
            h = (House) oi.readObject();
            oi.close();
            fi.close();
        } catch (Exception e) {
            System.err.print("Fudeu!");
            e.printStackTrace();
        }
        System.out.print(h.getHouseName());
        System.out.print(h.getId());
    }
}
