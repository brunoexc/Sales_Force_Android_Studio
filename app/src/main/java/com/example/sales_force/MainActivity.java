package com.example.sales_force;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sales_force.Classes.Users;
import com.example.sales_force.Controladores.UserController;

public class MainActivity extends AppCompatActivity {


    UserController user_controller;
    public String input_name;
    public String input_user;
    public String input_password;
    public EditText get_name;
    public EditText get_user;
    public EditText get_password;

    public String super_user;
    public String super_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        super_user = "admin";
        super_password = "admin";

        user_controller = new UserController(this);
//        user_controller.SaveAdminOnFile();
    }



    public void OnClickbuttonCadastrar (View view){

        Intent call_activity_create_user = new Intent(this, CreateUserActivity.class);
        startActivity(call_activity_create_user);


    }


    public void OnClickbuttonAcessar (View view){

        get_user = (EditText) findViewById(R.id.txt_input_Login);
        input_user = get_user.getText().toString();

        get_password = (EditText) findViewById(R.id.txt_input_Password);
        input_password = get_password.getText().toString();

        if (input_user.equals(super_user) && input_password.equals(super_password)){

            Toast.makeText(this, "Bem vindo!", Toast.LENGTH_SHORT).show();
            Intent call_activity_menu = new Intent(this, MenuActivity.class);
            this.startActivity(call_activity_menu);
            finish();
        }
        else{

            Boolean validate_user = false;

            validate_user = user_controller.ReadUserOnFile(input_user, input_password, validate_user);

            if (validate_user == true){

                Toast.makeText(this, "Bem vindo!", Toast.LENGTH_SHORT).show();
                Intent call_activity_menu = new Intent(this, MenuActivity.class);
                this.startActivity(call_activity_menu);
                finish();
            }
            else{

                Toast.makeText(this, "Usuário ou Senha Incorreto(s)", Toast.LENGTH_SHORT).show();
            }
        }

    }




}