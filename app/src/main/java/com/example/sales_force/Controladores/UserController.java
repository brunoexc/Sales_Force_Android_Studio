package com.example.sales_force.Controladores;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.sales_force.Classes.Users;
import com.example.sales_force.CreateUserActivity;
import com.example.sales_force.MenuActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;



public class UserController {

    Users user_global = new Users();
    private Context context;

    public UserController(Context context) {
        this.context = context;
    }

    public void SaveUserOnFile (int id, String name, String user, String password){
//      public void SaveUserOnFile (ArrayList<Users> lista){


        this.user_global.id = id;
        this.user_global.name = name;
        this.user_global.user = user;
        this.user_global.password = password;



        try {
            JSONObject jsonObj = new JSONObject();
            JSONArray dados = new JSONArray();

            JSONObject obj = new JSONObject();
            obj.put("id", this.user_global.id);
            obj.put("nome", this.user_global.name);
            obj.put("user", this.user_global.user);
            obj.put("senha", this.user_global.password);

            dados.put(obj);
//            }

            jsonObj.put("usuarios",dados);

            FileOutputStream fos = this.context.openFileOutput("usuarios.txt", Context.MODE_APPEND);
            PrintWriter writter = new PrintWriter(fos);
            writter.println(jsonObj.toString());
            writter.flush();
            writter.close();
            fos.close();


            Toast.makeText(this.context, "Usuário Cadastrado", Toast.LENGTH_SHORT).show();

        } catch (IOException | JSONException e) {
            Log.e("ERRO", e.getMessage());
        }

    }


    public boolean ReadUserOnFile (String user_p, String password_p, Boolean validate_user){

//        this.user_global.user = user_p;
//        this.user_global.password = password_p;

        try {
            FileInputStream fis = this.context.openFileInput("usuarios.txt");

            BufferedReader reader = new BufferedReader( new InputStreamReader(fis));
            StringBuilder sb = new StringBuilder();
            String linha;

            do{
                linha = reader.readLine();
                if (sb.length() != 0)
                    sb.append('\n');
                sb.append(linha);
            }while(linha != null);


            reader.close();
            fis.close();

            //Log.i("TAG",sb.toString());
            String jsonStr = sb.toString();
            JSONObject jsonObj = new JSONObject(jsonStr);
            JSONArray dados = jsonObj.getJSONArray("usuarios");

            for (int i = 0; i < dados.length(); i++) {
                JSONObject c = dados.getJSONObject(i);
                Users user_comparativo = new Users();
                user_comparativo.id = c.getInt("id");
                user_comparativo.user  = c.getString("user");
                user_comparativo.password = c.getString("senha");

                if (user_comparativo.user.equals(user_p) && user_comparativo.password.equals(password_p) ){
                    validate_user = true;
                    return validate_user;
                }
                else{
                    validate_user = false;
                }
            }



            return validate_user;

        } catch ( IOException| JSONException e) {
            Log.e("ERRO", e.getMessage());
        }

        return validate_user;
    }



    public void SaveAdminOnFile (){

        int admin_id = 0;
        String admin_name = "Admin Caride";
        String admin_user = "admin";
        String admin_password = "admin";

        try {
            JSONObject jsonObj = new JSONObject();
            JSONArray dados = new JSONArray();

            JSONObject obj = new JSONObject();

            obj.put("id", admin_id);
            obj.put("nome", admin_name);
            obj.put("user", admin_user);
            obj.put("senha", admin_password);
            dados.put(obj);

            jsonObj.put("usuarios",dados);

            FileOutputStream fos = this.context.openFileOutput("usuarios.txt", Context.MODE_PRIVATE);
            PrintWriter writter = new PrintWriter(fos);
            writter.println(jsonObj.toString());
            writter.flush();
            writter.close();
            fos.close();

        } catch (IOException | JSONException e) {
            Log.e("ERRO", e.getMessage());
        }

    }

}