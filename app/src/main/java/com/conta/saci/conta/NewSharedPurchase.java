package com.conta.saci.conta;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.conta.saci.conta.entity.Person;
import com.conta.saci.conta.ws.SharedPurchaseService;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class NewSharedPurchase extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_shared_purchase);
    }

    public void submitSharedPurchase(View view) throws Exception {
        EditText value = (EditText) findViewById(R.id.shared_purchase_value);
        EditText description = (EditText) findViewById(R.id.shared_purchase_description);
        EditText purchaseDate = (EditText) findViewById(R.id.shared_purchase_date);
        if (value.getText().toString().trim().isEmpty() || purchaseDate.getText().toString().trim().isEmpty()) {
            Toast mandatoryFieldsToast = Toast.makeText(this, getString(R.string.shared_purchase_mandatory_fields_toast), Toast.LENGTH_SHORT);
            mandatoryFieldsToast.show();
            return;
        }
        String userId = getUser().getId().toString();
        new SharedPurchaseService().execute(userId,
                value.getText().toString(),
                description.getText().toString(),
                purchaseDate.getText().toString());
    }

    private Person getUser() throws Exception {
        FileInputStream fis = null;
        Person person = null;
        fis = openFileInput(getString(R.string.user_file_name));
        ObjectInputStream ois = new ObjectInputStream(fis);
        person = (Person) ois.readObject();
        return person;
    }
}
