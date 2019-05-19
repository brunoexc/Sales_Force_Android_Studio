package com.example.sales_force;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sales_force.Classes.Users;
import com.example.sales_force.Controladores.UserController;

import org.json.JSONException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class CreateUserActivity extends AppCompatActivity implements View.OnClickListener {

    UserController controller;

    int cad_edi;
    public String input_name, input_user, input_password;
    Boolean valida_user;

    public EditText get_name, get_user, get_password;
    public Button troca_botao;

    public ListView list_view;
    public ArrayAdapter<Users> adaptador;
    public ArrayList<Users> listaUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        Intent intent = getIntent();

        //Tratar tela para receber cadastro ou edição de usuários
        cad_edi = intent.getIntExtra("cad_edi", 0);
        troca_botao = findViewById(R.id.but_Register);
        troca_botao.setOnClickListener(this);
        botaoCadastroEditar();

        //Tratar campos obrigatórios
        valida_user = false;
    }

    @Override
    public void onClick(View v) {
        switch (cad_edi){
            //cad_edi = 0 (Significa que estou querendo cadastrar)
            case 0:
                OnClickbuttonCadastrar(v);
                break;

            //cad_edi = 1 (Significa que estou querendo editar um cadastro)
            case 1:
                Teste();
                break;
        }
    }

    public void botaoCadastroEditar(){

        if(cad_edi == 0){
            troca_botao.setText("Cadastrar");
        }else{
            troca_botao.setText("Salvar");
        }
    }


    public void OnClickbuttonCadastrar (View view){

        get_name = (EditText) findViewById(R.id.txt_input_UserName);
        input_name = get_name.getText().toString();

        get_user = (EditText) findViewById(R.id.txt_input_UserLogin);
        input_user = get_user.getText().toString();

        get_password = (EditText) findViewById(R.id.txt_input_UserPassword);
        input_password = get_password.getText().toString();



        valida_user = verificaObrigatórios(input_name, input_user, input_password, valida_user);

        if (valida_user){
            controller = new UserController(this);
            controller.SaveUser(input_name, input_user, input_password);
            Toast.makeText(this, "Usuário: "+ input_name.toUpperCase() + " cadastrado", Toast.LENGTH_SHORT).show();
            finish();
        }
        else{
            Toast.makeText(getApplicationContext(), "Favor Preencher Campos Obrigatórios(*)", Toast.LENGTH_SHORT).show();
        }
    }

    public void Teste(){

        Toast.makeText(this, "Chamei o método TESTE ", Toast.LENGTH_SHORT).show();
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