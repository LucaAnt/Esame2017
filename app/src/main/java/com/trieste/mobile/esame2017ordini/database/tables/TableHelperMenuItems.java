package com.trieste.mobile.esame2017ordini.database.tables;

import android.provider.BaseColumns;

public class TableHelperMenuItems implements BaseColumns {
    public static final String TABLE_NAME = "menu";
    public static final String NOME = "nome";
    public static final String PREZZO = "prezzo";


    public static final String CREATE = "CREATE TABLE " + TABLE_NAME + " ( " +
            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
            NOME + " TEXT , " +
            PREZZO + " INTEGER DEFAULT 0 " +
            " ) ;";
}
