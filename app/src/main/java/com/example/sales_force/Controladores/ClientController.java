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
import java.util.ArrayList;

public class ClientController {

    private Context context;
    private ArrayList<Clients> lista;

    public ClientController(Context context) {

        this.context = context;

        lista = new ArrayList<Clients>();
        carregarLista();
    }


    private void carregarLista() {
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
                Clients client_comparativo = new Clients();
                client_comparativo.id = c.getInt("id");
                client_comparativo.name = c.getString("nome");
                client_comparativo.email = c.getString("email");
                client_comparativo.phone = c.getString("telefone");
                client_comparativo.cpf = c.getString("cpf");
                client_comparativo.cnpj = c.getString("cnpj");
                client_comparativo.address = c.getString("address");
                client_comparativo.district = c.getString("district");
                client_comparativo.uf = c.getString("uf");
                client_comparativo.city = c.getString("city");
                client_comparativo.cep = c.getString("cep");
                client_comparativo.juridica_fisica = c.getString("tipo");
                lista.add(client_comparativo);
            }

        } catch ( IOException| JSONException e) {
            Log.e("ERRO", e.getMessage());
        }
    }


    public void RegisterClient (int id, String name, String email, String phone, String cpf, String cnpj, String address, String district, String uf, String city, String cep, String juridica_fisica){

        Clients cliente = new Clients();
        cliente.id = id;
        cliente.name = name;
        cliente.email  = email;
        cliente.phone = phone;
        cliente.phone = cpf;
        cliente.phone = cnpj;
        cliente.phone = address;
        cliente.phone = district;
        cliente.phone = uf;
        cliente.phone = city;
        cliente.phone = cep;
        cliente.phone = juridica_fisica;
        lista.add(cliente);

        try {
            JSONObject jsonObj = new JSONObject();
            JSONArray dados = new JSONArray();

            for (Clients c : lista) {

            JSONObject obj = new JSONObject();

            obj.put("id", c.id);
            obj.put("nome", c.name);
            obj.put("email", c.email);
            obj.put("telefone", c.phone);
            obj.put("cpf", c.cpf);
            obj.put("cnpj", c.cnpj);
            obj.put("address", c.address);
            obj.put("district", c.district);
            obj.put("uf", c.uf);
            obj.put("city", c.city);
            obj.put("cep", c.cep);
            obj.put("tipo", c.juridica_fisica);
            dados.put(obj);
            }

            jsonObj.put("clientes:",dados);

            FileOutputStream fos = this.context.openFileOutput("clientes.txt", Context.MODE_PRIVATE);
            PrintWriter writter = new PrintWriter(fos);
            writter.println(jsonObj.toString());
            writter.flush();
            writter.close();
            fos.close();

        } catch (IOException | JSONException e) {
            Log.e("ERRO", e.getMessage());
        }

    }

    public void ReadClientOnFile(){

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
                Clients client_comparativo = new Clients();
                client_comparativo.id = c.getInt("id");
                client_comparativo.name = c.getString("nome");
                client_comparativo.email = c.getString("email");
                client_comparativo.phone = c.getString("telefone");
                client_comparativo.cpf = c.getString("cpf");
                client_comparativo.cnpj = c.getString("cnpj");
                client_comparativo.address = c.getString("address");
                client_comparativo.district = c.getString("district");
                client_comparativo.uf = c.getString("uf");
                client_comparativo.city = c.getString("city");
                client_comparativo.cep = c.getString("cep");
                client_comparativo.juridica_fisica = c.getString("tipo");
            }

        } catch ( IOException| JSONException e) {
            Log.e("ERRO", e.getMessage());
        }
    }

}
