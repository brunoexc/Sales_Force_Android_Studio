package com.example.sales_force.Controladores;

import android.content.Context;
import android.util.Log;

import com.example.sales_force.Classes.Users;

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

public class UserController {

    private Context context;
    public ArrayList<Users> lista;

    public UserController(Context context) {
        this.context = context;
        lista = new ArrayList<>();
        carregarLista();
    }

    public void carregarLista() {
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

            lista.clear();
            for (int i = 0; i < dados.length(); i++) {
                JSONObject c = dados.getJSONObject(i);
                Users usuario = new Users();
                usuario.id = c.getInt("id");
                usuario.name = c.getString("nome");
                usuario.user  = c.getString("user");
                usuario.password = c.getString("senha");
                lista.add(usuario);
            }

        } catch ( IOException| JSONException e) {
            Log.e("ERRO", e.getMessage());
        }
    }

    public void SaveUserOnFile (int id, String name, String user, String password){

        Users usuario = new Users();
        usuario.id = id;
        usuario.name = name;
        usuario.user  = user;
        usuario.password = password;
        lista.add(usuario);

        try {
            JSONObject jsonObj = new JSONObject();
            JSONArray dados = new JSONArray();

            for (Users u : lista) {
              JSONObject obj = new JSONObject();
              obj.put("id", u.id);
              obj.put("nome", u.name);
              obj.put("user", u.user);
              obj.put("senha", u.password);
              dados.put(obj);
            }
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

    public boolean ReadUserOnFile (String user_p, String password_p, Boolean validate_user){

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

}