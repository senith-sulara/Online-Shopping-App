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

import com.example.kingship.database.AccountDB;
import com.example.kingship.entities.Account;

import java.util.ArrayList;

public class CustomerRemoveAdapter extends RecyclerView.Adapter<CustomerRemoveAdapter.CustomerVH>{

    ArrayList<Account> Accounts;
    Context context;

    public  CustomerRemoveAdapter( ArrayList<Account> Accounts, Context context ){
        this.Accounts= Accounts;
        this.context =context;
    }


    @NonNull
    @Override
    public CustomerVH onCreateViewHolder( @NonNull ViewGroup parent, int viewType ) {

        LayoutInflater inflater= LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_customerremov,parent,false);
        CustomerVH pvh = new CustomerVH (view);
        return  pvh;


    }

    @Override
    public void onBindViewHolder( @NonNull CustomerVH holder, int position ) {

    final     Account a= Accounts.get(position);
        holder.cuname.setText(a.getUsername());

        holder.deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Confirmation  !!!");
                builder.setMessage("Are you sure to delete ?"+a.getUsername()+"?");
                builder.setIcon(android.R.drawable.ic_menu_delete);
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick( DialogInterface dialogInterface, int i ) {
                        AccountDB accountDB = new AccountDB(context);
                        int result = accountDB.deleteSeller(a.getUsername());

                        if(result>0){
                            Toast.makeText(context, "    Deleted",Toast.LENGTH_SHORT).show();
                            Accounts.remove(a);
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
        return Accounts.size();
    }

    class  CustomerVH extends RecyclerView.ViewHolder {

        TextView cuname;
        CardView deletebtn;
        public CustomerVH( @NonNull View v ) {
            super(v);

            cuname = v.findViewById(R.id.custname);
            deletebtn = v.findViewById(R.id.cardDelete);
        }
    }
}
