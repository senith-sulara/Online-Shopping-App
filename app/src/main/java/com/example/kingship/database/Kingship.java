package com.example.kingship.database;

import android.provider.BaseColumns;

public class Kingship {

    private Kingship() {}

    /* Inner class that defines the table contents */
    public static class Pro implements BaseColumns {
        public static final String TABLE_NAME = "productInfo";
        public static final String COLUMN_1= "category";
        public static final String COLUMN_2 = "brandname";
        public static final String COLUMN_3 = "productname";
        public static final String COLUMN_4 = "price";
        public static final String COLUMN_5 = "description";
        public static final String COLUMN_6 = "image";

    }
    public static class Cat implements BaseColumns {
        public static final String TABLE_NAME = "categoryinfo";
        public static final String COLUMN_1= "cate";

    }

}
