package com.example.quickksilent;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    SharedPreferences mySharedPreferences;
    SharedPreferences.Editor editor;
    Boolean check;

    @Override
    protected void onCreate(Bundle splashState) {
        // TODO Auto-generated method stub
        super.onCreate(splashState);
        setContentView(R.layout.activity_main);
        Intent openMenu = new Intent(MainActivity.this, Menu.class);
        startActivity(openMenu);

    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
    }
}
