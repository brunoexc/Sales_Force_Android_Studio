package com.example.sales_force;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sales_force.Classes.Users;
import com.example.sales_force.Controladores.UserController;
import com.example.sales_force.Interfaces.ITarefaCallback;

public class CreateUserActivity extends AppCompatActivity implements View.OnClickListener, ITarefaCallback {

    UserController controller;
    Users db_user;

    int input_id, cad_edi, id_user;
    public String input_name, input_user, input_password;
    Boolean valida_user;

    public EditText get_name, get_user, get_password;
    public Button troca_botao;


    Database helper;
    TarefaPost tarefa_p;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        intent = getIntent();

        controller = new UserController(this);

        //Tratar tela para receber cadastro ou edição de usuários
        cad_edi = intent.getIntExtra("cad_edi", 0);

        //Verifica se a chamada da Activity é para editar ou cadastrar
        if(cad_edi == 1){
            //Usuário que será lido do banco/lista para editar
            id_user = intent.getIntExtra("user_id", 0);
            db_user = new Users();
            db_user = controller.lista.get(id_user - 1);
        }

        helper = new Database(this);

        //Acha os campos do formulário para cadastrar ou editar
        get_name = findViewById(R.id.txt_input_UserName);
        get_user = findViewById(R.id.txt_input_UserLogin);
        get_password = findViewById(R.id.txt_input_UserPassword);

        //Identifica se a chamada da Activity é para cadastrar ou editar
        troca_botao = findViewById(R.id.but_Register);
        troca_botao.setOnClickListener(this);

        //Tratar campos obrigatórios
        valida_user = false;

        botaoCadastroEditar();
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
                AtualizaUsuario();
                break;
        }
    }

    public void botaoCadastroEditar(){

        if(cad_edi == 0){
            troca_botao.setText("Cadastrar");
        }else{
            get_user.setText(db_user.user);
            get_name.setText(db_user.name);
            get_password.setText(db_user.password);

            troca_botao.setText("Salvar");
        }
    }

    public void OnClickbuttonCadastrar (View view){

        input_name = get_name.getText().toString();
        input_user = get_user.getText().toString();
        input_password = get_password.getText().toString();

        valida_user = verificaObrigatórios(input_name, input_user, input_password, valida_user);

        if (valida_user){
            controller.SaveUser(input_name, input_user, input_password);
            Toast.makeText(this, "Usuário: "+ input_name.toUpperCase() + " cadastrado!", Toast.LENGTH_SHORT).show();
            finish();
        }
        else{
            Toast.makeText(getApplicationContext(), "Favor Preencher Campos Obrigatórios(*)", Toast.LENGTH_SHORT).show();
        }
    }

    public void AtualizaUsuario(){

        input_id = db_user.id;
        input_name = get_name.getText().toString();
        input_user = get_user.getText().toString();
        input_password = get_password.getText().toString();

        valida_user = verificaObrigatórios(input_name, input_user, input_password, valida_user);

        if (valida_user){
            controller.UpdateUser(input_id, input_name, input_user, input_password);
            Toast.makeText(this, "Usuário Atualizado!", Toast.LENGTH_SHORT).show();
            finish();
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

    public void onClickSalvarUsuario (View view){

        input_name = get_name.getText().toString();
        input_user = get_user.getText().toString();
        input_password = get_password.getText().toString();

//        tarefa_p =  new TarefaPost();
//        tarefa_p.chamada = 0;
//        tarefa_p.callback = this;
        //        String json = controller.CriarJson(input_name, input_user, input_password);




    }


    @Override
    public void retornoCallback(int code) {
        Toast.makeText(getApplicationContext(), "Retorno: " + code, Toast.LENGTH_SHORT).show();
    }
}




//        listaUsuarios.add(new Users(1, input_name, input_user, input_password));
//      listaUsuarios.add(new Usuario(1, input_name, input_user, input_password));
//        controller.SaveUserOnFile(listaUsuarios);




//        Toast.makeText(this, "Usuário cadastrado com sucesso", Toast.LENGTH_SHORT).show();

//      adaptador = new ArrayAdapter<Usuario>( this, android.R.layout.simple_list_item_1, listaUsuarios);