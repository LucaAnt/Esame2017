package com.trieste.mobile.esame2017ordini.model;

import android.content.ContentValues;
import android.database.Cursor;

import static android.provider.BaseColumns._ID;
import static com.trieste.mobile.esame2017ordini.database.tables.TableHelperMenuItems.NOME;
import static com.trieste.mobile.esame2017ordini.database.tables.TableHelperMenuItems.PREZZO;


public class MenuItem {
    public int _id,prezzo;
    public String nome;

    public MenuItem(int _id, int prezzo, String nome) {
        this._id = _id;
        this.prezzo = prezzo;
        this.nome = nome;
    }

    public MenuItem(int prezzo, String nome) {
        this.prezzo = prezzo;
        this.nome = nome;
    }

    public MenuItem(Cursor cursor) {
        _id = cursor.getInt(cursor.getColumnIndex(_ID));
        prezzo = cursor.getInt(cursor.getColumnIndex(PREZZO));
        nome = cursor.getString(cursor.getColumnIndex(NOME));
    }

    public ContentValues toContentValues()
    {
        ContentValues contentValues = new ContentValues();
        if ( _id!=0)
            contentValues.put(_ID,_id);
        contentValues.put(PREZZO,prezzo);
        contentValues.put(NOME,nome);

        return contentValues;
    }
}
