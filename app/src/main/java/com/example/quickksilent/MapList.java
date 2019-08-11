package com.example.quickksilent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MapList extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String TAG ="MapList";
    private static final int ERROR_DIALOG_REQ = 9001;
    private ListView lv;
    private Button mapb,listb;
    private ArrayAdapter<String> listAdapter;
    private ArrayList<String> al;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_list);
        mapb = findViewById(R.id.gotomap);
        listb = findViewById(R.id.listdisp);
        lv = findViewById(R.id.listview);
       //chrome al = new ArrayList<String>();

        listb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db.collection("locations")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        Toast.makeText(getApplicationContext(),document.getId(),Toast.LENGTH_SHORT).show();

                                    //    al.add(document.getId().toString());
                                        Toast.makeText(getApplicationContext(),document.get("latitude").toString(),Toast.LENGTH_SHORT).show();
                                        Log.d(TAG, document.getId() + " => " + document.getData());
                                    }
                                    //listAdapter = new ArrayAdapter<String>(MapList.this,android.R.layout.simple_list_item_2,al);
                              //      lv.setAdapter(listAdapter);
                                } else {
                                    Log.w(TAG, "Error getting documents.", task.getException());
                                }
                            }
                        });



            }


        });
        //Code starts from here for displaying lists

         //Ends for displaying the list. Code for transfering from one part of the app
        //to another for entering shit
        mapb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MapList.this,MapsActivity.class);
                startActivity(it);
            }
        });


/**
 * public class Menu extends AppCompatActivity {
 *
 *
 *     @Override
 *     protected void onCreate(Bundle menuState) {
 *         // TODO Auto-generated method stub
 *         super.onCreate(menuState);
 *         setContentView(R.layout.activity_menu);
 *         if(isServicesOK()){
 *             init();
 *         }
 *
 *         exit =  findViewById(R.id.bExit);
 *         lv = (ListView)findViewById(R.id.listview);
 *         db = new DatabaseHelper(Menu.this);
 *         db.open();
 *         ArrayList<String> names = new ArrayList<String>();
 *         names = db.tgetname();
 *         db.close();
 *         listAdapter = new ArrayAdapter<String>(Menu.this,android.R.layout.simple_list_item_1,names);
 *         lv.setAdapter(listAdapter);
 *         createTime=findViewById(R.id.bcreate);
 *         createTime.setOnClickListener(new View.OnClickListener() {
 *
 *             public void onClick(View v) {
 *                 // TODO Auto-generated method stub
 *                 Intent openCreateTime = new Intent(Menu.this,CreateTime.class);
 *                 startActivity(openCreateTime);
 *                 finish();
 *             }
 *         });
 *         lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
 *
 *             public void onItemClick(AdapterView<?> parent, View item, int position,long id) {
 *                 // TODO Auto-generated method stub
 *                 String name = listAdapter.getItem(position);
 *                 db.open();
 *                 String data = db.tgetdata(name);
 *                 db.close();
 *                 String[] temp = data.split("/");
 *                 Bundle time = new Bundle();
 *                 time.putString("name",name);
 *                 time.putInt("start",Integer.parseInt(temp[0]));
 *                 time.putInt("end", Integer.parseInt(temp[1]));
 *                 time.putString("rep", temp[2]);
 *                 Intent openEditTime = new Intent(Menu.this,EditTime.class);
 *                 openEditTime.putExtras(time);
 *                 startActivity(openEditTime);
 *                 finish();
 *             }
 *         });
 *
 *         exit.setOnClickListener(new View.OnClickListener() {
 *
 *             public void onClick(View v) {
 *                 // TODO Auto-generated method stub
 *                 finish();
 *             }
 *         });
 *     }
 *     private void init(){
 *         bt = findViewById(R.id.map);
 *         bt.setOnClickListener(new View.OnClickListener() {
 *
 *             @Override
 *             public void onClick(View view) {
 *                 Intent some = new Intent(Menu.this,MapList.class);
 *                 startActivity(some);
 *
 *
 *             }
 *         });
 *
 *     }
 *
 *
 *
 *     public void onBackPressed() {
 *         // TODO Auto-generated method stub
 *         finish();
 *
 *     }
 *
 *
 * }
 */
    }
}
