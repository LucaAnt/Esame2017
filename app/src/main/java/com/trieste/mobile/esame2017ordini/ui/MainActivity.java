package com.trieste.mobile.esame2017ordini.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.trieste.mobile.esame2017ordini.R;
import com.trieste.mobile.esame2017ordini.database.OrdiniDb;
import com.trieste.mobile.esame2017ordini.database.tables.TableHelperOrdini;
import com.trieste.mobile.esame2017ordini.ui.inserisci_ordine.ActivityInserisciOrdine;
import com.trieste.mobile.esame2017ordini.ui.elenco_ordini.ActivityElencoOrdini;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToInserisciOrdine(View v)
    {
        startActivity(new Intent(this, ActivityInserisciOrdine.class));
    }

    public void goToModificaOrdine(View v)
    {
        startActivity(new Intent(this, ActivityElencoOrdini.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        TextView totaleTextView = findViewById(R.id.textViewMainActivityTotaleOrdini);
        SQLiteDatabase db = (new OrdiniDb(this)).getReadableDatabase();
        Cursor c = db.rawQuery(TableHelperOrdini.TOTAL_ORDERS,null);
        c.moveToFirst();
        totaleTextView.setText("Totale Ordini: " + c.getInt(c.getColumnIndex(TableHelperOrdini.ORDER_COUNT)));
        c.close();
        db.close();
    }
}
