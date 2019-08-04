package com.example.quickksilent;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Menu extends AppCompatActivity {
    private static final String TAG = "Menu";

    private static final int ERROR_DIALOG_REQUEST = 9001;
    private ListView lv;
    private ArrayAdapter<String> listAdapter ;
    DatabaseHelper db ;
    FloatingActionButton exit;
    Button bt;
    FloatingActionButton createTime;
    @Override
    protected void onCreate(Bundle menuState) {
        // TODO Auto-generated method stub
        super.onCreate(menuState);
        setContentView(R.layout.activity_menu);
        if(isServicesOK()){
            init();
        }

        //create = (Button) findViewById(R.id.bCreate);
        //viewdata = (Button) findViewById(R.id.bView);
        exit =  findViewById(R.id.bExit);
        lv = (ListView)findViewById(R.id.listview);
        db = new DatabaseHelper(Menu.this);
        db.open();
        ArrayList<String> names = new ArrayList<String>();
        names = db.tgetname();
        db.close();
        listAdapter = new ArrayAdapter<String>(Menu.this,android.R.layout.simple_list_item_1,names);
        lv.setAdapter(listAdapter);
        createTime=findViewById(R.id.bcreate);
        createTime.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent openCreateTime = new Intent(Menu.this,CreateTime.class);
                startActivity(openCreateTime);
                finish();
            }
        });
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
                Intent openEditTime = new Intent(Menu.this,EditTime.class);
                openEditTime.putExtras(time);
                startActivity(openEditTime);
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
    private void init(){
        bt = findViewById(R.id.mapb);
        bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent some = new Intent(Menu.this,MapsActivity.class);
                startActivity(some);


            }
        });

    }
    public boolean isServicesOK(){
        Log.d(TAG, "isServicesOK: checking google services version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(Menu.this);

        if(available == ConnectionResult.SUCCESS){
            //everything is fine and the user can make map requests
            Log.d(TAG, "isServicesOK: Google Play Services is working");
            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            //an error occured but we can resolve it
            Log.d(TAG, "isServicesOK: an error occured but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(Menu.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        }else{
            Toast.makeText(this, "You can't make map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }


    public void onBackPressed() {
        // TODO Auto-generated method stub
        finish();

    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
    }
}
