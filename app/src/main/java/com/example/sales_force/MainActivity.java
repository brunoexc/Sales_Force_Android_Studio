package com.example.sales_force;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    public void OnClickbuttonLogin (View view){

        Intent call_activity_user = new Intent(this, CreateUserActivity.class);
        startActivity(call_activity_user);

    }

}




