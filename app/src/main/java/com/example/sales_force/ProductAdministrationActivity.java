package com.example.sales_force;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sales_force.Adaptadores.ProductAdapter;
import com.example.sales_force.Classes.Products;
import com.example.sales_force.Classes.Users;
import com.example.sales_force.Controladores.ProductController;

public class ProductAdministrationActivity extends AppCompatActivity implements View.OnClickListener {

    public ProductController controller;
    public ProductAdapter adapter;
    public ListView listView;

    public Products product;

    public int id_product;
    public TextView selected_product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_administration);

        controller = new ProductController(this);

        listView = findViewById(R.id.list_Products);
        adapter = new ProductAdapter(this, controller.lista_produto);
        listView.setAdapter(adapter);


        selected_product = findViewById(R.id.txt_input_PA_SelectedName);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                product = controller.lista_produto.get(position);
                SelecionaLista(product);
                id_product = product.id;

            }
        });
    }

    public void SelecionaLista (Products product){
        selected_product.setText(product.name);
    }


    public void OnClickbuttonEditarProdutos (View view){

        Intent ca_register_product = new Intent(this, RegisterProduct.class);
        ca_register_product.putExtra("cad_edi", 1);
        ca_register_product.putExtra("product_id", id_product);
        startActivity(ca_register_product);
        finish();
    }


    @Override
    public void onClick(View v) {

    }



}
