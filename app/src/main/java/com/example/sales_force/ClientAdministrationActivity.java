package com.example.sales_force;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
    String[] DeleteArray;
    ArrayAdapter<String> adapter_m_delete;

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


    public void AlertaDelete(View view){

        if (client != null) {
            DeleteArray = getResources().getStringArray(R.array.SystemMessageDelete);
            adapter_m_delete = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, DeleteArray);

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage(adapter_m_delete.getItem(2) + client.name + " ?");

            alertDialogBuilder.setCancelable(false);

            alertDialogBuilder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    controller.DeleteClient(client);
                    controller.carregarLista();
                    selected_client.setText("");
                    adapter.notifyDataSetChanged();
                    Toast.makeText(ClientAdministrationActivity.this, "Cliente " + client.name + " deletado", Toast.LENGTH_SHORT).show();
                }
            });
            alertDialogBuilder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(ClientAdministrationActivity.this, "Operação Cancelada!", Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                }
            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }else{

            Toast.makeText(ClientAdministrationActivity.this, "Nenhum Cliente Selecionado!", Toast.LENGTH_SHORT).show();
        }
    }

    public void OnClickbuttonEditarCliente (View view){

        if(client != null){
            Intent ca_create_client = new Intent(this, CreateClientActivity.class);
            ca_create_client.putExtra("cad_edi", 1);
            ca_create_client.putExtra("client_id", id_client);
            startActivity(ca_create_client);
            finish();
        }else{
            Toast.makeText(ClientAdministrationActivity.this, "Nenhum Cliente Selecionado!", Toast.LENGTH_SHORT).show();
        }
    }
}
