package com.example.sales_force;

import androidx.appcompat.app.AppCompatActivity;

        import android.os.Bundle;
        import android.widget.ListView;

        import com.example.sales_force.Adaptadores.ProductAdapter;
        import com.example.sales_force.Controladores.ProductController;

public class ProductAdministrationActivity extends AppCompatActivity {

    public ProductController controller;
    public ProductAdapter adapter;
    public ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_administration);

        controller = new ProductController(this);

        listView = findViewById(R.id.list_Products);
        adapter = new ProductAdapter(this, controller.lista_produto);
        listView.setAdapter(adapter);
    }


}
