package com.example.sales_force.Classes;

import java.sql.Date;
import java.util.ArrayList;

public class Orders {

    public int id, id_client, id_user;
    public float order_total;
    public String order_date; //Corrigir depois
    public ArrayList<Orders_Item> order_items_list;
    public ArrayList<Payments> order_payment; //Corrigir depois

    public Orders(){

    }

}
