package com.example.sales_force.Controladores;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.sales_force.Classes.Products;
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

public class ProductController {

    private Context context;
    public ArrayList<Products> lista_produto;
    Database helper;
    SQLiteDatabase db;

    public ProductController(Context context) {

        this.context = context;
        lista_produto = new ArrayList<Products>();
        carregarLista();
        helper = new Database(this.context);
        db = helper.getWritableDatabase();
    }

    private void carregarLista() {
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
                product_comparativo.um = c.getString("unidade medida");
                product_comparativo.qtd_estoque = c.getString("qtd em estoque");
                product_comparativo.custo = c.getString("custo");
                product_comparativo.preco_venda = c.getString("preço venda");
                product_comparativo.codigo_barras = c.getInt("código barras");
                product_comparativo.status = c.getString("status produto");
                lista_produto.add(product_comparativo);
            }

        } catch ( IOException| JSONException e) {
            Log.e("ERRO", e.getMessage());
        }
    }


    public void SaveProduct (String name, String um, String qtd_estoque, String status, String custo, String preco, int codigo_barras){

        Products produto = new Products();
        produto.name = name;
        produto.um = um;
        produto.qtd_estoque = qtd_estoque;
        produto.status = status;
        produto.custo = custo;
        produto.preco_venda = preco;
        produto.codigo_barras = codigo_barras;
        lista_produto.add(produto);

        SQLiteDatabase db = helper.getWritableDatabase();
        try{
            ContentValues cv = new ContentValues();
            cv.put("name", produto.name);
            cv.put("um", produto.um);
            cv.put("qtd_estoque", produto.qtd_estoque);
            cv.put("status", produto.status);
            cv.put("custo", produto.custo);
            cv.put("preco_venda", produto.preco_venda);
            cv.put("codigo_barras", produto.codigo_barras);
            long id = db.insert("Products", null, cv);
            produto.id = (int) id;
        }finally {
            db.close();
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
                product_comparativo.codigo_barras = c.getInt("código de barras");
            }

        } catch ( IOException| JSONException e) {
            Log.e("ERRO", e.getMessage());
        }
    }

}


//EXTRAS

/*  SALVAR PRODUTO EM UMA LISTA A PARTIR DE UM JSON*/


//        try {
//            JSONObject jsonObj = new JSONObject();
//            JSONArray dados = new JSONArray();
//
//            for (Products p : lista_produto) {
//            JSONObject obj = new JSONObject();
//
//            obj.put("id", p.id);
//            obj.put("nome", p.name);
//            obj.put("unidade medida", p.um);
//            obj.put("qtd em estoque", p.qtd_estoque);
//            obj.put("custo", p.custo);
//            obj.put("preço venda", p.preco_venda);
//            obj.put("código de barras", p.codigo_barras);
//            obj.put("status", p.status);
//            dados.put(obj);
//            }
//
//            jsonObj.put("produtos",dados);
//
//            FileOutputStream fos = this.context.openFileOutput("produtos.txt", Context.MODE_APPEND);
//            PrintWriter writter = new PrintWriter(fos);
//            writter.println(jsonObj.toString());
//            writter.flush();
//            writter.close();
//            fos.close();
//
//        } catch (IOException | JSONException e) {
//            Log.e("ERRO", e.getMessage());
//        }
