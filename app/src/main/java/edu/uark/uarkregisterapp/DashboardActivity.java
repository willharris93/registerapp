package edu.uark.uarkregisterapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewUserActivity extends AppCompatActivity {
    Button create_user;
    EditText firstname,lastname,password;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        create_user = (Button)findViewById(R.id.create);
        firstname = (EditText)findViewById(R.id.firstname);
        lastname = (EditText)findViewById(R.id.lastname);
        password = (EditText)findViewById(R.id.password);

        create_user.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Interface with the server, add user, etc.
            }
        });
    }
}
