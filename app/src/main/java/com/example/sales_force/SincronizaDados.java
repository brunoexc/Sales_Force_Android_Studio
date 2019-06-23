package com.example.sales_force;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.sales_force.Classes.Clients;
import com.example.sales_force.Classes.Products;
import com.example.sales_force.Classes.Users;
import com.example.sales_force.Controladores.ClientController;
import com.example.sales_force.Controladores.ProductController;
import com.example.sales_force.Controladores.UserController;
import com.example.sales_force.Interfaces.ITarefaCallback;

import org.json.JSONObject;

public class SincronizaDados extends AppCompatActivity implements ITarefaCallback {

    public UserController userController;
    public ProductController productController;
    public ClientController clientController;
    public TarefaPost tarefaPost;
    public TarefaGet tarefaGet;
    public Users usuario;
    public Products produto;
    public Clients cliente;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sincroniza_dados);

        userController = new UserController(this);
        productController = new ProductController(this);
        clientController = new ClientController(this);

    }


    public void onClickEnviarUsuario (View view){

        String json;
        for(int i = 0; i < userController.lista.size(); i++){
            usuario = new Users();
            usuario = userController.lista.get(i);
            json = userController.CriarJson(usuario);

            tarefaPost =  new TarefaPost();
            tarefaPost.chamada = 0;
            tarefaPost.callback = this;
            tarefaPost.execute(json);
        }

    }

    public void onClickBuscarUsuario (View view){

        String json;
        for(int i = 0; i < userController.lista.size(); i++){
            usuario = new Users();
            usuario = userController.lista.get(i);
            json = userController.CriarJson(usuario);

            tarefaGet =  new TarefaGet();
            tarefaGet.chamada = 0;
            tarefaGet.callback = this;
            tarefaGet.execute(json);

            Toast.makeText(getApplicationContext(), "Dados: "+ tarefaGet.dados, Toast.LENGTH_SHORT).show();


        }

    }

    public void onClickEnviarCliente (View view){

        String json;
        for(int i = 0; i < clientController.lista_cliente.size(); i++){
            cliente = new Clients();
            cliente = clientController.lista_cliente.get(i);
            json = clientController.CriarJson(cliente);

            tarefaPost =  new TarefaPost();
            tarefaPost.chamada = 1;
            tarefaPost.callback = this;
            tarefaPost.execute(json);
        }

    }

    public void onClickEnviarProduto (View view){

        String json;
        for(int i = 0; i < productController.lista_produto.size(); i++){
            produto = new Products();
            produto = productController.lista_produto.get(i);
            json = productController.CriarJson(produto);

            tarefaPost =  new TarefaPost();
            tarefaPost.chamada = 2;
            tarefaPost.callback = this;
            tarefaPost.execute(json);
        }
    }


    @Override
    public void retornoCallback(int code) {
        Toast.makeText(getApplicationContext(), "Retorno: " + code, Toast.LENGTH_SHORT).show();
    }
}
