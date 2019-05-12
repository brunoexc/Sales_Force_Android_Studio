package com.example.sales_force.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.sales_force.Classes.Users;
import com.example.sales_force.R;

import java.util.List;

public class UserAdapter extends ArrayAdapter<Users>{

    TextView txt_user_id;
    TextView txt_user_name;

    public UserAdapter(Context context, List<Users> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Users usuario = getItem(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_user_list, parent, false);

        txt_user_id = (TextView) convertView.findViewById(R.id.txt_LUL_UserID);
        txt_user_name = (TextView) convertView.findViewById(R.id.txt_LUL_UserName);

        txt_user_id.setText(String.valueOf(usuario.id + " - "));
        txt_user_name.setText(usuario.name);

        return convertView;
    }


}
