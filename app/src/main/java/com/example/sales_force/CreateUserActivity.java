package com.example.sales_force;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class CreateUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
    }



    public void OnClickbuttonCadastrar (View view){


//        user_name = findViewById(R.id.txt_input_UserName).toString();
//        Toast.makeText(this, "Usuário " + user_name + "criado com sucesso", Toast.LENGTH_SHORT).show();

        Intent call_activity_menu = new Intent(this, MenuActivity.class);
        startActivity(call_activity_menu);

    }


    public void OnClickbuttonCancelar (View view){

        Intent call_activity_main = new Intent(this, MainActivity.class);
        startActivity(call_activity_main);
    }
}
