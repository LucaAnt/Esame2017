package com.trieste.mobile.esame2017ordini.ui.elenco_ordini;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.trieste.mobile.esame2017ordini.R;
import com.trieste.mobile.esame2017ordini.database.OrdiniDb;
import com.trieste.mobile.esame2017ordini.database.tables.TableHelperOrdini;
import com.trieste.mobile.esame2017ordini.database.tables.TableHelperOrdiniItems;
import com.trieste.mobile.esame2017ordini.ui.dettaglio_modifica_ordine.ActivityModificaOrdineDettaglio;

public class ActivityElencoOrdini extends AppCompatActivity {

    SQLiteDatabase db;
    ListView riepilogoOrdiniListView;
    AdapterElencoOrdini riepilogoOrdiniAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica_ordine);
        riepilogoOrdiniListView = findViewById(R.id.Activity_RiepilogoOrdini_ListView);
        riepilogoOrdiniListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                db = (new OrdiniDb(ActivityElencoOrdini.this)).getWritableDatabase();

                db.delete(TableHelperOrdini.TABLE_NAME,TableHelperOrdini._ID+"="+id,null);
                db.delete(TableHelperOrdiniItems.TABLE_NAME,TableHelperOrdiniItems.ORDINE_ID+"="+id,null);

                db.close();
                loadOrders();
                return true;
            }
        });

        riepilogoOrdiniListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(ActivityElencoOrdini.this, ActivityModificaOrdineDettaglio.class).putExtra(ActivityModificaOrdineDettaglio.ACTIVITY_EXTRA_ID,id));
            }
        });
        loadOrders();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadOrders();
    }

    public void loadOrders()
    {
        db = (new OrdiniDb(this)).getReadableDatabase();
        Cursor c = db.rawQuery(TableHelperOrdini.QUERY_ORDERS,null);


        /* test query
        Log.d("SIZE", c.getCount()+"");
        while (c.moveToNext())
            Log.d("ITEM:", "_ID" + c.getInt(c.getColumnIndex(TableHelperOrdini._ID))
                    + " - IMPORTO:" +c.getInt(c.getColumnIndex(TableHelperOrdini.CALCULATED_IMPORT_FIELD))
                    + " - QTY:" +c.getInt(c.getColumnIndex(TableHelperOrdini.CALCULATED_QTY_FIELD))
                    + " - DATA:" +c.getString(c.getColumnIndex(TableHelperOrdini.DATA)));
        c.moveToFirst();
        */

        if (riepilogoOrdiniAdapter==null)
        {
            riepilogoOrdiniAdapter = new AdapterElencoOrdini(this,c);
            riepilogoOrdiniListView.setAdapter(riepilogoOrdiniAdapter);
        }
        else
            riepilogoOrdiniAdapter.changeCursor(c);


        riepilogoOrdiniAdapter.notifyDataSetChanged();

        //c.close();
        db.close();
    }

    public void onBackButtonPressed(View v)
    {
        finish();
    }
}
