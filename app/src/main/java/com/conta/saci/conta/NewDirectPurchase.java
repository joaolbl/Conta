package com.conta.saci.conta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.conta.saci.conta.entity.House;
import com.conta.saci.conta.entity.Person;
import com.conta.saci.conta.ws.HouseService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NewDirectPurchase extends AppCompatActivity {

    Person user = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_direct_purchase);
        setupUser();
        setupSpinner();
    }

    private void setupUser() {
        FileInputStream fis = null;
        try{
            fis = openFileInput(getString(R.string.user_file_name));
            ObjectInputStream ois = new ObjectInputStream(fis);
            user = (Person) ois.readObject();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Something went wrong while reading the user file", e);
        }
    }

    private void setupSpinner() {
        List<Person> inhabitants = null;
        House house = getHouse();
        try{
            inhabitants = new HouseService(this).getInhabitants(house);
            //remove the current user from the inhabitants list
            for(Person p : inhabitants){
                if(p.getId() == user.getId()){
                    inhabitants.remove(p);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("There has been a problem while retrieving the inhabitants list", e);
        }
        Spinner inhabitantsSpinner = (Spinner) findViewById(R.id.user_list);
        ArrayAdapter<Person> adapter = new ArrayAdapter<Person>(this, R.layout.support_simple_spinner_dropdown_item, inhabitants);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        inhabitantsSpinner.setAdapter(adapter);
    }

    public void submitDirectPurchase(View view){
        //get widgets values
        EditText purchaseValueWidget = (EditText) findViewById(R.id.direct_purchase_purchase_value);
        BigDecimal price = new BigDecimal(purchaseValueWidget.getText().toString());

        Spinner requesterWidget = (Spinner) findViewById(R.id.user_list);
        Person requester = (Person) requesterWidget.getSelectedItem();

        EditText purchaseDateWidget = (EditText) findViewById(R.id.direct_purchase_date);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date purchaseDate = sdf.parse(purchaseDateWidget.getText().toString());
        }catch (Exception e){
            Toast.makeText(this, getString(R.string.invalid_date_format), Toast.LENGTH_LONG);
        }

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