package com.example.kingship;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kingship.database.DBHandler;
import com.example.kingship.model.Category;

import java.util.ArrayList;

public class DisplayCategory extends AppCompatActivity {

    TextView tvtotal;
    RecyclerView recyclerView;
    CategoryAdapter categoryAdapter;
    ArrayList<Category> categories;
    Button delete;
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_category);

        recyclerView= findViewById(R.id.recyclerView);
        delete =findViewById(R.id.catbtndele);

//        tvtotal =findViewById(R.id.totalview);
        DBHandler dbHandler= new DBHandler(this);

        categories = dbHandler.getAllCategeris();

        categoryAdapter = new CategoryAdapter(categories,this);
        recyclerView.setAdapter(categoryAdapter);

        LinearLayoutManager llm= new LinearLayoutManager(this);
        llm.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(llm);

    }
}