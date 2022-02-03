package com.example.kingship.database;

import android.provider.BaseColumns;

public class UserProfile {
    private UserProfile(){}

    public static class User implements BaseColumns {
        public static final String TABLE_NAME = "UserInfo";
        public static final String COLUMN_1 = "username";
        public static final String COLUMN_2 = "mobileNo";
        public static final String COLUMN_3 = "email";
        public static final String COLUMN_4 = "password";
        public static final String COLUMN_5 = "Type";
    }
}
