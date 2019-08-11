package com.example.quickksilent;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper {

    public static final String KEY_TROWID = "_id";
    public static final String KEY_TNAME = "time_name";
    public static final String KEY_TSTART = "time_start";
    public static final String KEY_TEND = "time_end";
    public static final String KEY_TREP = "time_repeat";

    public static final String KEY_ROWID = "_id";
    public static final String KEY_NAME = "location_name";
    public static final String KEY_LOC = "location_geopoint";
    public static final String KEY_RAD = "location_radius";

    //location table

    public static final String ROW_ID = "id";
    public static final String TITLE = "locname";

    public static final String LAT = "lat";
    public static final String LNG = "lng";
    public static final String RADIUS = "rad";
    public static final String ACTIVE = "active";

    private static final String DB_TABLE = "locaa";

    //end table

    private static final String DATABASE_NAME = "autosilen";
    private static final String DATABASE_TTABLE = "time";
    //Niche waala used hai
    private static final String DATABASE_TABLE = "location";
    private static final int DATABASE_VERSION = 1;

    public DbHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;

    public static class DbHelper extends SQLiteOpenHelper {

        public DbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO Auto-generated method stub
            db.execSQL("CREATE TABLE "+DATABASE_TTABLE+" ("+
                    KEY_TROWID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    KEY_TNAME+" TEXT NOT NULL, "+
                    KEY_TSTART+" INTEGER, "+
                    KEY_TEND+" INTEGER, "+
                    KEY_TREP+" TEXT);"
            );

            db.execSQL("CREATE TABLE "+DATABASE_TABLE+" ("+
                    KEY_ROWID+" INTEGER, "+
                    KEY_NAME+" TEXT NOT NULL, "+
                    KEY_LOC+" TEXT NOT NULL, "+
                    KEY_RAD+" TEXT NOT NULL);"
            );
            //Remove this. It has never worked
            String query = "CREATE TABLE IF NOT EXISTS " +
                    DB_TABLE +
                    "(" +
                    "'id' integer," +
                    "'locname' text," +
                    "'lat' text," +
                    "'lng' text," +
                    "'rad' integer," +
                    "'active' integer);";
            db.execSQL(query);
            /**
             * Last table is for location. Not fuckin tested?
             *
             */
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
            // TODO Auto-generated method stub
            db.execSQL("DROP TABLE IF EXISTS "+DATABASE_TTABLE);
            db.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE);
            db.execSQL("DROP TABLE IF EXISTS "+ DB_TABLE);
            onCreate(db);
        }

    }

    public DatabaseHelper(Context c){
        ourContext = c;
    }

    public DatabaseHelper open() throws SQLException{
        ourHelper = new DbHelper(ourContext);
        ourDatabase = ourHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        ourHelper.close();
    }

    public long tcreateEntry(String sqlname, int sqlstart, int sqlend, String sqlrepeat) {
        // TODO Auto-generated method stub
        ContentValues cv = new ContentValues();
        cv.put(KEY_TNAME, sqlname);
        cv.put(KEY_TSTART, sqlstart);
        cv.put(KEY_TEND, sqlend);
        cv.put(KEY_TREP, sqlrepeat);
        return ourDatabase.insert(DATABASE_TTABLE, null, cv);
    }
    public long insertCord(int id,String name, String lat, String lon,int radius,int active) {
        // TODO Auto-generated method stub
        ContentValues cv = new ContentValues();
        cv.put(ROW_ID, id);
        cv.put(TITLE, name);
        cv.put(LAT, lat);
        cv.put(LNG,lon);
        cv.put(RADIUS,radius);
        //Change to take input
        cv.put(ACTIVE,active);

        return ourDatabase.insert(DB_TABLE, null, cv);
    }

    public ArrayList<String> tgetname(){

        String[] columns = new String[]{KEY_TROWID,KEY_TNAME,KEY_TSTART,KEY_TEND,KEY_TREP};
        Cursor c = ourDatabase.query(DATABASE_TTABLE, columns, null, null, null, null, null);

        int iName = c.getColumnIndex(KEY_TNAME);
        ArrayList<String> name = new ArrayList<String>();

        for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            name.add(c.getString(iName));
        }
        c.close();
        return name;
    }

    public String tgetdata(String name){

        String query=" SELECT " + KEY_TSTART+" , "+KEY_TEND +" , "+KEY_TREP +" FROM " + DATABASE_TTABLE + " WHERE " + KEY_TNAME +" LIKE '" + name +"';";
        Cursor c = ourDatabase.rawQuery(query, null);
        String result = "";
        int start = c.getColumnIndex(KEY_TSTART);
        int end = c.getColumnIndex(KEY_TEND);
        int rep = c.getColumnIndex(KEY_TREP);
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
        {
            result = c.getInt(start) + "/" + c.getInt(end)+ "/" + c.getString(rep);
        }
        c.close();
        return 	result;
    }

    public int tgetid(String name){

        String query=" SELECT " + KEY_TROWID+" FROM " + DATABASE_TTABLE + " WHERE " + KEY_TNAME +" LIKE '" + name +"';";
        Cursor c = ourDatabase.rawQuery(query, null);
        int result = 0;
        int rowid = c.getColumnIndex(KEY_TROWID);
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            result = c.getInt(rowid);
        }
        c.close();
        return 	result;
    }

    public String tgetrep(int rowid){

        String query=" SELECT " + KEY_TREP+" FROM " + DATABASE_TTABLE + " WHERE " + KEY_TROWID +" =" + rowid +";";
        Cursor c = ourDatabase.rawQuery(query, null);
        String result = "";
        int rep = c.getColumnIndex(KEY_TREP);
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            result = c.getString(rep);
        }
        c.close();
        return 	result;
    }

    public void tupdateEntry(String name,String newname,int newstart, int newend, String newrep){

        ContentValues cvupdate = new ContentValues();
        cvupdate.put(KEY_TNAME, newname);
        cvupdate.put(KEY_TSTART, newstart);
        cvupdate.put(KEY_TEND, newend);
        cvupdate.put(KEY_TREP, newrep);
        ourDatabase.update(DATABASE_TTABLE, cvupdate, KEY_TNAME+" LIKE '"+name+"'", null);

    }

    public void tdeleteEntry(String name){
        ourDatabase.delete(DATABASE_TTABLE, KEY_TNAME+" LIKE '"+name+"'", null);
    }

    public long createEntry(String sqlname, String sqllocation, String sqlradius) {
        // TODO Auto-generated method stub
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME, sqlname);
        cv.put(KEY_LOC, sqllocation);
        cv.put(KEY_RAD, sqlradius);
        return ourDatabase.insert(DATABASE_TABLE, null, cv);
    }

    public ArrayList<String> getname() {

        String[] columns = new String[]{KEY_ROWID,KEY_NAME,KEY_LOC,KEY_RAD};
        Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);

        int iName = c.getColumnIndex(KEY_NAME);
        ArrayList<String> name = new ArrayList<String>();

        for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            name.add(c.getString(iName));
        }
        c.close();
        return name;
    }

    public ArrayList<String> getlocrad(){

        String[] columns = new String[]{KEY_ROWID,KEY_NAME,KEY_LOC,KEY_RAD};
        Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
        int iLoc = c.getColumnIndex(KEY_LOC);
        int iRad = c.getColumnIndex(KEY_RAD);
        ArrayList<String> locrad = new ArrayList<String>();
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            locrad.add(c.getString(iLoc)+"/"+c.getString(iRad));
        }
        c.close();
        return locrad;
    }

    public String getdata(String name){

        String query=" SELECT " + KEY_LOC+" , "+KEY_RAD +" FROM " + DATABASE_TABLE + " WHERE " + KEY_NAME +" LIKE '" + name +"';";
        Cursor c = ourDatabase.rawQuery(query, null);
        String result = "";
        int loc = c.getColumnIndex(KEY_LOC);
        int rad = c.getColumnIndex(KEY_RAD);
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
        {
            result = c.getString(loc) + "/" + c.getString(rad);
        }
        c.close();
        return 	result;
    }

    public void updateEntry(String name,String newname,String newlocation, String newradius){

        ContentValues cvupdate = new ContentValues();
        cvupdate.put(KEY_NAME, newname);
        cvupdate.put(KEY_LOC, newlocation);
        cvupdate.put(KEY_RAD, newradius);
        ourDatabase.update(DATABASE_TABLE, cvupdate, KEY_NAME+" LIKE '"+name+"'", null);

    }

    public void deleteEntry(String name){
        ourDatabase.delete(DATABASE_TABLE, KEY_NAME+" LIKE '"+name+"'", null);
    }
    /**
     * Queries for the location table shit from here on
     *
     */

    public long insert(ContentValues contentValues){
        long rowID = ourDatabase.insert(DB_TABLE, null, contentValues);
        return rowID;
    }

    /**
     * Right here you will get the name of the location latitude of the location
     * It is the only parameter that will be returned.
     * get the latitude solely
     * Just test the demo on a working activity bar.
     *public ArrayList<String> getname() {
     *
     *         String[] columns = new String[]{KEY_ROWID,KEY_NAME,KEY_LOC,KEY_RAD};
     *         Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
     *
     *         int iName = c.getColumnIndex(KEY_NAME);
     *         ArrayList<String> name = new ArrayList<String>();
     *
     *         for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
     *             name.add(c.getString(iName));
     *         }
     *         c.close();
     *         return name;
     *     }
     *     public static final String ROW_ID = "_id";
     *     public static final String TITLE = "locname";
     *
     *     public static final String LAT = "lat";
     *     public static final String LNG = "lng";
     *     public static final String RADIUS = "rad";
     *     public static final String ACTIVE = "active";
     */
    public ArrayList<String> getLatitude(){

        String[] columns = new String[]{ROW_ID,TITLE,LAT,LNG,RADIUS,ACTIVE};
        Cursor c = ourDatabase.query(DB_TABLE,columns,null,null,null,null,null);
        int iLat = c.getColumnIndex(LAT);
        ArrayList<String> locat = new ArrayList<String>();
        for(c.moveToFirst();!c.isAfterLast();c.moveToFirst()){
            locat.add(c.getString(iLat));
        }
        c.close();
        return locat;

    }

}
