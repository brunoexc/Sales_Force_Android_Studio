package com.example.sales_force.Controladores;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sales_force.Classes.Clients;
import com.example.sales_force.Classes.Users;
import com.example.sales_force.Database;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

public class ClientController {

    private Context context;
    public ArrayList<Clients> lista_cliente;
    public ArrayList<String> lista_cliente_str;

    Calendar calendario;
    SimpleDateFormat timeNow;

    Database helper;
    SQLiteDatabase db;
    Cursor cursor;

    public ClientController(Context context) {
        this.context = context;
        lista_cliente = new ArrayList<>();
        lista_cliente_str = new ArrayList<>();

        helper = new Database(this.context);
        db = helper.getWritableDatabase();

        calendario = Calendar.getInstance();
        timeNow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        timeNow.setTimeZone(TimeZone.getTimeZone("Brazil/East"));

        carregarLista();
    }

    public void carregarLista() {
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
                    cliente.ultimaAlteracao = cursor.getString(cursor.getColumnIndex("ultimaAlteracao"));
                    lista_cliente.add(cliente);
                    lista_cliente_str.add(cliente.name);
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
        cliente.ultimaAlteracao = timeNow.format(calendario.getTime());

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
            cv.put("ultimaAlteracao", cliente.ultimaAlteracao);

            db.update("Clients", cv, "id = ?", new String[]{String.valueOf(id_client)});

        } finally {
            db.close();
        }
    }


    public void DeleteClient(Clients client){

        SQLiteDatabase db = helper.getWritableDatabase();
        try{
            db.delete("Clients","id = ?", new String[] {String.valueOf(client.id)});
        }finally {
            db.close();
        }
    }


    public String CriarJson(Clients client){

        String json;
        JSONObject obj = null;
        try {
            obj = new JSONObject();
            obj.put("id", client.id);
            obj.put("nome", client.name);
            obj.put("email", client.email);
            obj.put("phone", client.phone);
            obj.put("cpf", client.cpf);
            obj.put("cnpj", client.cnpj);
            obj.put("address", client.address);
            obj.put("address_num", client.address_num);
            obj.put("district", client.district);
            obj.put("uf", client.uf);
            obj.put("city", client.city);
            obj.put("cep", client.cep);
            obj.put("juridica_fisica", client.juridica_fisica);
            obj.put("ultimaAlteracao", client.ultimaAlteracao);
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        json = obj.toString();

        return json;
    }


//    public int getClientByName(String client_name){
//
//        Clients compara;
//
//        for(int i = 0; i < lista_cliente.size(); i++){
//
//            compara = lista_cliente.get(i);
//
//            if (compara.name.equals(client_name)  )
//                return compara.id;
//        }
//        return 0;
//    }



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