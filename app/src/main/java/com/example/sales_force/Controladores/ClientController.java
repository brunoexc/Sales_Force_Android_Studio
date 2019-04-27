package com.example.sales_force.Controladores;

import android.content.Context;
import android.util.Log;

import com.example.sales_force.Classes.Clients;
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

public class ClientController {

    Clients client_global = new Clients();
    private Context context;

    public ClientController(Context context) {
        this.context = context;
    }


    public void RegisterClient (int id, String name, String email, String phone, String cpf, String cnpj, String address, String district, String uf, String city, String cep, String juridica_fisica){

        try {
            JSONObject jsonObj = new JSONObject();
            JSONArray dados = new JSONArray();

            JSONObject obj = new JSONObject();

            obj.put("id", id);
            obj.put("nome", name);
            obj.put("email", email);
            obj.put("telefone", phone);
            obj.put("cpf", cpf);
            obj.put("cnpj", cnpj);
            obj.put("address", address);
            obj.put("district", district);
            obj.put("uf", uf);
            obj.put("city", city);
            obj.put("cep", cep);
            obj.put("tipo", juridica_fisica);

            dados.put(obj);


            jsonObj.put("clientes:",dados);

            FileOutputStream fos = this.context.openFileOutput("clientes.txt", Context.MODE_APPEND);
            PrintWriter writter = new PrintWriter(fos);
            writter.println(jsonObj.toString());
            writter.flush();
            writter.close();
            fos.close();

        } catch (IOException | JSONException e) {
            Log.e("ERRO", e.getMessage());
        }

    }


//    public void SaveClientOnFile(){
//
//        try {
//            JSONObject jsonObj = new JSONObject();
//            JSONArray dados = new JSONArray();
//
//            JSONObject obj = new JSONObject();
//
//            obj.put("id", this.client_global.id);
//            obj.put("nome", this.client_global.name);
//            obj.put("email", this.client_global.email);
//            obj.put("telefone", this.client_global.phone);
//            obj.put("cpf", this.client_global.cpf);
//            obj.put("cnpj", this.client_global.cnpj);
//            obj.put("address", this.client_global.address);
//            obj.put("district", this.client_global.district);
//            obj.put("uf", this.client_global.uf);
//            obj.put("city", this.client_global.city);
//            obj.put("cep", this.client_global.cep);
//            obj.put("fisica ou juridica", this.client_global.juridica_fisica);
//
//            dados.put(obj);
//
//
//            jsonObj.put("clientes:",dados);
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
//
//    }


    public void ReadClientPersonalData(){

        try {
            FileInputStream fis = this.context.openFileInput("clientes.txt");

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
            JSONArray dados = jsonObj.getJSONArray("clientes");

            for (int i = 0; i < dados.length(); i++) {
                JSONObject c = dados.getJSONObject(i);
                Users user_comparativo = new Users();
                user_comparativo.id = c.getInt("id");
                user_comparativo.user = c.getString("user");
                user_comparativo.password = c.getString("senha");
            }

        } catch ( IOException| JSONException e) {
            Log.e("ERRO", e.getMessage());
        }


    }


}


//
//                this.client_global.id = id;
//                this.client_global.name = name;
//                this.client_global.email = email;
//                this.client_global.phone = phone;
//                this.client_global.cpf = cpf;
//                this.client_global.cnpj = cnpj;
//                this.client_global.address = address;
//                this.client_global.district = district;
//                this.client_global.uf = uf;
//                this.client_global.city = city;
//                this.client_global.cep = cep;
//                this.client_global.juridica_fisica = juridica_fisica;

