package com.example.sales_force.Controladores;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sales_force.Classes.Users;
import com.example.sales_force.Database;
import com.example.sales_force.R;
import com.example.sales_force.TarefaPost;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

public class UserController {

    private Context context;
    public ArrayList<Users> lista, lista_servidor;
    Database helper;
    SQLiteDatabase db;
    Cursor cursor;
    Calendar calendario;
    SimpleDateFormat timeNow;
    TarefaPost tarefa_post;



    public UserController(Context context) {
        this.context = context;
        lista = new ArrayList<>();
        lista_servidor = new ArrayList<>();

        helper = new Database(this.context);
        db = helper.getWritableDatabase();

        calendario = Calendar.getInstance();
        timeNow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        timeNow.setTimeZone(TimeZone.getTimeZone("Brazil/East"));

        carregarLista();
    }

    public void carregarLista() {

        SQLiteDatabase db = helper.getReadableDatabase();
        try{
            cursor = db.rawQuery("select * from users", null);

            lista.clear();
            while (cursor.moveToNext()) {

                Users usuario = new Users();
                usuario.id = cursor.getInt(cursor.getColumnIndex("id"));
                usuario.name = cursor.getString(cursor.getColumnIndex("name"));
                usuario.user  = cursor.getString(cursor.getColumnIndex("user"));
                usuario.password = cursor.getString(cursor.getColumnIndex("password"));
                usuario.ultimaAlteracao = cursor.getString(cursor.getColumnIndex("ultimaAlteracao"));
                lista.add(usuario);
//                editText.getText().append("id: "+id+ ", nome: "+nome+ ", documento: "+documento+"\n");
            }
            cursor.close();
        }finally {
            db.close();
        }
    }

    public void SaveUser (String name, String user, String password){

        Users usuario = new Users();
        usuario.name = name;
        usuario.user  = user;
        usuario.password = password;
//        usuario.ultimaAlteracao = timeNow.format(calendario.getTime());
        lista_servidor.add(usuario);

        SQLiteDatabase db = helper.getWritableDatabase();
        try{
            ContentValues cv = new ContentValues();
            cv.put("name", usuario.name);
            cv.put("user", usuario.user);
            cv.put("password", usuario.password);
//            cv.put("ultimaAlteracao", usuario.ultimaAlteracao);
            long id = db.insert("Users", null, cv);
            usuario.id = (int) id;
        }finally {
            db.close();
        }
    }

    public void UpdateUser (Integer id_user, String name, String user, String password){

        Users usuario = new Users();
        usuario.name = name;
        usuario.user  = user;
        usuario.password = password;
        usuario.ultimaAlteracao = timeNow.format(calendario.getTime());

        SQLiteDatabase db = helper.getWritableDatabase();
        try{
            ContentValues cv = new ContentValues();
            cv.put("name", usuario.name);
            cv.put("user", usuario.user);
            cv.put("password", usuario.password);
            cv.put("ultimaAlteracao", usuario.ultimaAlteracao);

            db.update("users", cv,"id = ?", new String[] {String.valueOf(id_user)});

        }finally {
            db.close();
        }
    }

    public boolean ReadUser (String user_p, String password_p, Boolean validate_user){

        SQLiteDatabase db = helper.getReadableDatabase();
        try{
            cursor = db.rawQuery("select * from users", null);

            lista.clear();
            while (cursor.moveToNext()) {

                Users usuario = new Users();
                usuario.id = cursor.getInt(cursor.getColumnIndex("id"));
                usuario.name = cursor.getString(cursor.getColumnIndex("name"));
                usuario.user  = cursor.getString(cursor.getColumnIndex("user"));
                usuario.password = cursor.getString(cursor.getColumnIndex("password"));
                usuario.ultimaAlteracao = cursor.getString(cursor.getColumnIndex("ultimaAlteracao"));
                lista.add(usuario);

                if (usuario.user.equals(user_p) && usuario.password.equals(password_p) ){
                    validate_user = true;
                    return validate_user;
                }
                else{
                    validate_user = false;
                }
            }
            cursor.close();
        }finally {
            db.close();
        }
        return validate_user;
    }

    public void DeleteUser(Users user){

        SQLiteDatabase db = helper.getWritableDatabase();
        try{
            db.delete("Users","id = ?", new String[] {String.valueOf(user.id)});
        }finally {
            db.close();
        }
    }


    public String CriarJson(Users user){

       String json;
       JSONObject obj = null;

//        lista.clear();
        try {
                obj = new JSONObject();
                obj.put("id", user.id);
                obj.put("nome", user.name);
                obj.put("user", user.user);
                obj.put("senha", user.ultimaAlteracao);
            } catch (JSONException e1) {
            e1.printStackTrace();
        }
        json = obj.toString();
//            lista.add(usuario);


//            for (Users u : lista) {
//                obj = new JSONObject();
//                obj.put("id", u.id);
//                obj.put("nome", u.name);
//                obj.put("user", u.user);
//                obj.put("senha", u.password);
////                dados.put(obj);
//            }
//            jsonObj.put("usuarios",dados);

//            FileOutputStream fos = this.context.openFileOutput("usuarios.txt", Context.MODE_PRIVATE);
//            PrintWriter writter = new PrintWriter(fos);
//            writter.println(jsonObj.toString());
//            writter.flush();
//            writter.close();
//            fos.close();


        return json;

    }




}


//=============== FIM DO CÓDIGO ====================

//================== EXTRAS ========================

/*  SALVAR USUÁRIO EM UMA LISTA A PARTIR DE UM JSON*/

/*
try {
          Users usuario = new Users();
              usuario.name = name;
                usuario.user  = user;
                usuario.password = password;
                lista.add(usuario);
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
*/



/*  CARREGAR USUÁRIOS EM UMA LISTA A PARTIR DE UM JSON*/

/*
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

*/


/*  LER USUÁRIOS EM UMA LISTA A PARTIR DE UM JSON*/

/*
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
*/