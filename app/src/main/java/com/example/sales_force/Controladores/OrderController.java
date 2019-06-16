package com.example.sales_force.Controladores;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sales_force.Classes.Orders;
import com.example.sales_force.Classes.Orders_Item;
import com.example.sales_force.Classes.Products;
import com.example.sales_force.Database;

import java.util.ArrayList;
import java.util.Arrays;

public class OrderController {

    private Context context;
    public ProductController productController;
    public Products products;
    public Orders order;
    public OrderItemController orderItemController;
    public ClientController clientController;

    public ArrayList<Orders> lista_pedidos;
    public String[] lis_ped;
    public ArrayList<String> array_item_pedidos;


    Database helper;
    SQLiteDatabase db;
    Cursor cursor;

    public static String strSeparator = ",";


    public OrderController(Context context) {

        this.context = context;
        orderItemController = new OrderItemController(this.context);


//        lista = new ArrayList<>();
//
//        helper = new Database(this.context);
//        db = helper.getWritableDatabase();
//
        carregarLista();
    }

    public void carregarLista() {

        SQLiteDatabase db = helper.getReadableDatabase();
        try {
            cursor = db.rawQuery("select * from products", null);

            lista_pedidos.clear();
            while (cursor.moveToNext()) {

                Orders pedido = new Orders();
                pedido.id = cursor.getInt(cursor.getColumnIndex("id"));
                pedido.id_client = cursor.getInt(cursor.getColumnIndex("id_client"));
                pedido.id_user = cursor.getInt(cursor.getColumnIndex("id_user"));
                pedido.order_item = cursor.getString(cursor.getColumnIndex("order_item"));
                pedido.order_items_list = convertStringToArray(pedido.order_item);
                pedido.order_total = cursor.getFloat(cursor.getColumnIndex("order_total"));

                lista_pedidos.add(pedido);
            }
            cursor.close();
        } finally {
            db.close();
        }
    }

    public static String convertArrayToString(String[] order_array){
        String str = "";
        for (int i = 0; i < order_array.length; i++) {
            str += order_array[i];

            // IF para tratar o ultimo elemento da string, não colocar a virgula
            if(i < order_array.length - 1){
                str += strSeparator;
            }
        }
        return str;
    }
    public ArrayList convertStringToArray(String str){

        lis_ped = str.split(strSeparator);
        array_item_pedidos = new ArrayList<>(lis_ped.length);

        for (String item : lis_ped)
            array_item_pedidos.add(item);

        return array_item_pedidos;
    }


    public void CriarPrimeiroPedido (int id_client, int id_user, int order_total, String data, ArrayList items_list, String payment){

        order = new Orders();
        order.id = 1;
        order.id_client = id_client;
        order.id_user = id_user;
        order.order_total =  order_total;
        order.order_date = data;

        //Trata minha lista de itens que estão associados ao pedido
        order.order_items_list = items_list;
        order.order_items_list.trimToSize();
        order.order_item = convertArrayToString(order.order_items_list.toArray(new String[0]));

        lista_pedidos.add(order);

    }


}
