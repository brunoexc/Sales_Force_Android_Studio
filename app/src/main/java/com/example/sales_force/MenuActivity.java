package com.example.sales_force;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void OnClickbuttonCadastrarCliente (View view){

        Intent ca_create_client = new Intent(this, CreateClientActivity.class);
        ca_create_client.putExtra("cad_edi", 0);
        startActivity(ca_create_client);
    }

    public void OnClickbuttonCadastrarProduto (View view){

        Intent ca_register_product = new Intent(this, RegisterProduct.class);
        ca_register_product.putExtra("cad_edi", 0);
        startActivity(ca_register_product);
    }

    public void OnClickbuttonCadastrarUsuario (View view){

        Intent ca_create_user = new Intent(this, CreateUserActivity.class);
        ca_create_user.putExtra("cad_edi", 0);
        startActivity(ca_create_user);
    }

    public void OnClickbuttonEmDesenvolvimento (View view){
        Toast.makeText(this, "EM DESENVOLVIMENTO", Toast.LENGTH_SHORT).show();
    }

    public void OnClickbuttonAdministarUsuario (View view){

        Intent ca_adminstrate_user = new Intent(this, UserAdministrationActivity.class);
        startActivity(ca_adminstrate_user);
    }

    public void OnClickbuttonAdministarProduto (View view){

        Intent call_activity_adminstrate_product = new Intent(this, ProductAdministrationActivity.class);
        startActivity(call_activity_adminstrate_product);
    }

    public void OnClickbuttonAdministarCliente (View view){

        Intent call_activity_adminstrate_client = new Intent(this, ClientAdministrationActivity.class);
        startActivity(call_activity_adminstrate_client);
    }

}
