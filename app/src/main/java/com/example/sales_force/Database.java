package com.example.sales_force;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Database extends SQLiteOpenHelper {


    public Database(Context context){
        super(context,"database.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.i("TAG","onCreate Helper");
        db.execSQL(
                "CREATE TABLE Users ("+
                        "id    INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"+
                        "name    TEXT NOT NULL,"+
                        "user    TEXT,"+
                        "password    NUMERIC"+
                        ");"
        );

        db.execSQL(
                "CREATE TABLE Clients ("+
                        "id    INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"+
                        "name    TEXT NOT NULL,"+
                        "email    TEXT,"+
                        "phone    TEXT,"+
                        "cpf    TEXT,"+
                        "cnpj    TEXT,"+
                        "address    TEXT,"+
                        "address_num    TEXT,"+
                        "district    TEXT,"+
                        "uf    TEXT,"+
                        "city    TEXT,"+
                        "tipo    TEXT,"+
                        "cep    TEXT"+
                        ");"
        );

        db.execSQL(
                "CREATE TABLE Products ("+
                        "id    INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"+
                        "name    TEXT NOT NULL,"+
                        "um    TEXT,"+
                        "qtd_estoque    TEXT,"+
                        "status    TEXT,"+
                        "custo    TEXT,"+
                        "preco_venda    TEXT,"+
                        "codigo_barras    TEXT"+
                        ");"
        );


        db.execSQL(
                "CREATE TABLE Orders ("+
                        "id    INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"+
                        "id_client    INTEGER NOT NULL,"+
                        "id_user      INTEGER NOT NULL,"+
                        "order_total  REAL NOT NULL,"+
                        "order_date    TEXT"+
                        ");"
        );

        db.execSQL(
                "CREATE TABLE OrderItem ("+
                        "id    INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"+
                        "id_order    INTEGER NOT NULL,"+
                        "id_product  INTEGER NOT NULL,"+
                        "qtd_items   INTEGER NOT NULL,"+
                        "item_total  REAL NOT NULL"+
                        ");"
        );

        db.execSQL(
                "CREATE TABLE Payment ("+
                        "id    INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"+
                        "id_order    TEXT NOT NULL,"+
                        "payment_total    REAL"+
                        ");"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

//        Log.i("TAG","oldVersion:"+oldVersion+" newVersion:"+newVersion);
//        if (newVersion > 1){
//            db.execSQL( "ALTER TABLE Clients add address_num TEXT;");
//        }
    }
}
