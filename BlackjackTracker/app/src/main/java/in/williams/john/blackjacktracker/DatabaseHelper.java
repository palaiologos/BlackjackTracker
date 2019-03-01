package in.williams.john.blackjacktracker;

// For using sqlite database and handling the creation, updating
// and display of session data objects.
// source: https://www.youtube.com/watch?v=cp2rL3sAFmI&list=PLS1QulWo1RIaRdy16cOzBO5Jr6kEagA07

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Var declarations for database.
    // Name of db.
    public static final String DATABASE_NAME = "sessions.db";
    // Name of data table.
    public static final String TABLE_NAME = "session_table";

    // Data columns.
    public static final String ID = "id";
    public static final String DATE = "date";
    public static final String LOCATION = "location";
    public static final String TIME_SPENT = "time_spent";
    public static final String NUM_SHOES = "num_shoes";
    public static final String BUY_IN = "buy_in";
    public static final String CASH_OUT = "cash_out";
    public static final String NET_CHANGE = "net_change";


    // Constructor method.
    public DatabaseHelper(Context context) {
        // Context, name, factory, version.
        super(context, DATABASE_NAME, null, 1);
    }

    // On creation of class object.
    // source: https://www.youtube.com/watch?v=p8TaTgr4uKM&index=2&list=PLS1QulWo1RIaRdy16cOzBO5Jr6kEagA07
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the table with the following columns.
        db.execSQL("create table " + TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, date TEXT, location TEXT, time_spent INTEGER, num_shoes INTEGER, buy_in INTEGER, cash_out INTEGER, net_change INTEGER)" );

    }

    // On upgrade of class object.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the table if it already exists.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // Now create the table again.
        onCreate(db);

    }

    public boolean insertData(String date, String location, int time_spent, int num_shoes, int buy_in, int cash_out) {
        // Create the database and table.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        // Insert data with put() method for each column.
        contentValues.put(DATE, date);
        contentValues.put(LOCATION, location);
        contentValues.put(TIME_SPENT, time_spent);
        contentValues.put(NUM_SHOES, num_shoes);
        contentValues.put(BUY_IN, buy_in);
        contentValues.put(CASH_OUT, cash_out);

        // Calculate the net change before inserting value.
        int net_change = (cash_out - buy_in);
        contentValues.put(NET_CHANGE, net_change);

        // Insert the values to our table from contentValues instance.
        // If failed, it will return -1.
        long result = db.insert(TABLE_NAME, null, contentValues);

        // If it failed, return false.
        if (result == -1) {
            return false;
        }
        // Else, return true.
        return true;

    }

    // Return all the data rows for viewing all data.
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Res is a cursor object that is the query of getting all data from the table.
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    // Only get certain columns for the Sessions view.
    public Cursor getSessionsViewData() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Res is a cursor object that is the query of getting all data from the table.
        Cursor res = db.rawQuery("select id, date, location, time_spent, num_shoes, net_change from " + TABLE_NAME, null);
        return res;
    }

    // Update data using form on sessions.
    public boolean updateData(String id, String date, String location, int time_spent, int num_shoes, int buy_in, int cash_out) {
        // Create instances of database and content values.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        // Insert data with put() method for each column.
        contentValues.put(ID, id);
        contentValues.put(DATE, date);
        contentValues.put(LOCATION, location);
        contentValues.put(TIME_SPENT, time_spent);
        contentValues.put(NUM_SHOES, num_shoes);
        contentValues.put(BUY_IN, buy_in);
        contentValues.put(CASH_OUT, cash_out);

        // Calculate the net change before inserting value.
        int net_change = (cash_out - buy_in);
        contentValues.put(NET_CHANGE, net_change);

        // Update the records in the db based on ID. Also pass in a string array 'id'.
        // source: https://www.youtube.com/watch?v=PA4A9IesyCg&index=5&list=PLS1QulWo1RIaRdy16cOzBO5Jr6kEagA07
        db.update(TABLE_NAME, contentValues, "ID = ?", new String[] {id});
        // Return true if data is updated successfully.
        return true;
    }

    // Delete data rows.
    public Integer deleteData(String id) {
        // Create db object instance.
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete the data based on id, same as in updateData() function.
        // Function will return an int upon finishing.
        return db.delete(TABLE_NAME, "ID = ?", new String[] {id});
    }
}
