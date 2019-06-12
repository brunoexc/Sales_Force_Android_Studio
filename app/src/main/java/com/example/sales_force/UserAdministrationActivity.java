package com.example.sales_force;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sales_force.Adaptadores.UserAdapter;
import com.example.sales_force.Classes.Users;
import com.example.sales_force.Controladores.UserController;

public class UserAdministrationActivity extends AppCompatActivity {

    public UserController controller;
    public Users user;
    public UserAdapter adapter;
    public ListView listView;
    String[] DeleteArray;
    ArrayAdapter<String> adapter_m_delete;

    public TextView selected_user;
    public int id_user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_administration);

        controller = new UserController(this);

        listView = findViewById(R.id.list_Users);
        adapter = new UserAdapter(this, controller.lista);
        listView.setAdapter(adapter);

        selected_user = findViewById(R.id.txt_input_UA_SelectedName);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                user = controller.lista.get(position);
                SelecionaLista(user);
                id_user = user.id;
            }
        });

    }

    public void SelecionaLista (Users user){
        selected_user.setText(user.name);
    }


    public void AlertaDelete(View view){

        if (user != null) {
            DeleteArray = getResources().getStringArray(R.array.SystemMessageDelete);
            adapter_m_delete = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, DeleteArray);

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage(adapter_m_delete.getItem(0) + user.name + " ?");

            alertDialogBuilder.setCancelable(false);

            alertDialogBuilder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    controller.DeleteUser(user);
                    controller.carregarLista();
                    selected_user.setText("");
                    adapter.notifyDataSetChanged();
                    Toast.makeText(UserAdministrationActivity.this, "Usuário " + user.name + " deletado", Toast.LENGTH_SHORT).show();
                }
            });
            alertDialogBuilder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(UserAdministrationActivity.this, "Operação Cancelada!", Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                }
            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }else{

            Toast.makeText(UserAdministrationActivity.this, "Nenhum Usuário Selecionado!", Toast.LENGTH_SHORT).show();
        }
    }


    public void OnClickbuttonEditarUsuario (View view){

        if(user != null){
            Intent ca_create_user = new Intent(this, CreateUserActivity.class);
            ca_create_user.putExtra("cad_edi", 1);
            ca_create_user.putExtra("user_id", id_user);
            startActivity(ca_create_user);
            finish();
        }else{
            Toast.makeText(UserAdministrationActivity.this, "Nenhum usuário selecionado!", Toast.LENGTH_SHORT).show();
        }
    }

}
