package com.example.kingship;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kingship.database.AccountDB;
import com.example.kingship.database.DBHelper;
import com.example.kingship.entities.Account;

import java.util.List;

public class Signin extends AppCompatActivity {

    EditText username, password;
    Button login;
    String type;
    TextView signup, adsign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));

        username = findViewById(R.id.editTextEmailSignin);
        password = findViewById(R.id.editTextPasswordSignin);
        login = findViewById(R.id.btnsignin);
        signup = findViewById(R.id.textView8);
        adsign = findViewById(R.id.adminsin);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Signup.class);
                startActivity(i);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonLogin_onClick(view);
            }
        });

        adsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), AdminLogin.class);
                startActivity(i);
            }
        });

    }
    public void buttonLogin_onClick(View view) {
        AccountDB accountDB = new AccountDB(getApplicationContext());
        String uname = username.getText().toString();
        String pass = password.getText().toString();
        Account account = accountDB.login(uname, pass);
        Account acc = accountDB.type(uname);
        if (account == null){
            AlertDialog.Builder builder = new  AlertDialog.Builder(view.getContext());
            builder.setTitle(R.string.error);
            builder.setMessage(R.string.invalid);
            builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            builder.show();

        }else {
            if(acc.getType().equals("customer")) {
                        Intent intent = new Intent(Signin.this, Customer.class);
                        intent.putExtra("account", account);
                        startActivity(intent);
                        Toast.makeText(Signin.this, "Login Success.", Toast.LENGTH_SHORT).show();
            }else{
                        Intent intent = new Intent(Signin.this, SellerHomelog.class);
                        intent.putExtra("account", account);
                        startActivity(intent);
                        Toast.makeText(Signin.this, "Login Success.", Toast.LENGTH_SHORT).show();
                    }

        }

    }

}