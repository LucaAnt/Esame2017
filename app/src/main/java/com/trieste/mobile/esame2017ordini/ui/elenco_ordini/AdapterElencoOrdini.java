package com.trieste.mobile.esame2017ordini.ui.elenco_ordini;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.trieste.mobile.esame2017ordini.R;
import com.trieste.mobile.esame2017ordini.database.tables.TableHelperOrdini;

public class AdapterElencoOrdini extends CursorAdapter {

    public AdapterElencoOrdini(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        return LayoutInflater.from(context).inflate(R.layout.cell_riepilogo_ordine,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor c) {
            TextView textViewData= view.findViewById(R.id.cell_ordini_textView_data);
            TextView textViewImporto= view.findViewById(R.id.cell_ordini_textView_importo);
            TextView textViewQuantita= view.findViewById(R.id.cell_ordini_textView_qty);

            textViewData.setText(c.getString(c.getColumnIndex(TableHelperOrdini.DATA))+"");
            textViewImporto.setText(c.getInt(c.getColumnIndex(TableHelperOrdini.CALCULATED_IMPORT_FIELD))+"€");
            textViewQuantita.setText(c.getString(c.getColumnIndex(TableHelperOrdini.CALCULATED_QTY_FIELD)));
    }

}
