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
    String[] DeleteArray;
    ArrayAdapter<String> adapter_m_delete;

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

    public void AlertaDelete(View view){

        if (product != null) {
            DeleteArray = getResources().getStringArray(R.array.SystemMessageDelete);
            adapter_m_delete = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, DeleteArray);

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage(adapter_m_delete.getItem(1) + product.name + " ?");

            alertDialogBuilder.setCancelable(false);

            alertDialogBuilder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    controller.DeleteProduct(product);
                    controller.carregarLista();
                    selected_product.setText("");
                    adapter.notifyDataSetChanged();
                    Toast.makeText(ProductAdministrationActivity.this, "Produto " + product.name + " deletado", Toast.LENGTH_SHORT).show();
                }
            });
            alertDialogBuilder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(ProductAdministrationActivity.this, "Operação Cancelada", Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                }
            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }else{
            Toast.makeText(ProductAdministrationActivity.this, "Nenhum Produto Selecionado!", Toast.LENGTH_SHORT).show();
        }
    }

    public void OnClickbuttonEditarProdutos (View view){

        if(product != null){
            Intent ca_register_product = new Intent(this, RegisterProduct.class);
            ca_register_product.putExtra("cad_edi", 1);
            ca_register_product.putExtra("product_id", id_product);
            startActivity(ca_register_product);
            finish();
        }else{
            Toast.makeText(ProductAdministrationActivity.this, "Nenhum Produto Selecionado!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {

    }

}
