package com.conta.saci.conta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.conta.saci.conta.entity.House;
import com.conta.saci.conta.entity.Person;
import com.conta.saci.conta.ws.HouseService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.util.List;

public class NewDirectPurchase extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_direct_purchase);
        setupSpinner();
    }

    private void setupSpinner() {
        List<Person> inhabitants = null;
        House house = getHouse();
        try{
            inhabitants = new HouseService(this).getInhabitants(house);
        } catch (Exception e) {
            throw new RuntimeException("There has been a problem while retrieving the inhabitants list", e);
        }
        Spinner inhabitantsSpinner = (Spinner) findViewById(R.id.user_list);
        ArrayAdapter<Person> adapter = new ArrayAdapter<Person>(this, R.layout.support_simple_spinner_dropdown_item, inhabitants);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        inhabitantsSpinner.setAdapter(adapter);
    }

    public void submitDirectPurchase(View view){
        //TODO: call web service to persist the purchase
    }

    public House getHouse() {
        FileInputStream fis = null;
        House house = null;
        try {
            fis = openFileInput("house.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            house = (House) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("There has been an error while getting the house data", e);
        }
        return house;
    }
}