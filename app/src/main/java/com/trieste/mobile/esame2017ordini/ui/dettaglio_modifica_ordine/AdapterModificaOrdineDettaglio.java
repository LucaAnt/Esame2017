package com.trieste.mobile.esame2017ordini.ui.dettaglio_modifica_ordine;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cursoradapter.widget.CursorAdapter;

import com.trieste.mobile.esame2017ordini.R;
import com.trieste.mobile.esame2017ordini.database.tables.TableHelperMenuItems;
import com.trieste.mobile.esame2017ordini.database.tables.TableHelperOrdiniItems;

public class AdapterModificaOrdineDettaglio extends CursorAdapter {
    public AdapterModificaOrdineDettaglio(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.cell_inserisci_ordini,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor c) {
        TextView nameTextView = view.findViewById(R.id.cell_type);
        TextView qtyTextView = view.findViewById(R.id.cell_qty);
        nameTextView.setText(c.getString(c.getColumnIndex(TableHelperMenuItems.NOME)));
        qtyTextView.setText( c.getInt(c.getColumnIndex(TableHelperOrdiniItems.QUANTITA))+"");
    }
}
