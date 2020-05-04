package com.trieste.mobile.esame2017ordini.database.tables;

import android.provider.BaseColumns;

public class TableHelperOrdini implements BaseColumns {

    public static final String TABLE_NAME = "ordini";
    public static final String DATA = "data";


    public static final String CREATE = "CREATE TABLE " + TABLE_NAME + " ( " +
            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
            DATA + " TEXT " +
            " ) ;";


    //PROJECTION ORDERS UTILS
    private static final String SINGLE_ITEM_PRICE = "(SELECT " + TableHelperMenuItems.PREZZO  + " FROM " + TableHelperMenuItems.TABLE_NAME + " WHERE " + TableHelperMenuItems.TABLE_NAME + "."+ TableHelperMenuItems._ID + " = " + TableHelperOrdiniItems.ITEM_ID + ")";

    public static final String CALCULATED_IMPORT_FIELD = "importo_totale";
    public static final String CALCULATED_QTY_FIELD = "quantita_totale";

    public static final String QUERY_ORDERS = "SELECT " + TABLE_NAME +"."+_ID + " , " + DATA + " , " + "SUM(" + TableHelperOrdiniItems.QUANTITA + " * " + SINGLE_ITEM_PRICE + ") as "+ CALCULATED_IMPORT_FIELD + ", SUM(" + TableHelperOrdiniItems.QUANTITA + ") as " + CALCULATED_QTY_FIELD + " " +
                                                " FROM " + TABLE_NAME +
                                                " LEFT OUTER JOIN "+  TableHelperOrdiniItems.TABLE_NAME +
                                                " on " + TABLE_NAME +"." +_ID + " = " + TableHelperOrdiniItems.TABLE_NAME + "." + TableHelperOrdiniItems.ORDINE_ID +
                                                " GROUP BY " + TABLE_NAME +"."+_ID +  " ;";


    //Orders Count
    public static final String ORDER_COUNT = "totale_ordini";
    public static final String TOTAL_ORDERS = "SELECT COUNT(*) as "+ORDER_COUNT+" FROM " + TABLE_NAME +";";


}
