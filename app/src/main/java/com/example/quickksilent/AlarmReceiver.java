package com.example.quickksilent;

import java.util.Calendar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver{


    @Override
    public void onReceive(Context context, Intent arg1) {
        // TODO Auto-generated method stub

        Bundle b = arg1.getExtras();
        boolean flag = b.getBoolean("silent");
        int rowid = b.getInt("rowid");
        DatabaseHelper entry = new DatabaseHelper(context);
        entry.open();
        String rep = entry.tgetrep(rowid);
        entry.close();
        if(flag){
            if(rep.contains(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)+"")||(rep.equals("0"))) {
                Silent s = new Silent(context);
                Toast.makeText(context, "SILENT MODE", Toast.LENGTH_LONG).show();
            }
        }
        else {
            if(rep.contains(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)+"")||(rep.equals("0"))) {
                Normal n = new Normal(context);
                Toast.makeText(context, "NORMAL MODE", Toast.LENGTH_LONG).show();
            }
        }
    }
}