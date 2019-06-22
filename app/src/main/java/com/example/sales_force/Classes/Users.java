package com.example.sales_force.Classes;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileOutputStream;

import java.io.IOException;
import java.io.PrintWriter;

import androidx.appcompat.app.AppCompatActivity;

public class Users  {

    public int id;
    public String name;
    public String user;
    public String password;
    public String ultimaAlteracao;

    public Users() {}


    public Users(int id, String name, String user, String password) {
        this.id = id;
        this.name = name;
        this.user = user;
        this.password = password;
    }

    @Override
    public String toString() {

        String print = "id - " + String.valueOf(id) + " - " + String.valueOf(name);
        return print;
    }


}




