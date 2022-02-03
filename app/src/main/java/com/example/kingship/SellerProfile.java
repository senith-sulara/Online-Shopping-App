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
import com.example.kingship.entities.Account;

public class SellerProfile extends AppCompatActivity {

    EditText uname, mNo, emal;
    Button update;
    TextView typ;
    private Account account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_profile);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));


        uname = findViewById(R.id.proName);
        mNo = findViewById(R.id.proPhone);
        emal = findViewById(R.id.proEmail);
        typ = findViewById(R.id.textType);
        update = findViewById(R.id.btn_p_update);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnSave_onClick(view);

            }
        });

        loadData();
    }
    private void loadData(){
        Intent i = getIntent();
        account = (Account) i.getSerializableExtra("account");
        typ.setText(account.getType().toUpperCase());
        emal.setText(account.getEmail());
        mNo.setText(account.getMobile());
        uname.setText(account.getUsername());
    }

    public void btnSave_onClick(View view) {
        try {
            AccountDB accountDB = new AccountDB(getApplicationContext());
            Account currentAccount = accountDB.find(account.getId());
            String newUsername = uname.getText().toString();
            Account temp = accountDB.checkUsername(newUsername);
            if (!newUsername.equalsIgnoreCase(currentAccount.getUsername()) && temp != null){
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
                return;
            }

            currentAccount.setUsername(uname.getText().toString());
            currentAccount.setMobile(mNo.getText().toString());
            currentAccount.setEmail(emal.getText().toString());
            if(accountDB.update(currentAccount)){
                Intent intent = new Intent(SellerProfile.this, SellerHomelog.class);
                intent.putExtra("account", currentAccount);
                startActivity(intent);
                Toast.makeText(SellerProfile.this, "Successfully Updated.", Toast.LENGTH_SHORT).show();

            }else {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle(R.string.error);
                builder.setMessage(R.string.failed);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.show();

            }

        }catch (Exception e){
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

    }
}