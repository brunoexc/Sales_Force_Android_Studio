package com.example.sales_force.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.sales_force.Classes.Clients;
import com.example.sales_force.R;

import java.util.List;

public class ClientAdapter extends ArrayAdapter<Clients>{

    TextView txt_client_id;
    TextView txt_client_name;
    TextView txt_client_uf;
    TextView txt_client_city;

    public ClientAdapter(Context context, List<Clients> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Clients clientes = getItem(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_client_list, parent, false);

        txt_client_id = convertView.findViewById(R.id.txt_LCL_ClientID);
        txt_client_name = convertView.findViewById(R.id.txt_LCL_ClientName);
        txt_client_uf = convertView.findViewById(R.id.txt_LCL_ClientUF);
        txt_client_city = convertView.findViewById(R.id.txt_LCL_ClientCity);

        txt_client_id.setText(String.valueOf(clientes.id + " - "));
        txt_client_name.setText(clientes.name + " ");
        txt_client_uf.setText(clientes.uf + " ");
        txt_client_city.setText(clientes.city);

        return convertView;
    }
}
