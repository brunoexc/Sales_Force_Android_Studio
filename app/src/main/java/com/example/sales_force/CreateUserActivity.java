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

    Boolean valida_user;


    public ListView list_view;
    public ArrayAdapter<Users> adaptador;
    public ArrayList<Users> listaUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        valida_user = false;

    }


    public void OnClickbuttonCadastrar (View view){

//      input_id = findViewById(R.id.txt_input_User);

//        listaUsuarios = new ArrayList<Users>();

        get_name = (EditText) findViewById(R.id.txt_input_UserName);
        input_name = get_name.getText().toString();

        get_user = (EditText) findViewById(R.id.txt_input_UserLogin);
        input_user = get_user.getText().toString();

        get_password = (EditText) findViewById(R.id.txt_input_UserPassword);
        input_password = get_password.getText().toString();

        valida_user = verificaObrigatórios(input_name, input_user, input_password, valida_user);

        if (valida_user == true){
            controller = new UserController(this);
            controller.SaveUserOnFile(1, input_name, input_user, input_password);
            Toast.makeText(this, "Usuário: "+ input_name.toUpperCase() + " cadastrado", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(), "Favor Preencher Campos Obrigatórios(*)", Toast.LENGTH_SHORT).show();
        }

    }


    public Boolean verificaObrigatórios(String name, String user, String password, Boolean valida){

        if (name.trim().equals("") || user.trim().equals("") || password.trim().equals("") )
        {
            valida = false;
        }
        else{
            valida = true;
        }
        return valida;
    }









}




//        listaUsuarios.add(new Users(1, input_name, input_user, input_password));
//      listaUsuarios.add(new Usuario(1, input_name, input_user, input_password));
//        controller.SaveUserOnFile(listaUsuarios);




//        Toast.makeText(this, "Usuário cadastrado com sucesso", Toast.LENGTH_SHORT).show();

//      adaptador = new ArrayAdapter<Usuario>( this, android.R.layout.simple_list_item_1, listaUsuarios);