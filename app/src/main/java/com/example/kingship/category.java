package com.example.kingship;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kingship.database.DBHandler;

public class category extends AppCompatActivity {


    EditText cattext;
    Button done, btn;
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        cattext=(EditText)findViewById(R.id.textcat);
        done =(Button)findViewById(R.id.btncat);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {

                DBHandler dbHandler = new DBHandler(getApplicationContext());
                long newID=   dbHandler.addcate(cattext.getText().toString());
                Toast.makeText(category.this, " Added to category !"+newID, Toast.LENGTH_SHORT).show();

                Intent i =new Intent(getApplicationContext(), DisplayCategory.class);
                startActivity(i);

                cattext.setText(null);

            }
        });

    }
}