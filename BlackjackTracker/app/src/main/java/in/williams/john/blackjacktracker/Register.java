package in.williams.john.blackjacktracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    UserDatabase db;

    // source: https://www.youtube.com/watch?v=mPhqDzO7PUU
    EditText mTextUsername;
    EditText mTextPassword;
    EditText mTextCnfPassword;
    Button mButtonRegister;
    TextView mTextViewLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Make an instance of the db for this page.
        db = new UserDatabase(this);

        // Associate variables with the views from the layout page.
        mTextUsername = (EditText) findViewById(R.id.edittext_username);
        mTextPassword = (EditText) findViewById(R.id.edittext_password);
        mTextCnfPassword = (EditText) findViewById(R.id.edittext_password_confirmation);
        mButtonRegister = (Button) findViewById(R.id.button_register);
        mTextViewLogin = (TextView) findViewById(R.id.textview_login);

        // On click listener for the register button.
        mTextViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LoginIntent = new Intent(Register.this, Login.class);
                startActivity(LoginIntent);

            }
        });

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = mTextUsername.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();
                String cnf_pwd = mTextCnfPassword.getText().toString().trim();

                // Check if passwords match in the two separate fields.
                if (pwd.equals(cnf_pwd) ) {
                    // Add user to db.
                    long val = db.addUser(user, pwd);

                    // Check if successfully registered.
                    if (val > 0)
                    {
                        Toast.makeText(Register.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(Register.this, "Registration Error", Toast.LENGTH_SHORT).show();
                    }


                    Toast.makeText(Register.this, "Successfully Registered", Toast.LENGTH_SHORT).show();

                    // Once registered, move to the login page.
                    Intent moveToLogin = new Intent(Register.this, Login.class);
                    startActivity(moveToLogin);

                }
                else {
                    Toast.makeText(Register.this, "Password Mismatch", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
