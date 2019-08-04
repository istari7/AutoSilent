package com.example.quickksilent;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;

public class CreateTime extends AppCompatActivity {

    FloatingActionButton save,cancel;
    EditText name;
    TimePicker startTime,endTime;
    CheckBox sun,mon,tue,wed,thu,fri,sat;
    ArrayList<String> checkName;
    Calendar cal,ecal;

    @Override
    protected void onCreate(Bundle createTimeState) {
        // TODO Auto-generated method stub
        super.onCreate(createTimeState);
        setContentView(R.layout.activity_create_time);
        name = (EditText) findViewById(R.id.eName);
        startTime = (TimePicker) findViewById(R.id.tpStart);
        endTime = (TimePicker) findViewById(R.id.tpEnd);
        sun = (CheckBox) findViewById(R.id.cbSun);
        mon = (CheckBox) findViewById(R.id.cbMon);
        tue = (CheckBox) findViewById(R.id.cbTue);
        wed = (CheckBox) findViewById(R.id.cbWed);
        thu = (CheckBox) findViewById(R.id.cbThu);
        fri = (CheckBox) findViewById(R.id.cbFri);
        sat = (CheckBox) findViewById(R.id.cbSat);
        save =  findViewById(R.id.bSave);
        cancel =  findViewById(R.id.beCancel);

        DatabaseHelper entry = new DatabaseHelper(CreateTime.this);
        entry.open();
        checkName = new ArrayList<String>();
        checkName = entry.tgetname();
        entry.close();

        save.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                String sqlname = name.getText().toString().trim();
                Integer sqlstart = (startTime.getCurrentHour() * 100) + (startTime.getCurrentMinute());
                Integer sqlend = (endTime.getCurrentHour() * 100) + (endTime.getCurrentMinute());
                String sqlrep = "0";
                if(sun.isChecked()){
                    sqlrep = sqlrep + "1";
                }
                if(mon.isChecked()){
                    sqlrep = sqlrep + "2";
                }
                if(tue.isChecked()){
                    sqlrep = sqlrep + "3";
                }
                if(wed.isChecked()){
                    sqlrep = sqlrep + "4";
                }
                if(thu.isChecked()){
                    sqlrep = sqlrep + "5";
                }
                if(fri.isChecked()){
                    sqlrep = sqlrep + "6";
                }
                if(sat.isChecked()){
                    sqlrep = sqlrep + "7";
                }
                sqlrep.trim();
                if(!sqlname.equals("")){
                    if(!checkName.contains(sqlname)){
                        if(sqlstart != sqlend){
                            DatabaseHelper entry = new DatabaseHelper(CreateTime.this);
                            entry.open();
                            entry.tcreateEntry(sqlname, sqlstart, sqlend, sqlrep);
                            int rowid = entry.tgetid(sqlname);
                            entry.close();

                            cal = Calendar.getInstance();
                            int day,month,year;
                            day=cal.get(Calendar.DAY_OF_MONTH);
                            month=cal.get(Calendar.MONTH);
                            year=cal.get(Calendar.YEAR);
                            cal.set(Calendar.DAY_OF_MONTH, day);
                            cal.set(Calendar.MONTH,month);
                            cal.set(Calendar.YEAR,year);
                            cal.set(Calendar.HOUR_OF_DAY,startTime.getCurrentHour());
                            cal.set(Calendar.MINUTE,startTime.getCurrentMinute());
                            Intent alarm = new Intent(CreateTime.this,AlarmReceiver.class);
                            Bundle falarm = new Bundle();
                            falarm.putBoolean("silent", true);
                            falarm.putInt("rowid",rowid);
                            alarm.putExtras(falarm);
                            PendingIntent pendingIntent = PendingIntent.getBroadcast(CreateTime.this.getApplicationContext(), rowid+startTime.getCurrentHour()+startTime.getCurrentMinute(), alarm, PendingIntent.FLAG_UPDATE_CURRENT|  Intent.FILL_IN_DATA);
                            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                            if(sqlrep.equals("0")){
                                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(),0,pendingIntent);
                            }
                            else{
                                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(),24*60*60*1000,pendingIntent);
                            }

                            ecal = Calendar.getInstance();
                            int eday,emonth,eyear;
                            eday=ecal.get(Calendar.DAY_OF_MONTH);
                            emonth=ecal.get(Calendar.MONTH);
                            eyear=ecal.get(Calendar.YEAR);
                            ecal.set(Calendar.DAY_OF_MONTH, eday);
                            ecal.set(Calendar.MONTH,emonth);
                            ecal.set(Calendar.YEAR,eyear);
                            ecal.set(Calendar.HOUR_OF_DAY,endTime.getCurrentHour());
                            ecal.set(Calendar.MINUTE,endTime.getCurrentMinute());
                            Intent ealarm = new Intent(CreateTime.this,AlarmReceiver.class);
                            Bundle fealarm = new Bundle();
                            fealarm.putBoolean("silent", false);
                            fealarm.putInt("rowid",rowid);
                            ealarm.putExtras(fealarm);
                            PendingIntent ependingIntent = PendingIntent.getBroadcast(CreateTime.this.getApplicationContext(), rowid+endTime.getCurrentHour()+endTime.getCurrentMinute(), ealarm, PendingIntent.FLAG_UPDATE_CURRENT|  Intent.FILL_IN_DATA);
                            AlarmManager ealarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                            if(sqlrep.equals("0")){
                                ealarmManager.setRepeating(AlarmManager.RTC_WAKEUP,ecal.getTimeInMillis(),0,ependingIntent);
                            }
                            else{
                                ealarmManager.setRepeating(AlarmManager.RTC_WAKEUP,ecal.getTimeInMillis(),24*60*60*1000,ependingIntent);
                            }
                            Toast.makeText(CreateTime.this,"CREATED SUCCESSFULLY", Toast.LENGTH_LONG).show();
                            Intent openCreate=new Intent(CreateTime.this,Menu.class);
                            startActivity(openCreate);
                            finish();
                        }
                        else{
                            Toast.makeText(CreateTime.this,"START TIME AND END TIME ARE SAME", Toast.LENGTH_LONG).show();
                        }
                    }
                    else{
                        Toast.makeText(CreateTime.this,"NAME ALREADY EXISTS", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(CreateTime.this,"SOME FIELDS ARE EMPTY", Toast.LENGTH_LONG).show();
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent openCreate = new Intent(CreateTime.this,Menu.class);
                startActivity(openCreate);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
    }

}
