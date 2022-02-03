package com.example.kingship.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.kingship.model.Category;
import com.example.kingship.model.Product;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    public  static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Database.db";


    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        db.execSQL(SQL_CREATE_ENTRIES_Cate);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        db.execSQL(SQL_DELETE_ENTRIES_Cate);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Kingship.Pro.TABLE_NAME + " (" +
                    Kingship.Pro._ID + " INTEGER PRIMARY KEY," +
                    Kingship.Pro.COLUMN_1 + " TEXT," +
                    Kingship.Pro.COLUMN_2 + " TEXT," +
                    Kingship.Pro.COLUMN_3 + " TEXT," +
                    Kingship.Pro.COLUMN_4 + " TEXT," +
                    Kingship.Pro.COLUMN_5 + " TEXT," +
                    Kingship.Pro.COLUMN_6+ " TEXT)";

    private static final String SQL_CREATE_ENTRIES_Cate =
            "CREATE TABLE " + Kingship.Cat.TABLE_NAME + " (" +
                    Kingship.Cat._ID + " INTEGER PRIMARY KEY," +
                    Kingship.Cat.COLUMN_1 + " TEXT)";



    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " +  Kingship.Pro.TABLE_NAME;

    private static final String SQL_DELETE_ENTRIES_Cate =
            "DROP TABLE IF EXISTS " +  Kingship.Pro.TABLE_NAME;

    public  long addInfo(String categ,String braname,String proname,String price,String desc,byte[] image){
        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put( Kingship.Pro.COLUMN_1, categ);
        values.put( Kingship.Pro.COLUMN_2, braname);
        values.put( Kingship.Pro.COLUMN_3, proname);
        values.put( Kingship.Pro.COLUMN_4, price);
        values.put( Kingship.Pro.COLUMN_5, desc);
        values.put( Kingship.Pro.COLUMN_6, image);

// Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert( Kingship.Pro.TABLE_NAME, null, values);

        return newRowId;
    }

    public ArrayList<Product> getAllProduct(){
        ArrayList<Product> products =new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor= db.rawQuery("SELECT * from  productInfo", null);

        if(cursor.moveToFirst()){
            do{

                int id = cursor.getInt(0);
                String cat =cursor.getString(1);
                String Bname= cursor.getString(2);
                String pname= cursor.getString(3);
                String price= cursor.getString(4);
                String descrip =cursor.getString(5);
                byte[] image =cursor.getBlob(6);

                Product p = new Product(id,cat,Bname,pname,price,descrip,image);
                products .add(p);
            }while (cursor.moveToNext());
        }

        return  products;
    }

    public int deleteInfo( String braname){


        SQLiteDatabase db = getWritableDatabase();

        // Define 'where' part of query.
        String selection = Kingship.Pro.COLUMN_2 + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { braname};
        // Issue SQL statement.
        int deletedRows = db.delete(Kingship.Pro.TABLE_NAME, selection, selectionArgs);

        return deletedRows;
    }

    public int updateProduct(Product p) {
        SQLiteDatabase db= getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("category",p.getCat());
        values.put("brandname",p.getBname() );
        values.put("productname",p.getPname());
        values.put( "price",p.getPrice());
        values.put("description", p.getDescrip());
        values.put("avatar", p.getImage());

      db.update("productInfo",values,"id=?",new String[]{String.valueOf(p.getId())});
      return 0;
    }

    public int deleteProduct( int id ) {

        SQLiteDatabase db = getWritableDatabase();
       return db.delete("productInfo","id=?",new String[] {String.valueOf(id)});



    }

    public  long addcate(String categ){
        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put( Kingship.Cat.COLUMN_1, categ);


// Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert( Kingship.Cat.TABLE_NAME, null, values);

        return newRowId;
    }


    public ArrayList<Category> getAllCategeris() {
        ArrayList<Category> categories =new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor= db.rawQuery("SELECT * from  categoryinfo", null);

        if(cursor.moveToFirst()){
            do{

                int id = cursor.getInt(0);
                String cat =cursor.getString(1);


                Category p = new Category(id,cat);
                categories .add(p);
            }while (cursor.moveToNext());
        }

        return  categories;

    }

    public int deletecateg( String cat ) {

        SQLiteDatabase db = getWritableDatabase();

        // Define 'where' part of query.
        String selection = Kingship.Cat.COLUMN_1 + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { cat};
        // Issue SQL statement.
        int deletedRows = db.delete(Kingship.Cat.TABLE_NAME, selection, selectionArgs);

        return deletedRows;

    }
}
