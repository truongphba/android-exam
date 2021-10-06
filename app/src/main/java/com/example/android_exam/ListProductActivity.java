package com.example.android_exam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ListProductActivity extends AppCompatActivity {

    private DBHelper db;
    private Cursor cursor;
    private SimpleCursorAdapter simpleCursorAdapter;
    private ListView lvProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);
        db = new DBHelper(this);
        lvProducts = findViewById(R.id.lvProducts);
        cursor = db.getAllProduct();
        simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.item_product, cursor, new String[]{
                DBHelper.ID, DBHelper.NAME, DBHelper.QUANTITY}, new int[]{R.id.tvId, R.id.tvName, R.id.tvQuantity},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        lvProducts.setAdapter(simpleCursorAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        //reload data
        Cursor c = db.getAllProduct();
        simpleCursorAdapter.changeCursor(c);
        simpleCursorAdapter.notifyDataSetChanged();
        db.close();
    }
}