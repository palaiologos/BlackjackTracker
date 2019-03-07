package in.williams.john.blackjacktracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static java.lang.Thread.sleep;

public class SplashScreen extends AppCompatActivity {

    UserAccountManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Account instance.
        session = new UserAccountManager(getApplicationContext());

        // Thread for running splash screen before main drawer menu.
        // source: https://www.youtube.com/watch?v=ND6a4V-xdjI&t=3s
        Thread myThread = new Thread() {
            @Override
            public void run() {
                // Tries to sleep for 3 seconds.
                try {
                    sleep(3000);

                    // Starts the splash screen then goes to login screen if not logged in.
                    // Check user login.
                    // If user not logged in, redirect to the login page.
                    if (session.checkLogin() ) {
                        finish();
                    }
                    // Otherwise, go straight to home page.
                    else {
                        Intent goToHome = new Intent(getApplicationContext(), DrawerMenu.class);
                        startActivity(goToHome);
                        finish();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };

        myThread.start();

    }
}
