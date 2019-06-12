package com.example.sales_force;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import android.widget.ListView;
import android.widget.TextView;

import com.example.sales_force.Adaptadores.ProductAdapter;
import com.example.sales_force.Adaptadores.UserAdapter;
import com.example.sales_force.Classes.Products;
import com.example.sales_force.Classes.Users;
import com.example.sales_force.Controladores.ProductController;
import com.example.sales_force.Controladores.UserController;

public class RegisterOrder extends AppCompatActivity {

    public ProductController controller;
    public Products produto;
    public ListView listView;

    public ProductAdapter adapter;
    public TextView selected_user;
    public int id_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_order);



        listView = findViewById(R.id.list_Products);
//        adapter = new UserAdapter(this, controller.lista_produto);
//        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                produto = controller.lista_produto.get(position);
                SelecionaLista(produto);
                id_user = produto.id;
            }
        });


    }


    public void SelecionaLista (Products produto){
        selected_user.setText(produto.name);
    }




}
