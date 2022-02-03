package com.example.kingship;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kingship.database.DBHandler;
import com.example.kingship.model.Category;

import java.util.ArrayList;

public class CategoryAdapter extends  RecyclerView.Adapter<CategoryAdapter.CategoryVH>{

ArrayList<Category> categories;
Context context;

public CategoryAdapter(ArrayList<Category> categories, Context context){
    this.categories=categories;
    this.context=context;
}

    @NonNull
    @Override
    public CategoryVH onCreateViewHolder( @NonNull ViewGroup parent, int viewType ) {


        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_category,parent,false);
        CategoryVH svh = new CategoryVH(view);
        return svh;
    }

    @Override
    public void onBindViewHolder( @NonNull CategoryVH holder, int position ) {

  final   Category s= categories.get(position);
    holder.catname.setText(s.getCat());

    holder.catbtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick( View view ) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Confirmation  !!!");
            builder.setMessage("Are you sure to delete ?"+s.getCat()+"?");
            builder.setIcon(android.R.drawable.ic_menu_delete);
            builder.setCancelable(false);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick( DialogInterface dialogInterface, int i ) {
                    DBHandler dbHandler = new DBHandler(context);
                    int result = dbHandler.deletecateg(s.getCat());

                    if(result>0){
                        Toast.makeText(context, "   Deleted",Toast.LENGTH_SHORT).show();
                        categories.remove(s);
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
        return categories.size();
    }


    class  CategoryVH extends RecyclerView.ViewHolder{

        TextView catname;
        CardView catbtn;

        public CategoryVH( @NonNull View v ) {
            super(v);

            catname= v.findViewById(R.id.catnames);
            catbtn = v.findViewById(R.id.catbtndele);

        }
    }
}
