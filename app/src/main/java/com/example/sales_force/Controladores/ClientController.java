package com.example.sales_force.Controladores;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.sales_force.Classes.Clients;
import com.example.sales_force.Classes.Users;
import com.example.sales_force.Database;

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

public class ClientController {

    private Context context;
    public ArrayList<Clients> lista_cliente;

    Database helper;
    SQLiteDatabase db;
    Cursor cursor;

    public ClientController(Context context) {
        this.context = context;
        lista_cliente = new ArrayList<>();

        helper = new Database(this.context);
        db = helper.getWritableDatabase();
        carregarLista();
    }

    private void carregarLista() {
        try {

            SQLiteDatabase db = helper.getReadableDatabase();
            try {
                cursor = db.rawQuery("select * from clients", null);

                lista_cliente.clear();
                while (cursor.moveToNext()) {
                    Clients cliente = new Clients();
                    cliente.id = cursor.getInt(cursor.getColumnIndex("id"));
                    cliente.name = cursor.getString(cursor.getColumnIndex("name"));
                    cliente.email = cursor.getString(cursor.getColumnIndex("email"));
                    cliente.phone = cursor.getString(cursor.getColumnIndex("phone"));
                    cliente.cpf = cursor.getString(cursor.getColumnIndex("cpf"));
                    cliente.cnpj = cursor.getString(cursor.getColumnIndex("cnpj"));
                    cliente.address = cursor.getString(cursor.getColumnIndex("address"));
                    cliente.address_num = cursor.getString(cursor.getColumnIndex("address_num"));
                    cliente.district = cursor.getString(cursor.getColumnIndex("district"));
                    cliente.uf = cursor.getString(cursor.getColumnIndex("uf"));
                    cliente.city = cursor.getString(cursor.getColumnIndex("city"));
                    cliente.cep = cursor.getString(cursor.getColumnIndex("cep"));
                    cliente.juridica_fisica = cursor.getString(cursor.getColumnIndex("tipo"));
                    lista_cliente.add(cliente);
                }

            } finally {
                cursor.close();
            }
        } finally {
            db.close();
        }
    }


    public void SaveClient(String name, String email, String phone, String cpf, String cnpj, String address, String input_address_num, String district, String uf, String city, String cep, String juridica_fisica) {

        Clients cliente = new Clients();
        cliente.name = name;
        cliente.email = email;
        cliente.phone = phone;
        cliente.cpf = cpf;
        cliente.cnpj = cnpj;
        cliente.address = address;
        cliente.address_num = input_address_num;
        cliente.district = district;
        cliente.uf = uf;
        cliente.city = city;
        cliente.cep = cep;
        cliente.juridica_fisica = juridica_fisica;
        lista_cliente.add(cliente);

        SQLiteDatabase db = helper.getWritableDatabase();
        try {
            ContentValues cv = new ContentValues();
            cv.put("name", cliente.name);
            cv.put("email", cliente.email);
            cv.put("phone", cliente.phone);
            cv.put("cpf", cliente.cpf);
            cv.put("cnpj", cliente.cnpj);
            cv.put("address", cliente.address);
            cv.put("address_num", cliente.address_num);
            cv.put("district", cliente.district);
            cv.put("uf", cliente.uf);
            cv.put("city", cliente.city);
            cv.put("cep", cliente.cep);
            cv.put("tipo", cliente.juridica_fisica);
            long id = db.insert("Clients", null, cv);
            cliente.id = (int) id;
        } finally {
            db.close();
        }
    }

    public void UpdateClient (Integer id_client, String name, String email, String phone, String cpf, String cnpj, String address, String input_address_num, String district, String uf, String city, String cep, String juridica_fisica) {

        Clients cliente = new Clients();
        cliente.name = name;
        cliente.email = email;
        cliente.phone = phone;
        cliente.cpf = cpf;
        cliente.cnpj = cnpj;
        cliente.address = address;
        cliente.address_num = input_address_num;
        cliente.district = district;
        cliente.uf = uf;
        cliente.city = city;
        cliente.cep = cep;
        cliente.juridica_fisica = juridica_fisica;

        SQLiteDatabase db = helper.getWritableDatabase();
        try {
            ContentValues cv = new ContentValues();
            cv.put("name", cliente.name);
            cv.put("email", cliente.email);
            cv.put("phone", cliente.phone);
            cv.put("cpf", cliente.cpf);
            cv.put("cnpj", cliente.cnpj);
            cv.put("address", cliente.address);
            cv.put("address_num", cliente.address_num);
            cv.put("district", cliente.district);
            cv.put("uf", cliente.uf);
            cv.put("city", cliente.city);
            cv.put("cep", cliente.cep);
            cv.put("tipo", cliente.juridica_fisica);

            db.update("Clients", cv, "id = ?", new String[]{String.valueOf(id_client)});

        } finally {
            db.close();
        }
    }


}

//
//        try {
//            FileInputStream fis = this.context.openFileInput("clientes.txt");
//
//            BufferedReader reader = new BufferedReader( new InputStreamReader(fis));
//            StringBuilder sb = new StringBuilder();
//            String linha;
//
//            do{
//                linha = reader.readLine();
//                if (sb.length() != 0)
//                    sb.append('\n');
//                sb.append(linha);
//            }while(linha != null);
//
//            reader.close();
//            fis.close();
//
//            //Log.i("TAG",sb.toString());
//            String jsonStr = sb.toString();
//            JSONObject jsonObj = new JSONObject(jsonStr);
//            JSONArray dados = jsonObj.getJSONArray("clientes");
//
//            for (int i = 0; i < dados.length(); i++) {
//                JSONObject c = dados.getJSONObject(i);
//                Clients client_comparativo = new Clients();
//                client_comparativo.id = c.getInt("id");
//                client_comparativo.name = c.getString("nome");
//                client_comparativo.email = c.getString("email");
//                client_comparativo.phone = c.getString("telefone");
//                client_comparativo.cpf = c.getString("cpf");
//                client_comparativo.cnpj = c.getString("cnpj");
//                client_comparativo.address = c.getString("address");
//                client_comparativo.district = c.getString("district");
//                client_comparativo.uf = c.getString("uf");
//                client_comparativo.city = c.getString("city");
//                client_comparativo.cep = c.getString("cep");
//                client_comparativo.juridica_fisica = c.getString("tipo");
//            }
//
//        } catch ( IOException| JSONException e) {
//            Log.e("ERRO", e.getMessage());
//        }
//    }

//
//    public void ReadClient() {
//
//
//        SQLiteDatabase db = helper.getReadableDatabase();
//        try {
//            cursor = db.rawQuery("select * from clients", null);
//
//            lista_cliente.clear();
//            while (cursor.moveToNext()) {
//                Clients cliente = new Clients();
//                cliente.id = cursor.getInt(cursor.getColumnIndex("id"));
//                cliente.name = cursor.getString(cursor.getColumnIndex("name"));
//                cliente.email = cursor.getString(cursor.getColumnIndex("email"));
//                cliente.phone = cursor.getString(cursor.getColumnIndex("phone"));
//                cliente.cpf = cursor.getString(cursor.getColumnIndex("cpf"));
//                cliente.cnpj = cursor.getString(cursor.getColumnIndex("cnpj"));
//                cliente.address = cursor.getString(cursor.getColumnIndex("address"));
//                cliente.district = cursor.getString(cursor.getColumnIndex("district"));
//                cliente.uf = cursor.getString(cursor.getColumnIndex("uf"));
//                cliente.city = cursor.getString(cursor.getColumnIndex("city"));
//                cliente.cep = cursor.getString(cursor.getColumnIndex("cep"));
//                cliente.juridica_fisica = cursor.getString(cursor.getColumnIndex("tipo"));
//                lista_cliente.add(cliente);
//            }
//            cursor.close();
//        } finally {
//            db.close();
//        }
//    }



//EXTRAS

/*  SALVAR CLIENTE EM UMA LISTA A PARTIR DE UM JSON*/

//        try {
//            JSONObject jsonObj = new JSONObject();
//            JSONArray dados = new JSONArray();
//
//            for (Clients c : lista_cliente) {
//              JSONObject obj = new JSONObject();
//              obj.put("id", c.id);
//              obj.put("nome", c.name);
//              obj.put("email", c.email);
//              obj.put("telefone", c.phone);
//              obj.put("cpf", c.cpf);
//              obj.put("cnpj", c.cnpj);
//              obj.put("address", c.address);
//              obj.put("district", c.district);
//              obj.put("uf", c.uf);
//              obj.put("city", c.city);
//              obj.put("cep", c.cep);
//              obj.put("tipo", c.juridica_fisica);
//              dados.put(obj);
//            }
//            jsonObj.put("clientes",dados);
//
//            FileOutputStream fos = this.context.openFileOutput("clientes.txt", Context.MODE_PRIVATE);
//            PrintWriter writter = new PrintWriter(fos);
//            writter.println(jsonObj.toString());
//            writter.flush();
//            writter.close();
//            fos.close();
//
//        } catch (IOException | JSONException e) {
//            Log.e("ERRO", e.getMessage());
//        }