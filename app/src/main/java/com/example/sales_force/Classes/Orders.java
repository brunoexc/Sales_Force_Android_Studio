package com.example.sales_force.Classes;

import java.sql.Date;
import java.util.ArrayList;

public class Orders {

    public int id_order, id_client, id_user;
    public float order_total;
    public Date order_date;
    public Orders_Item order_item;
    public Payments order_payment;

    public Orders(){

    }

}
