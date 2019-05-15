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

    TextView txt_user_id;
    TextView txt_user_name;

    public ProductAdapter(Context context, List<Products> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Products produtos = getItem(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_product_list, parent, false);

        txt_user_id = (TextView) convertView.findViewById(R.id.txt_LUL_UserID);
        txt_user_name = (TextView) convertView.findViewById(R.id.txt_LUL_UserName);

        txt_user_id.setText(String.valueOf(produtos.id + " - "));
        txt_user_name.setText(produtos.name);

        return convertView;
    }



}
