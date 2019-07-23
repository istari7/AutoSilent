package com.example.quickksilent;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class TimeView extends AppCompatActivity {


    private ListView lv;
    private ArrayAdapter<String> listAdapter ;
    DatabaseHelper db ;

    @Override
    protected void onCreate(Bundle viewTimeState) {
        // TODO Auto-generated method stub
        super.onCreate(viewTimeState);
        setContentView(R.layout.activity_time_view);
        lv = (ListView)findViewById(R.id.lvDisplay11);
        db = new DatabaseHelper(TimeView.this);
        db.open();
        ArrayList<String> names = new ArrayList<String>();
        names = db.tgetname();
        db.close();
        listAdapter = new ArrayAdapter<String>(TimeView.this,android.R.layout.simple_list_item_1,names);
        lv.setAdapter(listAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View item, int position,long id) {
                // TODO Auto-generated method stub
                String name = listAdapter.getItem(position);
                db.open();
                String data = db.tgetdata(name);
                db.close();
                String[] temp = data.split("/");
                Bundle time = new Bundle();
                time.putString("name",name);
                time.putInt("start",Integer.parseInt(temp[0]));
                time.putInt("end", Integer.parseInt(temp[1]));
                time.putString("rep", temp[2]);
                Intent openEditTime = new Intent(TimeView.this,EditTime.class);
                openEditTime.putExtras(time);
                startActivity(openEditTime);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        Intent openView = new Intent(TimeView.this,ViewStoredData.class);
        startActivity(openView);
        finish();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
    }
}
