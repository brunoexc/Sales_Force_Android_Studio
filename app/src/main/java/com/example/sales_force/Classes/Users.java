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

public class Users extends Activity {

    public int id;
    public String name;
    public String user;
    public String password;


    public Users() {}


    public Users(int id, String user, String name, String password) {
        this.id = id;
        this.name = name;
        this.user = user;
        this.password = password;
    }

    @Override

    public String toString() {

        return "Usuario{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


}




