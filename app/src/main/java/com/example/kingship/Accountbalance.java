package com.example.kingship;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Accountbalance extends AppCompatActivity {

    EditText et1,et2;
    TextView et3;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountbalance);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));

        et1=findViewById(R.id.textinco);
         et2=findViewById(R.id.amountedit);
         et3=findViewById(R.id.textView4);
    }

    public void profitfen( View v ){


        int n1= Integer.parseInt(et1.getText().toString());
        int n2= Integer.parseInt(et2.getText().toString());
        int result= n1-n2;

       et3.setText(String.valueOf(result));
    }

}