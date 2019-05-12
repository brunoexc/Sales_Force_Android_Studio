package com.example.sales_force;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import com.example.sales_force.Adaptadores.UserAdapter;
import com.example.sales_force.Controladores.UserController;

public class UserAdministrationActivity extends AppCompatActivity {

    public UserController controller;
    public UserAdapter adapter;
    public ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_administration);

        controller = new UserController(this);

        listView = findViewById(R.id.list_Users);
        adapter = new UserAdapter(this, controller.lista);
        listView.setAdapter(adapter);

    }

}
