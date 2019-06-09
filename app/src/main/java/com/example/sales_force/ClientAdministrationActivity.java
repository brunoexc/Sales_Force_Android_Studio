package com.example.sales_force;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sales_force.Adaptadores.ClientAdapter;
import com.example.sales_force.Classes.Clients;
import com.example.sales_force.Classes.Products;
import com.example.sales_force.Controladores.ClientController;

public class ClientAdministrationActivity extends AppCompatActivity {

    public ClientController controller;
    public ClientAdapter adapter;
    public ListView listView;

    public Clients client;

    public int id_client;
    public TextView selected_client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_administration);

        controller = new ClientController(this);

        listView = findViewById(R.id.list_Clients);
        adapter = new ClientAdapter(this, controller.lista_cliente);
        listView.setAdapter(adapter);

        selected_client = findViewById(R.id.txt_input_CA_SelectedName);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                client = controller.lista_cliente.get(position);
                SelecionaLista(client);
                id_client = client.id;

            }
        });
    }

    public void SelecionaLista (Clients client){
        selected_client.setText(client.name);
    }

    public void OnClickbuttonEditarCliente (View view){

        Intent ca_create_client = new Intent(this, CreateClientActivity.class);
        ca_create_client.putExtra("cad_edi", 1);
        ca_create_client.putExtra("client_id", id_client);
        startActivity(ca_create_client);
        finish();
    }
}
