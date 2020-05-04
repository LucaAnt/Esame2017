package com.trieste.mobile.esame2017ordini.database.tables;

import android.provider.BaseColumns;

public class TableHelperOrdiniItems implements BaseColumns {

    public static final String TABLE_NAME = "menuitems";
    public static final String ORDINE_ID = "ordine_id";
    public static final String ITEM_ID = "item_id";
    public static final String QUANTITA = "quantita";


    public static final String CREATE = "CREATE TABLE " + TABLE_NAME + " ( " +
            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
            ORDINE_ID + " INTEGER DEFAULT 0 ," +
            ITEM_ID + " INTEGER DEFAULT 0 ," +
            QUANTITA + " INTEGER DEFAULT 0 " +
            " ) ;";

    //Projection OrderDetailsMod
    public static final String QUERY_SINGLE_ORDER_ITEMS = "SELECT " + TABLE_NAME +"." + _ID + "," + QUANTITA + " , " + TableHelperMenuItems.NOME+
            " FROM " + TABLE_NAME +
            " INNER JOIN "+  TableHelperMenuItems.TABLE_NAME +
            " on " + ITEM_ID + " = " + TableHelperMenuItems.TABLE_NAME + "." + TableHelperMenuItems._ID +
            " WHERE " +TABLE_NAME + "." +ORDINE_ID +"=?" + " ;";

    //Projection Order Total
    public static final String QUERY_SINGLE_ORDER_TOTAL_FIELD = "totale_ordine";
    public static final String QUERY_SINGLE_ORDER_TOTAL = "SELECT SUM("+ QUANTITA + "*" + TableHelperMenuItems.PREZZO + ") as "+ QUERY_SINGLE_ORDER_TOTAL_FIELD +
            " FROM " + TABLE_NAME +
            " INNER JOIN "+  TableHelperMenuItems.TABLE_NAME +
            " on " + ITEM_ID + " = " + TableHelperMenuItems.TABLE_NAME + "." + TableHelperMenuItems._ID +
            " WHERE " +TABLE_NAME + "." +ORDINE_ID +"=?" +
            " GROUP BY " + ORDINE_ID+
            " ;";


}
