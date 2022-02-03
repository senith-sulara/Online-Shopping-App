package com.example.kingship;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kingship.database.DBHandler;
import com.example.kingship.model.Product;

public class updateproduct extends AppCompatActivity {

    EditText pnames,bname,des,prices;
    Button upd;
    Spinner sp1;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateproduct);

        Product p= (Product) getIntent().getExtras().getSerializable("PRODUCT");


        id = p.getId();
        sp1= (Spinner) findViewById(R.id.spin);
        bname=(EditText)findViewById(R.id.bname);
        pnames=(EditText)findViewById(R.id.pname);
        prices=(EditText)findViewById(R.id.prices);
        des=(EditText)findViewById(R.id.desci);
        upd =(Button)findViewById(R.id.btnupdate);

        
        sp1.setDropDownVerticalOffset(Integer.parseInt(p.getCat()));
        bname.setText(p.getBname());
        pnames.setText(p.getPname());
        prices.setText(p.getPrice());
        des.setText(p.getDescrip());
    }



    public void update(View view){


        String cat = sp1.getSelectedItem().toString().trim();
        String Bname=bname.getText().toString().trim();
        String pname=pnames.getText().toString().trim();
        String price=prices.getText().toString().trim();
        String descrip=des.getText().toString().trim();
        

        Product p = new Product(id,cat,Bname,pname,price,descrip);

        DBHandler dbHandler = new DBHandler(this);
       int result = dbHandler.updateProduct(p);

       if(result>0)
       {
           Toast.makeText(this,"Updated"+result,Toast.LENGTH_SHORT).show();
           finish();
       }else{
           Toast.makeText(this,"Failed"+result,Toast.LENGTH_SHORT).show();
       }



    }


}