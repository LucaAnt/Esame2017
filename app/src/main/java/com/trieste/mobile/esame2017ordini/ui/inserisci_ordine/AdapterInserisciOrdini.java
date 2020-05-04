package com.trieste.mobile.esame2017ordini.ui.inserisci_ordine;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.trieste.mobile.esame2017ordini.R;

import java.util.LinkedList;
import java.util.List;

public class AdapterInserisciOrdini extends BaseAdapter {

    List<ActivityInserisciOrdine.OrderItem> list = new LinkedList<>();

    public void setData(List<ActivityInserisciOrdine.OrderItem> list)
    {
        this.list = list;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).menuItem._id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null)
            convertView =  LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_inserisci_ordini,parent,false);


        TextView typeTextView = convertView.findViewById(R.id.cell_type);
        TextView qtyTextView = convertView.findViewById(R.id.cell_qty);
        typeTextView.setText(list.get(position).menuItem.nome);
        qtyTextView.setText(list.get(position).quantita+"");

        return convertView;

    }
}
