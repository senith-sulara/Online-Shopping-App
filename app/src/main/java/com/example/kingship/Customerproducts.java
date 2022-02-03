package com.example.kingship;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kingship.database.DBHelper;
import com.example.kingship.entities.Account;

public class Customerproducts extends AppCompatActivity {

    TextView welcome;
    Button profile, logout, prod;
    private Account account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customerproducts);

        welcome = findViewById(R.id.welcm);
//set welcome text with the user name
        Intent intent = getIntent();
        account = (Account) intent.getSerializableExtra("account");

//        welcome.setText("Welcome "+ " " + account.getUsername());

        profile = findViewById(R.id.btnpro);
        logout = findViewById(R.id.btnLogout);
        prod = findViewById(R.id.btnproduct);

        prod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Customerproducts.this, Customer.class);
                startActivity(i);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Customerproducts.this, Signin.class);
                i.putExtra("account", account);
                startActivity(i);
                Toast.makeText(Customerproducts.this, "Successfully logout.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Customerproducts.this, Profile.class);
                i.putExtra("account", account);
                startActivity(i);
            }
        });
    }
}