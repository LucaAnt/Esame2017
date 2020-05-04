package com.trieste.mobile.esame2017ordini.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.trieste.mobile.esame2017ordini.database.tables.*;
import com.trieste.mobile.esame2017ordini.model.MenuItem;

import java.util.LinkedList;
import java.util.List;


public class OrdiniDb extends SQLiteOpenHelper {

    public static final String DB_NAME = "ordini.db";
    public static final int VERSION = 1;

    public OrdiniDb(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TableHelperOrdini.CREATE);
        db.execSQL(TableHelperMenuItems.CREATE);
        db.execSQL(TableHelperOrdiniItems.CREATE);

        populateMenuItems(db);
    }

    private void populateMenuItems(SQLiteDatabase db)
    {
        List<MenuItem> menuItems = new LinkedList<>();
        menuItems.add(new MenuItem(8,"pizza"));
        menuItems.add(new MenuItem(6,"panino"));
        menuItems.add(new MenuItem(3,"bibita"));
        menuItems.add(new MenuItem(3,"gelato"));
        menuItems.add(new MenuItem(1,"caffe"));

        for (MenuItem item: menuItems)
            db.insert(TableHelperMenuItems.TABLE_NAME,null,item.toContentValues());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
