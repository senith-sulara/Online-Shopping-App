package com.example.kingship;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.kingship.database.DBHandler;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ProductInsert extends AppCompatActivity {

    EditText pname;
    EditText bname;
    EditText des;
    EditText price;
    Button btnchoose;
    Button done;
    ImageView imageView;
    Spinner sp1;

    final int REQUEST_CODE_GALLERY=999;


    AwesomeValidation awesomeValidation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_insert);
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        sp1=(Spinner) findViewById(R.id.catspinner);
        bname=(EditText)findViewById(R.id.Bname);
        pname=(EditText)findViewById(R.id.Pname);
        price=(EditText)findViewById(R.id.Price);
        des=(EditText)findViewById(R.id.desc);
        done =(Button)findViewById(R.id.btndone);
        btnchoose =(Button)findViewById(R.id.btnChoose);
        imageView=(ImageView)findViewById(R.id.imageButton);

        awesomeValidation.addValidation(ProductInsert.this, R.id.Bname, RegexTemplate.NOT_EMPTY, R.string.err_Bname);
        awesomeValidation.addValidation(ProductInsert.this, R.id.Pname, RegexTemplate.NOT_EMPTY, R.string.err_pname);
        awesomeValidation.addValidation(ProductInsert.this, R.id.Price, RegexTemplate.NOT_EMPTY, R.string.err_price);
        awesomeValidation.addValidation(ProductInsert.this, R.id.desc, RegexTemplate.NOT_EMPTY, R.string.err_des);

        btnchoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {



                ActivityCompat.requestPermissions(

                        ProductInsert.this,
                        new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY

                );

            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(awesomeValidation.validate()) {
                    DBHandler dbHandler = new DBHandler(getApplicationContext());
                    long newID = dbHandler.addInfo(sp1.getSelectedItem().toString(), bname.getText().toString(), pname.getText().toString(), price.getText().toString(), des.getText().toString(), imageViewByte(imageView));
                    Toast.makeText(ProductInsert.this, "Product Added,user Id:" + newID, Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(getApplicationContext(), DisplayProduct.class);
                    startActivity(i);

                    bname.setText(null);
                    pname.setText(null);
                    price.setText(null);
                    des.setText(null);
                }   else{
                    Toast.makeText(getApplicationContext(),"validation Failed",Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
    public void onRequestPermissionsResult(int requestcode, @NonNull String[] permissins, @NonNull int[] grantResults) {
        if (requestcode ==REQUEST_CODE_GALLERY){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent= new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE_GALLERY);
            }
            else{
                Toast.makeText(getApplicationContext(), "Product Added,user Id:", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestcode,permissins,grantResults);
    }

    @Override
    protected void onActivityResult( int requestCode, int resultCode, @Nullable Intent data ) {

        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri =data.getData();

            try{
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);

            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private byte[] imageViewByte( ImageView image) {

        Bitmap bitmap=((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;

    }

}