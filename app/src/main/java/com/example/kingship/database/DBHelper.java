package com.example.kingship.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;


import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "UserInfo.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + UserProfile.User.TABLE_NAME + "(" +
                    UserProfile.User._ID + " INTEGER PRIMARY KEY," +
                    UserProfile.User.COLUMN_1 + " TEXT," +
                    UserProfile.User.COLUMN_2 + " TEXT," +
                    UserProfile.User.COLUMN_3 + " TEXT," +
                    UserProfile.User.COLUMN_4 + " TEXT," +
                    UserProfile.User.COLUMN_5 + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + UserProfile.User.TABLE_NAME;

    public long addUser(String username,String mobileNo,String email,String password, String type){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UserProfile.User.COLUMN_1,username);
        values.put(UserProfile.User.COLUMN_2,mobileNo);
        values.put(UserProfile.User.COLUMN_3,email);
        values.put(UserProfile.User.COLUMN_4,password);
        values.put(UserProfile.User.COLUMN_5,type);

        long newRowID = db.insert(UserProfile.User.TABLE_NAME,null,values);

        return newRowID;
    }
//////////////

    public Boolean loginDetails(String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();

        // array of columns to fetch
        String[] columns = {
                UserProfile.User._ID
        };
        // selection criteria
        String selection = UserProfile.User.COLUMN_3 + " = ?" + " AND " + UserProfile.User.COLUMN_4 + " = ?";
        // selection arguments
        String[] selectionArgs = {username, password};
        // query user table with conditions

        Cursor cursor = db.query(UserProfile.User.TABLE_NAME, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    public List userType(String type){
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                BaseColumns._ID,
                UserProfile.User.COLUMN_1,
                UserProfile.User.COLUMN_2,
                UserProfile.User.COLUMN_3,
                UserProfile.User.COLUMN_4,
                UserProfile.User.COLUMN_5,
        };
        // selection criteria
        String selection = UserProfile.User.COLUMN_3 + " Like ?";
        // selection arguments
        String[] selectionArgs = {type};
        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        String sortOrder = UserProfile.User.COLUMN_1 + " ASC";

        Cursor cursor = db.query(UserProfile.User.TABLE_NAME, //Table to query
                projection,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                sortOrder);                      //The sort order

        List userInfo = new ArrayList<>();
        while (cursor.moveToNext()){
            String user = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.User.COLUMN_1));
            String mobile = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.User.COLUMN_2));
            String eml = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.User.COLUMN_3));
            String password = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.User.COLUMN_4));
            String ty = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.User.COLUMN_5));

            userInfo.add(user);
            userInfo.add(mobile);
            userInfo.add(eml);
            userInfo.add(password);
            userInfo.add(ty);

        }
        cursor.close();
        return userInfo;
    }

    public List readInfor(String username){
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                BaseColumns._ID,
                UserProfile.User.COLUMN_1,
                UserProfile.User.COLUMN_2,
                UserProfile.User.COLUMN_3,
                UserProfile.User.COLUMN_4
        };
        String selection = UserProfile.User.COLUMN_3 + " LIKE ?";
        String[] selectionArgs = { username};

        String sortOrder =
                UserProfile.User.COLUMN_1 + " ASC";

        Cursor cursor = db.query(
                UserProfile.User.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );
        List userInfo  = new ArrayList<>();
        while(cursor.moveToNext()) {
            String user = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.User.COLUMN_1));
            String mob = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.User.COLUMN_2));
            String emal = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.User.COLUMN_3));
            String pass = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.User.COLUMN_4));
            String typ = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.User.COLUMN_5));
            userInfo.add(user);//0
            userInfo.add(mob);//1
            userInfo.add(emal);//1
            userInfo.add(pass);//2
            userInfo.add(typ);//3

        }
        cursor.close();
        return userInfo;
    }

    public void deleteInfo(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        // Define 'where' part of query.
        String selection = UserProfile.User.COLUMN_3 + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { username};
        // Issue SQL statement.
        int deletedRows = db.delete(UserProfile.User.TABLE_NAME, selection, selectionArgs);

    }
    /////////////

//    public Boolean updateInfor(String username,String mobileNo,String email){
//        SQLiteDatabase db = getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(UserProfile.User.COLUMN_1,username);
//        values.put(UserProfile.User.COLUMN_2,mobileNo);
//        values.put(UserProfile.User.COLUMN_3,email);
//
//
//        String selection = UserProfile.User._ID + " LIKE ?";
//        String[] selectionArgs = {username};
//
//        int count = db.update(
//                UserProfile.User.TABLE_NAME,
//                values,
//                selection,
//                selectionArgs
//        );
//
//        if(count>=1){
//            return true;
//        }else{
//            return false;
//        }
//    }

//    public List readAllInfor(String username){
//        SQLiteDatabase db = getReadableDatabase();
//
//        String[] projection = {
//                BaseColumns._ID,
//                UserProfile.User.COLUMN_1,
//                UserProfile.User.COLUMN_2,
//                UserProfile.User.COLUMN_3,
//                UserProfile.User.COLUMN_4,
//        };
//
//        String selection = UserProfile.User.COLUMN_1 + " LIKE ?";
//        String[] selectionArgs = {username};
//
//        String sortOrder = UserProfile.User.COLUMN_1 + " ASC";
//
//        Cursor cursor = db.query(
//                UserProfile.User.TABLE_NAME,
//                projection,
//                selection,
//                selectionArgs,
//                null,
//                null,
//                sortOrder
//        );
//
//        List userInfor = new ArrayList<>();
//        while (cursor.moveToNext()){
//            String user = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.User.COLUMN_1));
//            String mobile = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.User.COLUMN_2));
//            String password = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.User.COLUMN_3));
//            String gen = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.User.COLUMN_4));
//            userInfor.add(user);
//            userInfor.add(mobile);
//            userInfor.add(password);
//            userInfor.add(gen);
//
//        }
//        cursor.close();
//        return userInfor;
//
//    }

//    public void deleteInfo(String username){
//        SQLiteDatabase db = getWritableDatabase();
//
//        String selection = UserProfile.User.COLUMN_1 + " LIKE ?";
//        String[] selectionArgs = { username};
//        int deleteRows = db.delete(UserProfile.User.TABLE_NAME,selection,selectionArgs);
//    }
}
