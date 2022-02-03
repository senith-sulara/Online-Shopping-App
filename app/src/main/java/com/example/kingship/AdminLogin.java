package com.example.kingship;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kingship.database.AccountDB;
import com.example.kingship.entities.Account;

public class AdminLogin extends AppCompatActivity {

    EditText username, password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        username = findViewById(R.id.editadTextEmailSignin);
        password = findViewById(R.id.editadTextPasswordSignin);
        login = findViewById(R.id.btnsignin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonLogin_onClick(view);
            }
        });
    }
    public void buttonLogin_onClick(View view) {
//        String uname = username.getText().toString();
//        String pass = password.getText().toString();
        if (username.getText().toString().equals("admin") && password.getText().toString().equals("admin") ){
            Intent intent = new Intent(AdminLogin.this, AdminHome.class);
            startActivity(intent);
            Toast.makeText(AdminLogin.this, "Login Success.", Toast.LENGTH_SHORT).show();


        }else {

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


        }

    }
}