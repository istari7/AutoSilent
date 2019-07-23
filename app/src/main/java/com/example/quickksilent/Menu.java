package com.example.quickksilent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Menu extends AppCompatActivity {

    Button create,viewdata,exit;
    @Override
    protected void onCreate(Bundle menuState) {
        // TODO Auto-generated method stub
        super.onCreate(menuState);
        setContentView(R.layout.activity_menu);
        create = (Button) findViewById(R.id.bCreate);
        viewdata = (Button) findViewById(R.id.bView);
        exit = (Button) findViewById(R.id.bExit);

        create.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent openCreate = new Intent(Menu.this,Create.class);
                startActivity(openCreate);
                finish();
            }
        });
        viewdata.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent openView = new Intent(Menu.this,ViewStoredData.class);
                startActivity(openView);
                finish();
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();
            }
        });
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
