package com.trieste.mobile.esame2017ordini.model;

import android.content.ContentValues;
import android.database.Cursor;
import static com.trieste.mobile.esame2017ordini.database.tables.TableHelperOrdini.*;

public class Ordine {
    String data;
//    int _id,pizza,panino,bibita,caffe,gelato;

    public Ordine() {
        super();
    }

//    public Ordine(Cursor cursor) {
//        _id = cursor.getInt(cursor.getColumnIndex(_ID));
//        data = cursor.getString(cursor.getColumnIndex(DATA));
//        pizza = cursor.getInt(cursor.getColumnIndex(PIZZE));
//        panino = cursor.getInt(cursor.getColumnIndex(PANINI));
//        bibita = cursor.getInt(cursor.getColumnIndex(BIBITE));
//        caffe = cursor.getInt(cursor.getColumnIndex(CAFFE));
//        gelato = cursor.getInt(cursor.getColumnIndex(GELATI));
//    }
//
//    public int getBill()
//    {
//        return (pizza + 8) + (panino * 6) + (bibita * 3) + (gelato * 3) + caffe;
//    }
//
//    public int getTotal()
//    {
//        return pizza + panino + bibita  + caffe + gelato;
//    }
//
//    public ContentValues toContentValues()
//    {
//        ContentValues contentValues = new ContentValues();
//        if ( _id>-1)
//            contentValues.put(_ID,_id);
//        contentValues.put(DATA,data);
//        contentValues.put(PIZZE,pizza);
//        contentValues.put(PANINI,panino);
//        contentValues.put(BIBITE,bibita);
//        contentValues.put(CAFFE,caffe);
//        contentValues.put(GELATI,gelato);
//        return contentValues;
//    }
}
