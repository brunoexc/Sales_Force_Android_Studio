package com.example.sales_force.Controladores;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.sales_force.Classes.Users;
import com.example.sales_force.Database;
import com.example.sales_force.R;

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
    Database helper;
    SQLiteDatabase db;

    public UserController(Context context) {
        this.context = context;
        lista = new ArrayList<>();
        carregarLista();

        helper = new Database(this.context);
        db = helper.getWritableDatabase();
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

    public void SaveUser (String name, String user, String password){

        Users usuario = new Users();
        usuario.name = name;
        usuario.user  = user;
        usuario.password = password;
        lista.add(usuario);

        SQLiteDatabase db = helper.getWritableDatabase();
        try{
            ContentValues cv = new ContentValues();
            cv.put("name", usuario.name);
            cv.put("user", usuario.user);
            cv.put("password", usuario.password);
            long id = db.insert("Users", null, cv);
            usuario.id = (int) id;
        }finally {
            db.close();
        }
    }

    public boolean ReadUser (String user_p, String password_p, Boolean validate_user){

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



//EXTRAS

/*  SALVAR USUÁRIO EM UMA LISTA A PARTIR DE UM JSON*/

//try {
//          Users usuario = new Users();
//              usuario.name = name;
//                usuario.user  = user;
//                usuario.password = password;
//                lista.add(usuario);
//            JSONObject jsonObj = new JSONObject();
//            JSONArray dados = new JSONArray();
//
//            for (Users u : lista) {
//              JSONObject obj = new JSONObject();
//              obj.put("id", u.id);
//              obj.put("nome", u.name);
//              obj.put("user", u.user);
//              obj.put("senha", u.password);
//              dados.put(obj);
//            }
//            jsonObj.put("usuarios",dados);
//
//            FileOutputStream fos = this.context.openFileOutput("usuarios.txt", Context.MODE_PRIVATE);
//            PrintWriter writter = new PrintWriter(fos);
//            writter.println(jsonObj.toString());
//            writter.flush();
//            writter.close();
//            fos.close();
//
//        } catch (IOException | JSONException e) {
//            Log.e("ERRO", e.getMessage());
//        }
