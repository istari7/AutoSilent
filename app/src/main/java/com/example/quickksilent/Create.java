package com.example.quickksilent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Create extends AppCompatActivity {

    Button createTime,createGps;
    @Override
    protected void onCreate(Bundle createState) {
        // TODO Auto-generated method stub
        super.onCreate(createState);
        setContentView(R.layout.activity_create);
        createTime=(Button)findViewById(R.id.bTime);
        createTime.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent openCreateTime = new Intent(Create.this,CreateTime.class);
                startActivity(openCreateTime);
                finish();
            }
        });


    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        Intent openMenu = new Intent(Create.this,Menu.class);
        startActivity(openMenu);
        finish();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
    }
}
