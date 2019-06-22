package com.example.sales_force;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.sales_force.Classes.Users;
import com.example.sales_force.Controladores.UserController;
import com.example.sales_force.Interfaces.ITarefaCallback;

import org.json.JSONObject;

public class SincronizaDados extends AppCompatActivity implements ITarefaCallback {

    public UserController userController;
    public TarefaPost tarefaPost;
    public Users usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sincroniza_dados);

        userController = new UserController(this);

    }


    public void onClickEnviarUsuario (View view){


        for(int i = 0; i < userController.lista.size(); i++){
            usuario = new Users();
            usuario = userController.lista.get(i);
            String json = userController.CriarJson(usuario);

            tarefaPost =  new TarefaPost();
            tarefaPost.chamada = 0;
            tarefaPost.callback = this;
            tarefaPost.execute(json);
        }

    }


    @Override
    public void retornoCallback(int code) {
        Toast.makeText(getApplicationContext(), "Retorno: " + code, Toast.LENGTH_SHORT).show();
    }
}
