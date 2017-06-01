package com.conta.saci.conta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class NewDirectPurchase extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_direct_purchase);
        //TODO: initialize the user's list/spinner
    }

    public void submitDirectPurchase(View view){
        //TODO: call web service to persist the purchase
    }

}