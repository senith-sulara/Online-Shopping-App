package com.example.kingship;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kingship.database.AccountDB;
import com.example.kingship.entities.Account;

import java.util.ArrayList;

public class RemoveCustomer extends AppCompatActivity {

    TextView tvtotal;
    RecyclerView recyclerView;
    CustomerRemoveAdapter customerRemoveAdapter;
    ArrayList<Account> Accounts;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_customer);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));


        recyclerView =findViewById(R.id.recyclerView1);

        AccountDB dbHandler = new AccountDB(this);

        Accounts =dbHandler.getAllCustomer();

        customerRemoveAdapter = new CustomerRemoveAdapter(Accounts,this);
        recyclerView.setAdapter(customerRemoveAdapter);

        LinearLayoutManager ilm = new LinearLayoutManager(this);
        ilm.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(ilm);

    }
}