package com.example.sales_force;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.sales_force.Adaptadores.ProductAdapter;
import com.example.sales_force.Controladores.ProductController;

public class RegisterOrder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_order);

    }
}
