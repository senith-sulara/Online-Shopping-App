package com.example.kingship;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kingship.entities.Account;

public class SellerHomelog extends AppCompatActivity {

    Button actbtn, pro,prod;
    private Account account;
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_homelog);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));

        Intent intent = getIntent();
        account = (Account) intent.getSerializableExtra("account");
        actbtn =findViewById(R.id.acctbtn);
        pro = findViewById(R.id.button);
        prod = findViewById(R.id.ProdBtn);

        actbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                Intent i = new Intent(getApplicationContext(),Accountbalance.class);
                startActivity(i);

            }
        });

        pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                Intent i = new Intent(SellerHomelog.this,SellerProfile.class);
                i.putExtra("account", account);
                startActivity(i);

            }
        });

        prod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),DisplayProduct.class);
                startActivity(i);
            }
        });

    }
}
