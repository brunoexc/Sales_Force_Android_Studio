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

        Intent call_activity_create_client = new Intent(this, CreateClientActivity.class);
        startActivity(call_activity_create_client);
    }


    public void OnClickbuttonCadastrarProduto (View view){

        Intent call_activity_register_product = new Intent(this, RegisterProduct.class);
        startActivity(call_activity_register_product);

    }


    public void OnClickbuttonCriarPedido (View view){


    }


    public void OnClickbuttonRetornar (View view){

        Intent call_activity_main = new Intent(this, MainActivity.class);
        startActivity(call_activity_main);

    }
}
