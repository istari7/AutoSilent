package com.example.quickksilent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ViewStoredData extends AppCompatActivity {
    Button viewTime,viewGps;

    @Override
    protected void onCreate(Bundle viewState) {
        // TODO Auto-generated method stub
        super.onCreate(viewState);
        setContentView(R.layout.activity_view_stored_data);
        viewTime = (Button)findViewById(R.id.bvTime);
        viewTime.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent openViewTime = new Intent(ViewStoredData.this,TimeView.class);
                startActivity(openViewTime);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        Intent openMenu = new Intent(ViewStoredData.this,Menu.class);
        startActivity(openMenu);
        finish();
    }

    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
    }
}
