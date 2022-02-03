package com.example.kingship;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kingship.database.AccountDB;
import com.example.kingship.entities.Account;

import java.util.ArrayList;

public class RemoveSeller extends AppCompatActivity {


    TextView total;
    EditText brdname;
    RecyclerView recyclerView;
    SellerRemoveAdapter sellerRemoveAdapter;
    ArrayList<Account> Accounts;

    Button delete;
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_seller);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));


        recyclerView = findViewById(R.id.recyclerView2);
        delete =findViewById(R.id.cardDelete);


        AccountDB dbHandler = new AccountDB(this);

        Accounts =dbHandler.getAllSeler();

        sellerRemoveAdapter = new SellerRemoveAdapter(Accounts,this);
        recyclerView.setAdapter(sellerRemoveAdapter);

        LinearLayoutManager ilm = new LinearLayoutManager(this);
        ilm.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(ilm);

    }
}