package com.trieste.mobile.esame2017ordini.ui.inserisci_ordine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.trieste.mobile.esame2017ordini.R;
import com.trieste.mobile.esame2017ordini.database.OrdiniDb;
import com.trieste.mobile.esame2017ordini.database.tables.TableHelperMenuItems;
import com.trieste.mobile.esame2017ordini.database.tables.TableHelperOrdini;
import com.trieste.mobile.esame2017ordini.database.tables.TableHelperOrdiniItems;
import com.trieste.mobile.esame2017ordini.model.MenuItem;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class ActivityInserisciOrdine extends AppCompatActivity {

    Date creationDate;
    String creationDateString;
    ListView listViewItems;
    AdapterInserisciOrdini adapterInserisciOrdini;
    List<OrderItem> orderItemList;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserisci_ordine);
        creationDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm yyyy/MM/dd ");
        creationDateString = simpleDateFormat.format(this.creationDate);
        TextView textViewDate = findViewById(R.id.textViewInserisciOrdineActivityDate);
        textViewDate.setText(creationDateString);
        //
        orderItemList =new LinkedList<>();
        db =  (new OrdiniDb(this)).getWritableDatabase();
        Cursor c = db.query(TableHelperMenuItems.TABLE_NAME,null,null,null,null,null,null);

        while (c.moveToNext())
            orderItemList.add(new OrderItem(new MenuItem(c),0));

        //List Setup
        final AdapterInserisciOrdini adapter = new AdapterInserisciOrdini();
        adapter.setData(orderItemList);
        listViewItems = findViewById(R.id.listViewInserisciOrdini);
        listViewItems.setAdapter(adapter);
        listViewItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                orderItemList.get(position).quantita++;
                adapter.notifyDataSetChanged();

            }
        });

        listViewItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                orderItemList.get(position).quantita = 0;
                adapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    public void onCancel(View v)
    {
        finish();
    }

    public void onSave(View v)
    {
        ContentValues contentValuesOrdine = new ContentValues();
        contentValuesOrdine.put(TableHelperOrdini.DATA,creationDateString);
        long insertionId = db.insert(TableHelperOrdini.TABLE_NAME,null,contentValuesOrdine);
        for (OrderItem item :orderItemList) {
            if (item.quantita>0)
            {
                ContentValues contentValuesOrderItem = new ContentValues();
                contentValuesOrderItem.put(TableHelperOrdiniItems.ITEM_ID,item.menuItem._id);
                contentValuesOrderItem.put(TableHelperOrdiniItems.ORDINE_ID,insertionId);
                contentValuesOrderItem.put(TableHelperOrdiniItems.QUANTITA,item.quantita);
                db.insert(TableHelperOrdiniItems.TABLE_NAME,null,contentValuesOrderItem);
            }
        }

        finish();
    }

    public class OrderItem
    {
        public OrderItem(MenuItem menuItem, int quantita) {
            this.menuItem = menuItem;
            this.quantita = quantita;
        }

        MenuItem menuItem;
        int quantita;
    }
}
