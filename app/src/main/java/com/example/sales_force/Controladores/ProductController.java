package com.example.sales_force.Controladores;

import android.content.Context;
import android.util.Log;
import android.widget.EditText;

import com.example.sales_force.Classes.Clients;
import com.example.sales_force.Classes.Products;
import com.example.sales_force.Classes.Users;
import com.example.sales_force.RegisterProduct;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class ProductController {

    Products global_product = new Products();
    private Context context;

    public ProductController(Context context) {
        this.context = context;
    }


    public void RegisterProduct (int id, String name, String um, String qtd_estoque, String status, String custo, String preco, int codigo_barras){

        try {
            JSONObject jsonObj = new JSONObject();
            JSONArray dados = new JSONArray();

            JSONObject obj = new JSONObject();

            obj.put("id", id);
            obj.put("nome", name);
            obj.put("unidade de medida", um);
            obj.put("qtd estoque", qtd_estoque);
            obj.put("custo", custo);
            obj.put("preço", preco);
            obj.put("código de barras", codigo_barras);
            obj.put("status", status);

            dados.put(obj);

            jsonObj.put("produtos:",dados);

            FileOutputStream fos = this.context.openFileOutput("produtos.txt", Context.MODE_APPEND);
            PrintWriter writter = new PrintWriter(fos);
            writter.println(jsonObj.toString());
            writter.flush();
            writter.close();
            fos.close();

        } catch (IOException | JSONException e) {
            Log.e("ERRO", e.getMessage());
        }

    }



    public void ReadProductsOnFile(){

        try {
            FileInputStream fis = this.context.openFileInput("produtos.txt");

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
            JSONArray dados = jsonObj.getJSONArray("produtos");

            for (int i = 0; i < dados.length(); i++) {
                JSONObject c = dados.getJSONObject(i);
                Products product_comparativo = new Products();
                product_comparativo.id = c.getInt("id");
                product_comparativo.name = c.getString("nome");
                product_comparativo.um = c.getString("unidade de medida");
                product_comparativo.status = c.getString("status");
                product_comparativo.custo = c.getString("custo");
                product_comparativo.preco_venda = c.getString("preço");
                product_comparativo.codigo_barras = c.getString("código de barras");

            }

        } catch ( IOException| JSONException e) {
            Log.e("ERRO", e.getMessage());
        }


    }








}
