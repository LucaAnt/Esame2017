package com.trieste.mobile.esame2017ordini.ui.dettaglio_modifica_ordine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.trieste.mobile.esame2017ordini.R;
import com.trieste.mobile.esame2017ordini.database.OrdiniDb;
import com.trieste.mobile.esame2017ordini.database.tables.TableHelperMenuItems;
import com.trieste.mobile.esame2017ordini.database.tables.TableHelperOrdini;
import com.trieste.mobile.esame2017ordini.database.tables.TableHelperOrdiniItems;

public class ActivityModificaOrdineDettaglio extends AppCompatActivity {
    public static final String ACTIVITY_EXTRA_ID = "ACTIVITY_EXTRA_ID";
    private long currentOrderId;
    private TextView totaleTextView;
    private ListView listDettaglio;
    private AdapterModificaOrdineDettaglio adapterModificaOrdineDettaglio;
    Cursor c;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica_ordine_dettaglio);
        currentOrderId = getIntent().getLongExtra(ACTIVITY_EXTRA_ID,0);
        listDettaglio = findViewById(R.id.activityModificaOrdineDettaglioListView);
        totaleTextView = findViewById(R.id.activityModificaOrdineDettaglioTotaleTextView);
        reloadData();
        listDettaglio.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                db = (new OrdiniDb(ActivityModificaOrdineDettaglio.this)).getWritableDatabase();
                c.moveToPosition(position);
                ContentValues contentValues = new ContentValues();
                contentValues.put(TableHelperOrdiniItems.QUANTITA,c.getInt(c.getColumnIndex(TableHelperOrdiniItems.QUANTITA))+1);

                db.update(TableHelperOrdiniItems.TABLE_NAME,contentValues,TableHelperOrdiniItems._ID+"=?",new String[]{id+""});
                db.close();
                reloadData();
            }
        });

        listDettaglio.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                db = (new OrdiniDb(ActivityModificaOrdineDettaglio.this)).getWritableDatabase();
                db.delete(TableHelperOrdiniItems.TABLE_NAME,TableHelperOrdiniItems._ID+"=?",new String[]{id+""});
                db.close();
                reloadData();
                return true;
            }
        });

    }

    private void reloadData()
    {
        db = (new OrdiniDb(this)).getReadableDatabase();

        c = db.rawQuery(TableHelperOrdiniItems.QUERY_SINGLE_ORDER_ITEMS,new String[] {currentOrderId+""});
        if (adapterModificaOrdineDettaglio == null)
        {
            adapterModificaOrdineDettaglio = new AdapterModificaOrdineDettaglio(this,c);
            listDettaglio.setAdapter(adapterModificaOrdineDettaglio);
        }
        else
            adapterModificaOrdineDettaglio.swapCursor(c);
        adapterModificaOrdineDettaglio.notifyDataSetChanged();

        Cursor totalCursor = db.rawQuery(TableHelperOrdiniItems.QUERY_SINGLE_ORDER_TOTAL,new String[] {currentOrderId+""});
        if (totalCursor.getCount()>0)
        {
            totalCursor.moveToFirst();
            totaleTextView.setText("Totale ordine : " + totalCursor.getInt(totalCursor.getColumnIndex(TableHelperOrdiniItems.QUERY_SINGLE_ORDER_TOTAL_FIELD))+" €");
        }else
        {
            totaleTextView.setText("Totale ordine : 0 €");
        }
          totalCursor.close();
//        Log.d("CURSOR", c.toString());
//        while (c.moveToNext())
//        {
//            Log.d("ITEM ID - ",c.getInt(c.getColumnIndex(TableHelperOrdiniItems._ID))+" - "+ c.getString(c.getColumnIndex(TableHelperMenuItems.NOME)) + " - " + c.getInt(c.getColumnIndex(TableHelperOrdiniItems.QUANTITA)));
//        }
        db.close();
    }

    public void onExit(View v)
    {
        finish();
    }
}
