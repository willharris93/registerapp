package edu.uark.uarkregisterapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Button;
import android.widget.EditText;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import org.apache.commons.lang3.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;

import edu.uark.uarkregisterapp.models.api.Employee;
import edu.uark.uarkregisterapp.models.api.Product;
import edu.uark.uarkregisterapp.models.api.services.EmployeeService;
import edu.uark.uarkregisterapp.models.api.services.ProductService;

public class LoginActivity extends AppCompatActivity {
    PopupWindow user_alert;
    LinearLayout layout;
    Button login_submit;
    EditText userid,password;

    ArrayList<Employee> employees = new ArrayList<Employee>();

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_submit = (Button)findViewById(R.id.log_in);
        userid = (EditText)findViewById(R.id.userid);
        password = (EditText)findViewById(R.id.password);
        layout = new LinearLayout(this);
        user_alert = new PopupWindow(this);

        // Make sure that this list of employees actually pulls data from the server. As of now it just initializes it.
    }

    /** Called when the activity is about to become visible. */
    @Override
    protected void onStart() {
        super.onStart();
    }

    /** Called when the activity has become visible.*/
    @Override
    protected void onResume() {
        super.onResume();

        (new RetrieveEmployeesTask()).execute(); //This might be a problem

        if (employees.isEmpty()){
            AlertDialog AlertDialog = new AlertDialog.Builder(LoginActivity.this).create();
            AlertDialog.setTitle("No existing users found.");
            AlertDialog.setMessage("Please create a user to continue.");
            AlertDialog.setButton(DialogInterface.BUTTON_POSITIVE,"OK",new DialogInterface.OnClickListener(){

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent myIntent = new Intent(LoginActivity.this, NewUserActivity.class);
                    LoginActivity.this.startActivity(myIntent);
                    finish();
                }
            });
            AlertDialog.show();
        }
    }

    /** Called when another activity is taking focus. */
    @Override
    protected void onPause() {
        super.onPause();
    }

    /** Called when the activity is no longer visible. */
    @Override
    protected void onStop() {
        super.onStop();
    }

    /** Called just before the activity is destroyed. */
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private class RetrieveEmployeesTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            employees.clear();
            employees.addAll(
                    (new EmployeeService()).getEmployees()
            );

            return null;
        }
    }
}
