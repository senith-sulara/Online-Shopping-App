package com.example.kingship;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.kingship.entities.Account;

public class Seller extends AppCompatActivity {

    TextView welcome;
    Button profile;
    private Account account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller);

        welcome = findViewById(R.id.welcmsel);
        profile = findViewById(R.id.btnprofile);

//set welcome text with the user name
        Intent intent = getIntent();
        account = (Account) intent.getSerializableExtra("account");

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Seller.this, Profile.class);
                i.putExtra("account", account);
                startActivity(i);
            }
        });

    }
}