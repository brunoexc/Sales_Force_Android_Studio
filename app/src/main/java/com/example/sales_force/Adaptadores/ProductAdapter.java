package com.example.sales_force.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.sales_force.Classes.Products;
import com.example.sales_force.Classes.Users;
import com.example.sales_force.R;

import java.util.List;

public class ProductAdapter extends ArrayAdapter<Products>{

    TextView txt_product_id;
    TextView txt_product_name;
    TextView txt_product_cost;
    TextView txt_product_stock;

    public ProductAdapter(Context context, List<Products> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Products produtos = getItem(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_product_list, parent, false);

        txt_product_id = convertView.findViewById(R.id.txt_LPL_ProductID);
        txt_product_name = convertView.findViewById(R.id.txt_LPL_ProductName);
        txt_product_cost = convertView.findViewById(R.id.txt_LPL_ProductCost);
        txt_product_stock = convertView.findViewById(R.id.txt_LPL_ProductQtdStock);

        txt_product_id.setText(String.valueOf(produtos.id));
        txt_product_name.setText(produtos.name);
        txt_product_cost.setText(produtos.custo);
        txt_product_stock.setText(produtos.qtd_estoque);

        return convertView;
    }



}
