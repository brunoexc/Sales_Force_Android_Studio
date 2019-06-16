package com.example.sales_force.Classes;

import java.sql.Date;
import java.util.ArrayList;

public class Orders {

    public int id, id_client, id_user;
    public float order_total;
    public String order_date; //Corrigir depois
    public String order_item;
    public ArrayList<String> order_items_list;
    public String order_payment; //Corrigir depois

    public Orders(){

    }

}
