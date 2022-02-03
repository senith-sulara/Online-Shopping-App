package com.example.kingship;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kingship.database.AccountDB;
import com.example.kingship.database.DBHelper;
import com.example.kingship.entities.Account;

public class Signup extends AppCompatActivity {

    EditText username, mobile, email, password;
    Button register;
    RadioButton customer, seller;
    String type;
    CheckBox agree;
    TextView signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));


        username = findViewById(R.id.editTextPersonName);
        mobile = findViewById(R.id.editTextPhoneNumber);
        email = findViewById(R.id.editTextEmailSignin);
        password = findViewById(R.id.editTextPasswordSignin);
        customer = findViewById(R.id.radioButtoncustomer);
        register = findViewById(R.id.btnContinue);
        agree = findViewById(R.id.checkBoxagree);
        signin = findViewById(R.id.signuptxtsinin);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonSave_onClick(view);
            }
        });


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Signin.class);
                startActivity(i);
            }
        });

    }
    public void buttonSave_onClick(View view) {

        try {
            if (agree.isChecked()) {
            if (customer.isChecked()) {
                        type = "customer";
                    }else{
                        type="seller";
                    }
            AccountDB accountDB = new AccountDB(getApplicationContext());
            Account account = new Account();
            account.setType(type);
            account.setPassword(password.getText().toString());
            account.setEmail(email.getText().toString());
            account.setMobile(mobile.getText().toString());
            account.setUsername(username.getText().toString());
            Account temp = accountDB.checkUsername(username.getText().toString());

            if(temp == null) {
                if (accountDB.create(account)) {
                    Intent i = new Intent(Signup.this, Signin.class);
                    startActivity(i);
                    Toast.makeText(Signup.this, "Successfully Registered.", Toast.LENGTH_SHORT).show();


                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setTitle(R.string.error);
                    builder.setMessage(R.string.cannot);
                    builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    builder.show();
                }
            }else{
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle(R.string.error);
                builder.setMessage(R.string.username_exists);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.show();

            }
            }else{
                    Toast.makeText(Signup.this, "Please Agree to the services and privacy policy", Toast.LENGTH_SHORT).show();
                }

        }catch (Exception e){
            AlertDialog.Builder builder = new  AlertDialog.Builder(view.getContext());
            builder.setTitle(R.string.error);
            builder.setMessage(e.getMessage());
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