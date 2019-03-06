package in.williams.john.blackjacktracker;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// source: https://www.youtube.com/watch?v=35nseBz0CKY
// and Database helper class.
public class UserDatabase extends SQLiteOpenHelper {

    public static final String USER_DATABASE_NAME = "users.db";
    public static final String USER_TABLE_NAME = "user_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "username";
    public static final String COL_3 = "password";

    public UserDatabase (Context context) {
        super (context, USER_DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the table with the following columns.
        db.execSQL("create table " + USER_TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT)" );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the table if it already exists.
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        // Now create the table again.
        onCreate(db);
    }

    // Create a user.
    public long addUser(String user, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Place inputs into content values object.
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", user);
        contentValues.put("password", password);

        // Insert and close db.
        long res = db.insert("user_table", null, contentValues);
        db.close();

        return res;
    }

    // Check if user already exists in db.
    public boolean checkifUserExists(String username, String password) {
        String[] columns = { COL_1 };
        SQLiteDatabase db = getReadableDatabase();
        // Check the username and password.
        String selection = COL_2 + "=?" + " and " + COL_3 + "=?";
        String[] selectionArgs = { username, password };

        // Make cursor for traversing the database rows.
        Cursor cursor = db.query(USER_TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();

        // If there is at least one row matching these records, the user exists.
        if (count > 0) {
            return true;
        }
        // Otherwise, return false.
        else {
            return false;
        }
    }


}
