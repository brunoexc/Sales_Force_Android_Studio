package com.example.sales_force.Controladores;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sales_force.Classes.Orders;
import com.example.sales_force.Classes.Orders_Item;
import com.example.sales_force.Classes.Products;
import com.example.sales_force.Database;

import java.util.ArrayList;

public class OrderItemController {

    public Context context;
    public ProductController productController;
    public Products product;
    public OrderController orderController;
    public Orders_Item order_item;

    public ArrayList<Orders_Item> lista_itens;

    Database helper;
    SQLiteDatabase db;
    Cursor cursor;


    public OrderItemController(Context context) {
        this.context = context;
//        lista = new ArrayList<>();

        productController = new ProductController(this.context);
        orderController = new OrderController(this.context);
//
//        helper = new Database(this.context);
//        db = helper.getWritableDatabase();
//
//        carregarLista();
    }

    public void addItem(int id_product, int qtd_items, int order_id){

        product = productController.lista_produto.get(id_product);

        order_item = new Orders_Item();

        order_item.id_order = order_id;

        if(order_item.id > 1){
            order_item.id_order = orderController.lista_pedidos.size() + 1;
        }else{
            order_item.id_order = order_id;
        }

        order_item.id_product = product.id;
        order_item.qtd_items = qtd_items;
        order_item.item_total = qtd_items * Integer.parseInt(product.preco_venda);
        lista_itens.add(order_item);

        SQLiteDatabase db = helper.getWritableDatabase();
        try{
            ContentValues cv = new ContentValues();
            cv.put("id_order", order_item.qtd_items);
            cv.put("id_product", order_item.id_product);
            cv.put("qtd_items", order_item.qtd_items);
            cv.put("item_total", order_item.item_total);
            long id = db.insert("OrderItem", null, cv);
            order_item.id = (int) id;
        }finally {
            db.close();
        }
    }

    public void SaveItemsOnOrder(ArrayList<Orders_Item> lista_itens){

        SQLiteDatabase db = helper.getWritableDatabase();
        try{
            ContentValues cv = new ContentValues();
            cv.put("id_order", order_item.qtd_items);
            cv.put("id_product", order_item.id_product);
            cv.put("qtd_items", order_item.qtd_items);
            cv.put("item_total", order_item.item_total);
            long id = db.insert("OrderItem", null, cv);
            order_item.id = (int) id;
        }finally {
            db.close();
        }



    }





    public void getLastOrderIndex(){


    }

}
