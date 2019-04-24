package com.example.sales_force;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sales_force.Classes.Users;
import com.example.sales_force.Controladores.UserController;

import org.json.JSONException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class CreateUserActivity extends AppCompatActivity {


    Users user;
    UserController controller;

    public int input_id;
    public String input_name;
    public String input_user;
    public String input_password;
    public EditText get_name;
    public EditText get_user;
    public EditText get_password;


    public ListView list_view;
    public ArrayAdapter<Users> adaptador;
    public ArrayList<Users> listaUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

    }


    public void OnClickbuttonCadastrar (View view){

//      input_id = findViewById(R.id.txt_input_User);

        listaUsuarios = new ArrayList<Users>();

        get_name = (EditText) findViewById(R.id.txt_input_UserName);
        input_name = get_name.getText().toString();

        get_user = (EditText) findViewById(R.id.txt_input_UserLogin);
        input_user = get_user.getText().toString();

        get_password = (EditText) findViewById(R.id.txt_input_UserPassword);
        input_password = get_password.getText().toString();

//        listaUsuarios.add(new Users(1, input_name, input_user, input_password));


//      listaUsuarios.add(new Usuario(1, input_name, input_user, input_password));


        controller = new UserController(this);
        controller.SaveUserOnFile(1, input_name, input_user, input_password);

//        controller.SaveUserOnFile(listaUsuarios);




//        Toast.makeText(this, "Usuário cadastrado com sucesso", Toast.LENGTH_SHORT).show();


//        Intent call_activity_menu = new Intent(this, MenuActivity.class);
//        startActivity(call_activity_menu);


//      adaptador = new ArrayAdapter<Usuario>( this, android.R.layout.simple_list_item_1, listaUsuarios);



    }


    public void OnClickbuttonCancelar (View view){

        Intent call_activity_main = new Intent(this, MainActivity.class);
        startActivity(call_activity_main);
    }








}
