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
                "CREATE TABLE clientes ("+
                        "codigo    INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"+
                        "nome    TEXT NOT NULL,"+
                        "documento    TEXT,"+
                        "data_nasc    NUMERIC,"+
                        "email    TEXT"+
                        ");"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.i("TAG","oldVersion:"+oldVersion+" newVersion:"+newVersion);
        if (newVersion > 1){
            db.execSQL( "ALTER TABLE clientes add telefone TEXT;");
        }
    }
}
