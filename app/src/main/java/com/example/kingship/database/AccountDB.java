package com.example.kingship.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.example.kingship.entities.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountDB extends SQLiteOpenHelper {

    private static  String dbName= "accountDB";
    private static  String tableName= "account";
    private static  String idColumn= "id";
    private static  String userNameColumn= "username";
    private static  String mobileColumn= "mobile";
    private static  String emailColumn= "email";
    private static  String passwordColumn= "password";
    private static  String typeColumn= "type";


    public AccountDB(Context context){
        super(context, dbName, null,1);

    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table " + tableName + "(" +
                idColumn + " integer primary key autoincrement, " +
                userNameColumn + " text," +
                mobileColumn + " text," +
                emailColumn + " text," +
                passwordColumn + " text," +
                typeColumn + " text" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        onCreate(sqLiteDatabase);
    }

    public boolean create(Account account){
        boolean result = true;
        try{
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(userNameColumn, account.getUsername());
            contentValues.put(mobileColumn, account.getMobile());
            contentValues.put(emailColumn, account.getEmail());
            contentValues.put(passwordColumn, account.getPassword());
            contentValues.put(typeColumn, account.getType());
            result = sqLiteDatabase.insert(tableName, null, contentValues) > 0;

        }catch (Exception e){
            result = false;
        }
        return result;
    }

    public boolean update(Account account){
        boolean result = true;
        try{
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(userNameColumn, account.getUsername());
            contentValues.put(mobileColumn, account.getMobile());
            contentValues.put(emailColumn, account.getEmail());
            contentValues.put(passwordColumn, account.getPassword());
            contentValues.put(typeColumn, account.getType());
            result = sqLiteDatabase.update(tableName, contentValues, idColumn + " = ?", new String[] { String.valueOf(account.getId())}) > 0;

        }catch (Exception e){
            result = false;
        }
        return result;
    }

    public Account login(String username, String password){
        Account account = null;
        try {
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from " + tableName + " where username = ? and password = ?", new String[]{username, password});
            if (cursor.moveToFirst()){
                account = new Account();
                account.setId(cursor.getInt(0));
                account.setUsername(cursor.getString(1));
                account.setMobile(cursor.getString(2));
                account.setEmail(cursor.getString(3));
                account.setPassword(cursor.getString(4));
                account.setType(cursor.getString(5));

            }
        }catch (Exception e){
            account = null;
        }
        return account;
    }

    public Account checkUsername(String username){
        Account account = null;
        try {
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from " + tableName + " where username = ?", new String[]{username});
            if (cursor.moveToFirst()){
                account = new Account();
                account.setId(cursor.getInt(0));
                account.setUsername(cursor.getString(1));
                account.setMobile(cursor.getString(2));
                account.setEmail(cursor.getString(3));
                account.setPassword(cursor.getString(4));
                account.setType(cursor.getString(5));

            }
        }catch (Exception e){
            account = null;
        }
        return account;
    }

    public Account type(String username){
        Account account = null;
        try {
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from " + tableName + " where username = ?", new String[]{username});
            if (cursor.moveToFirst()){
                account = new Account();
                account.setId(cursor.getInt(0));
                account.setUsername(cursor.getString(1));
                account.setMobile(cursor.getString(2));
                account.setEmail(cursor.getString(3));
                account.setPassword(cursor.getString(4));
                account.setType(cursor.getString(5));

            }
        }catch (Exception e){
            account = null;
        }
        return account;
    }

    public Account find(int id){
        Account account = null;
        try {
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from " + tableName + " where id = ?", new String[]{String.valueOf(id)});
            if (cursor.moveToFirst()){
                account = new Account();
                account.setId(cursor.getInt(0));
                account.setUsername(cursor.getString(1));
                account.setMobile(cursor.getString(2));
                account.setEmail(cursor.getString(3));
                account.setPassword(cursor.getString(4));
                account.setType(cursor.getString(5));

            }
        }catch (Exception e){
            account = null;
        }
        return account;
    }

    public ArrayList<Account> getAllCustomer() {
        ArrayList<Account> Accounts =new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor= db.rawQuery("SELECT * from account ", null);

        if(cursor.moveToFirst()){
            do{

                int id = cursor.getInt(0);
                String username =cursor.getString(1);


                Account a = new Account(id,username);
                Accounts.add(a);
            }while (cursor.moveToNext());
        }

        return  Accounts;
    }

    public int deleteSCustomer( String username ) {


        SQLiteDatabase db = getWritableDatabase();

        // Define 'where' part of query.
        String selection = AccountDB.tableName + userNameColumn + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { username};
        // Issue SQL statement.
        int deletedRows = db.delete(AccountDB.tableName, selection, selectionArgs);

        return deletedRows;
    }

    public ArrayList<Account> getAllSeler() {
        ArrayList<Account> Accounts =new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor= db.rawQuery("SELECT * from account ", null);

        if(cursor.moveToFirst()){
            do{

                int id = cursor.getInt(0);
                String username =cursor.getString(1);


                Account a = new Account(id,username);
                Accounts.add(a);
            }while (cursor.moveToNext());
        }

        return  Accounts;
    }
    public int deleteSeller( String username ) {


        SQLiteDatabase db = getWritableDatabase();

        // Define 'where' part of query.
        String selection = AccountDB.userNameColumn + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { username};
        // Issue SQL statement.
        int deletedRows = db.delete(AccountDB.tableName, selection, selectionArgs);

        return deletedRows;
    }


}
