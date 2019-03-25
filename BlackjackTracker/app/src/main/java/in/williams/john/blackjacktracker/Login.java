package in.williams.john.blackjacktracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Login extends AppCompatActivity {
    // source: https://www.youtube.com/watch?v=mPhqDzO7PUU
    EditText mTextUsername;
    EditText mTextPassword;
    Button mButtonLogin;
    TextView mTextViewRegister;
    UserDatabase db;
    UserAccountManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Make instance of db for this page.
        db = new UserDatabase(this);

        mTextUsername = (EditText) findViewById(R.id.edittext_username);
        mTextPassword = (EditText) findViewById(R.id.edittext_password);
        mButtonLogin = (Button) findViewById(R.id.button_login);
        mTextViewRegister = (TextView) findViewById(R.id.textview_register);

        session = new UserAccountManager(getApplicationContext());

        mTextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());


        // On click listener for the register button.
        mTextViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(Login.this, Register.class);
                startActivity(registerIntent);

            }
        });

        // Check if user exists when clicking the login button.
        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View  view) {
                String user = mTextUsername.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();
                // Check user existence.
                Boolean res = db.checkifUserExists(user, pwd);

                if (res == true) {
                    // Print to the screen popup.
                    Toast.makeText(Login.this, "Successfully Logged In", Toast.LENGTH_SHORT).show();

                    session.createUserLoginSession(user, pwd);

                    // Go to home screen after login.
                    Intent goToHome = new Intent(Login.this, DrawerMenu.class);

                    // Flags to avoid errors
                    goToHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    goToHome.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    startActivity(goToHome);
                    finish();
                }
                else {
                    Toast.makeText(Login.this, "Login Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}