package edu.uark.uarkregisterapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DashboardActivity extends AppCompatActivity {
    Button start_transaction,sr_product,sr_cashier,create_employee,logout;
    TextView welcome;
    String user;
;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        start_transaction = (Button)findViewById(R.id.start_transaction);
        sr_product = (Button)findViewById(R.id.sr_product);
        sr_cashier = (Button)findViewById(R.id.sr_cashier);
        create_employee = (Button)findViewById(R.id.create_employee);
        logout = (Button)findViewById(R.id.logout);
        welcome = (TextView)findViewById(R.id.text_view_welcome);
        user = "{USER}";
        //Set user to appropriate name when logged in

        welcome.setText("Welcome " + user + "!");

        start_transaction.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // do stuff
            }
        });
        sr_product.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // do stuff
            }
        });
        sr_cashier.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // do stuff
            }
        });
        create_employee.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // do stuff
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // do stuff
            }
        });
    }
}
