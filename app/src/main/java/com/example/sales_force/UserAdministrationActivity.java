package com.example.sales_force;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import com.example.sales_force.Adaptadores.UserAdapter;
import com.example.sales_force.Classes.Users;
import com.example.sales_force.Controladores.UserController;

public class UserAdministrationActivity extends AppCompatActivity {

    public UserController controller;
    public Users usuario;
    public UserAdapter adapter;
    public ListView listView;


    public int input_id;
    int cad_edi;
    public String input_name;
    public String input_user;
    public String input_password;
    public EditText get_name;
    public EditText get_user;
    public EditText get_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_administration);

        controller = new UserController(this);

        listView = findViewById(R.id.list_Users);
        adapter = new UserAdapter(this, controller.lista);
        listView.setAdapter(adapter);
    }


    public void OnClickbuttonEditarUsuario (View view){

//        usuario = new Users();//
//
//        get_name = (EditText) findViewById(R.id.txt_input_UserName);
//        input_name = get_name.getText().toString();
//
//        get_user = (EditText) findViewById(R.id.txt_input_UserLogin);
//        input_user = get_user.getText().toString();
//
//        get_password = (EditText) findViewById(R.id.txt_input_UserPassword);
//        input_password = get_password.getText().toString();

        Intent ca_create_user = new Intent(this, CreateUserActivity.class);
        ca_create_user.putExtra("cad_edi", 1);
        startActivity(ca_create_user);
//        call_activity_create_user.putExtra("user", 1);
    }

}
