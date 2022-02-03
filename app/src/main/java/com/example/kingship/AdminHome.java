package com.example.kingship;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class AdminHome extends AppCompatActivity {

    Button cus,sell,cate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        cus = findViewById(R.id.cusbtn);
        sell = findViewById(R.id.selbtn);
        cate = findViewById(R.id.catbtn);

        cus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(AdminHome.this,RemoveCustomer.class);
                startActivity(i);
            }
        });
        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(AdminHome.this,RemoveSeller.class);
                startActivity(i);
            }
        });

        cate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(AdminHome.this, category.class);
                startActivity(i);
            }
        });
    }
}