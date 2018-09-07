package in.williams.john.blackjacktracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Thread for running splash screen before main drawer menu.
        // source: https://www.youtube.com/watch?v=ND6a4V-xdjI&t=3s
        Thread myThread = new Thread() {
            @Override
            public void run() {
                // Tries to sleep for 3 seconds.
                try {
                    sleep(3000);
                    // Starts the activity, then finishes.
                    Intent intent = new Intent(getApplicationContext(), DrawerMenu.class);
                    startActivity(intent);
                    finish();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };

        myThread.start();
    }
}
