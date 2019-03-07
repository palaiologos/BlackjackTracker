package in.williams.john.blackjacktracker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

public class UserAccountManager {

    // Shared preferences reference.
    SharedPreferences pref;

    // Editor reference for shared preferences.
    SharedPreferences.Editor editor;

    // Context.
    Context _context;

    // Shared pref mode.
    int PRIVATE_MODE = 0;

    // Shared pref file name.
    private static final String PREFER_NAME = "AndroidExmaplePref";

    // All shared preferences keys.
    private static final String IS_USER_LOGIN = "IsUserLoggedIn";

    // User name.
    public static final String KEY_NAME = "name";

    // Password.
    public static final String KEY_PASSWORD = "password";

    // Constructor
    public UserAccountManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    // Create a login session.
    public void createUserLoginSession(String name, String password) {
        // Store login value as true.
        editor.putBoolean(IS_USER_LOGIN, true);

        // Storing name in pref.
        editor.putString(KEY_NAME, name);

        // Storing password in pref.
        editor.putString(KEY_PASSWORD, password);

        // Commit changes.
        editor.commit();

    }


    // Check login status. If not logged in, redirect to login page.
    // Otherwise, go to home page as usual.
    public boolean checkLogin() {

        // Check login status.
        if (!this.isUserLoggedIn() ) {

            // Not logged in, so redirect to login, return true.
            Intent goToLogin = new Intent(_context, Login.class);

            // Close activities from stack and add new start flag.
            goToLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            goToLogin.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            _context.startActivity(goToLogin);

            return true;
        }
        else {
            return false;
        }
    }


    // Get stored session data.
    public HashMap<String, String> getUserDetails() {

        // Use hashmap to store credentials.
        HashMap<String, String> user = new HashMap<>();

        // Username.
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));

        // Password.
        user.put(KEY_PASSWORD, pref.getString(KEY_PASSWORD, null));

        // Return user.
        return user;
    }


    // Clear session details.
    public void logoutUser() {

        // Clearing all user data from shared preferences.
        editor.clear();
        editor.commit();

        // After logout, redirect user to login.
        Intent goToLogin = new Intent(_context, Login.class);

        // Start the activity.
        _context.startActivity(goToLogin);

    }


    // Checks if the user is actually logged in.
    public boolean isUserLoggedIn() {
        return pref.getBoolean(IS_USER_LOGIN, false);
    }

}