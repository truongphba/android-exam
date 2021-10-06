package com.example.android_exam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edName;
    private EditText edQuantity;
    private TextView tvValidateName;
    private TextView tvValidateQuantity;
    private Button btnAdd;
    private Button btnView;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        edName = findViewById(R.id.edName);
        edQuantity = findViewById(R.id.edQuantity);
        tvValidateName = findViewById(R.id.tvValidateName);
        tvValidateQuantity = findViewById(R.id.tvValidateQuantity);
        btnAdd = findViewById(R.id.btnAdd);
        btnView = findViewById(R.id.btnView);
        dbHelper = new DBHelper(this);

        btnAdd.setOnClickListener(this);
        btnView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnAdd) {
            onAddProduct();
        }
        if (view.getId() == R.id.btnView) {
            onViewProducts();
        }
    }

    private void onAddProduct() {
        if (edName.getText().toString().equals("")) {
            tvValidateName.setText("Name is required!");
            return;
        }
        if (edQuantity.getText().toString().equals("")) {
            tvValidateQuantity.setText("Quantity is required!");
            return;
        }
        if (edQuantity.getText().toString().matches("-?(0|[1-9]\\d*)")) {
            tvValidateQuantity.setText("Quantity must be integer!");
            return;
        }

        String isAdd = dbHelper.addProduct(edName.getText().toString(), edQuantity.getText().toString());
        Toast.makeText(this, isAdd, Toast.LENGTH_SHORT).show();
    }

    private void onViewProducts() {
        Intent intent = new Intent(this, ListProductActivity.class);
        startActivity(intent);
    }
    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }
}