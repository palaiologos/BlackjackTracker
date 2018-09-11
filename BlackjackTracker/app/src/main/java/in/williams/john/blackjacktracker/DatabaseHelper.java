package in.williams.john.blackjacktracker;

// For using sqlite database and handling the creation, updating
// and display of session data objects.
// source: https://www.youtube.com/watch?v=cp2rL3sAFmI&list=PLS1QulWo1RIaRdy16cOzBO5Jr6kEagA07

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
        // Create the database and table.
        SQLiteDatabase db = this.getWritableDatabase();
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
}
