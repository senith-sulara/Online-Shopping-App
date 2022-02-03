package com.example.kingship.database;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public class FragranceContract {
    private FragranceContract() {}

    public static final String CONTENT_AUTHORITY = "com.example.kingship";

    /**
     * Use CONTENT_AUTHORITY to create the base of all URI's which apps will use to contact
     * the content provider.
     */
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);


    public static final String PATH_FRAGRANCE = "fragrance-path";

    public static final String PATH_CART= "cart-path";


    public static final class FragranceEntry implements BaseColumns {

        /** The content URI to access the fragrance data in the provider */
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_FRAGRANCE);

        public static final Uri CONTENT_URI_CART = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_CART);

        /**
         * The MIME type of the {@link #CONTENT_URI} for a list of fragrance.
         */
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FRAGRANCE;

        /**
         * The MIME type of the {@link #CONTENT_URI} for a single fragrance.
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FRAGRANCE;

        /** Name of database table for fragrance */
        public final static String TABLE_NAME = "fragrances";

        //cart table name
        public final static String CART_TABLE = "cart";

        /**
         * Unique ID number for the fragrance (only for use in the database table).
         *
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        public final static String _CARTID = BaseColumns._ID;

        /**
         * Name of the fragrance.
         *
         * Type: TEXT
         */
        public final static String COLUMN_NAME = "fragrancename";
        public final static String COLUMN_DESCRIPTION = "description";
        public final static String COLUMN_IMAGE = "imageurl";
        public final static String COLUMN_PRICE = "price";
        public final static String COLUMN_USERRATING = "userrating";


        public final static String COLUMN_CART_NAME = "cartfragrancename";
        public final static String COLUMN_CART_IMAGE = "cartimageurl";
        public final static String COLUMN_CART_QUANTITY = "cartquantity";
        public final static String COLUMN_CART_TOTAL_PRICE = "carttotalprice";

    }
}
