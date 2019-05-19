package com.example.sales_force;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.sales_force.Adaptadores.ClientAdapter;
import com.example.sales_force.Controladores.ClientController;

public class ClientAdministrationActivity extends AppCompatActivity {

    public ClientController controller;
    public ClientAdapter adapter;
    public ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_administration);

        controller = new ClientController(this);

        listView = findViewById(R.id.list_Clients);
        adapter = new ClientAdapter(this, controller.lista_cliente);
        listView.setAdapter(adapter);
    }


}