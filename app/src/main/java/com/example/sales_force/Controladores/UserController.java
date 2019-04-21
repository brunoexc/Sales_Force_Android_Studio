package com.example.sales_force.Controladores;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.sales_force.Classes.Users;
import com.example.sales_force.CreateUserActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;



public class UserController extends Users {

    Users user;

    public UserController() {  }


//    public void SaveUserOnFile (int id, String name, String user, String password)
      public void SaveUserOnFile (ArrayList<Users> lista){

//        this.user.id = id;
//        this.user.name = name;
//        this.user.user = user;
//        this.user.password = password;


        Toast.makeText(this, "Cheguei no Método", Toast.LENGTH_SHORT).show();


        try {
            JSONObject jsonObj = new JSONObject();
            JSONArray dados = new JSONArray();

            for (Users u : lista) {
                JSONObject obj = new JSONObject();
//                obj.put("id", this.user.id);
//                obj.put("nome", this.user.name);
//                obj.put("nome", this.user.user);
//                obj.put("senha", this.user.password);

                obj.put("id", u.id);
                obj.put("nome", u.name);
                obj.put("user", u.user);
                obj.put("senha", u.password);
                dados.put(obj);
            }

            jsonObj.put("Usuários:",dados);

            FileOutputStream fos = openFileOutput("usuarios.txt", Context.MODE_PRIVATE);
            PrintWriter writter = new PrintWriter(fos);
            writter.println(jsonObj.toString());
            writter.flush();
            writter.close();
            fos.close();

        } catch (IOException | JSONException e) {
            Log.e("ERRO", e.getMessage());
        }

    }


    public void ReadUserOnFile (String usuario, String senha){


    }



}

