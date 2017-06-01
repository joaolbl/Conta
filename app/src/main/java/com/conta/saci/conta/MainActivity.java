package com.conta.saci.conta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToNewSharedPurchase(View view){
        Intent intent = new Intent(this, NewSharedPurchase.class);
        startActivity(intent);
    }

    public void goToNewDirectPurchase(View view){
        Intent intent = new Intent(this, NewDirectPurchase.class);
        startActivity(intent);
    }
}
