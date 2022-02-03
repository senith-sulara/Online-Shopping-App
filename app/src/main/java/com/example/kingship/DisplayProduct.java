package com.example.kingship;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.kingship.database.DBHandler;
import com.example.kingship.model.Product;

import java.util.ArrayList;

public class DisplayProduct extends AppCompatActivity {

    TextView total;
    // EditText brdname;
    RecyclerView recyclerView;
    ProductAdapter productAdapter;
    ArrayList<Product> products;

    Button delete;
    Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_product);

        recyclerView = findViewById(R.id.recyclerView);
        delete =findViewById(R.id.cardDelete);

        //brdname =findViewById(R.id.Braname);
        // total =findViewById(R.id.totalview);

        DBHandler dbHandler= new DBHandler(this);
        products =dbHandler.getAllProduct();

        productAdapter = new ProductAdapter(products,this);
        recyclerView.setAdapter(productAdapter);

        LinearLayoutManager llms= new LinearLayoutManager(this);
        llms.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(llms);


    }
}