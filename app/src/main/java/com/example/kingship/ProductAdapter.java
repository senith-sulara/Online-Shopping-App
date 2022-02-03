package com.example.kingship;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kingship.database.DBHandler;

import com.example.kingship.model.Product;

import java.util.ArrayList;

public class ProductAdapter extends  RecyclerView.Adapter<ProductAdapter.ProductVH> {

    ArrayList<Product> products;
    Context context;

    public ProductAdapter(ArrayList<Product> products, Context context){
        this.products =products;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater= LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_product,parent,false);
        ProductVH pvh = new ProductVH(view);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductVH holder, int position) {

        final  Product p =products.get(position);
        holder.categ.setText(p.getCat());;
        holder.BranName.setText(p.getBname());
        holder.produname.setText(p.getPname());
        holder.Price.setText(p.getPrice());
        holder.Descrip.setText(p.getDescrip());
        // holder.img.setImageBitmap(p.getImage());
//
        byte[] img =p.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(img,0,img.length);
        holder.img.setImageBitmap(bitmap);








//        holder.upbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Toast.makeText(context, p.getCat()+ p.getBname() +"  will be updated",Toast.LENGTH_SHORT).show();
//
//                Bundle data = new Bundle();
//                data.putInt("id",p.getId());
//                data.putString("category",p.getCat());
//                data.putString("brandname",p.getBname());
//                data.putString("productname",p.getPname());
//                data.putString("price",p.getPrice());
//                data.putString("description",p.getPname());
//                data.putString("avatar", String.valueOf(p.getImage()));
//
//                Intent intent= new Intent(context,updateproduct.class);
//                intent.putExtra("PRODUCT",p);
//                context.startActivity(intent);
//
//
//
//
//            }
//        });

        holder.delbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText(context, p.getCat()+ p.getBname() +"  will be Deleted",Toast.LENGTH_SHORT).show();

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Confirmation  !!!");
                builder.setMessage("Are you sure to delete ?"+p.getBname()+"?");
                builder.setIcon(android.R.drawable.ic_menu_delete);
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick( DialogInterface dialogInterface, int i ) {
                        DBHandler dbHandler = new DBHandler(context);
                        int result = dbHandler.deleteInfo(p.getBname());

                        if(result>0){
                            Toast.makeText(context, "    Deleted",Toast.LENGTH_SHORT).show();
                            products.remove(p);
                            notifyDataSetChanged();
                        }else {
                            Toast.makeText(context, "  Faild",Toast.LENGTH_SHORT).show();

                        }

                    }
                });
                builder.setNegativeButton("No",null);
                builder.show();


            }
        });
    }

    @Override
    public int getItemCount() {
        return products .size();
    }

    class ProductVH extends RecyclerView.ViewHolder {

        TextView produname,BranName,Descrip,Price,categ;
        CardView upbtn,delbtn;
        ImageView img;

        public ProductVH(@NonNull View v) {
            super(v);

            categ=v.findViewById(R.id.cate);
            BranName=v.findViewById(R.id.Braname);
            produname=v.findViewById(R.id.prod);
            Price=v.findViewById(R.id.price);
            Descrip=v.findViewById(R.id.descrip);
//            upbtn =v.findViewById(R.id.cardUpdate);
            img=v.findViewById(R.id.imageView3);
            delbtn =v.findViewById(R.id.cardDelete);
        }
    }
}
