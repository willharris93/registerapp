package edu.uark.uarkregisterapp;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import org.apache.commons.lang3.StringUtils;
import edu.uark.uarkregisterapp.models.api.Employee;
import edu.uark.uarkregisterapp.models.api.enums.EmployeeApiRequestStatus;
import edu.uark.uarkregisterapp.models.api.services.EmployeeService;
import edu.uark.uarkregisterapp.models.transition.EmployeeTransition;

public class NewUserActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

    }

    public void saveButtonOnClick(View view) {
        if (!this.validateInput()) {
            return;
        }

        this.savingEmployeeAlert = new AlertDialog.Builder(this).
                setMessage(R.string.alert_dialog_employee_save).
                create();

        (new NewUserActivity.SaveActivityTask_E(
                this,
                this.getEmployeePasswordEditText().getText().toString(),
                this.getEmployeeFirstNameEditText().getText().toString(),
                this.getEmployeeLastNameEditText().getText().toString()
        )).execute();
    }

    private EditText getEmployeePasswordEditText() {
        return (EditText) this.findViewById(R.id.password);
    }

    private EditText getEmployeeFirstNameEditText() {
        return (EditText) this.findViewById(R.id.firstname);
    }

    private EditText getEmployeeLastNameEditText() {
        return (EditText) this.findViewById(R.id.lastname);
    }

    private class SaveActivityTask_E extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... params) {
            Employee employee = (new EmployeeService()).putEmployee(
                    (new Employee()).
                            setId(employeeTransition.getId()).
                            setPassword(this.password).
                            setFirstName(this.firstName).
                            setLastName(this.lastName)
            );

            if (employee.getApiRequestStatus() == EmployeeApiRequestStatus.OK) {
                employeeTransition.setPassword(this.password);
            }

            return (employee.getApiRequestStatus() == EmployeeApiRequestStatus.OK);
        }

        @Override
        protected void onPostExecute(Boolean successfulSave) {
            String message;

            savingEmployeeAlert.dismiss();

            if (successfulSave) {
                message = getString(R.string.alert_dialog_employee_save_success);
            } else {
                message = getString(R.string.alert_dialog_employee_save_failure);
            }

            new AlertDialog.Builder(this.activity).
                    setMessage(message).
                    setPositiveButton(
                            R.string.button_dismiss,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                }
                            }
                    ).
                    create().
                    show();
        }

        private String password;
        private String firstName;
        private String lastName;
        private NewUserActivity activity;

        private SaveActivityTask_E(NewUserActivity activity, String password, String firstName, String lastName) {
            this.activity = activity;
            this.password = password;
            this.firstName = firstName;
            this.lastName = lastName;
        }
    }

    private boolean validateInput() {
        boolean inputIsValid = true;
        String validationMessage = StringUtils.EMPTY;

        if (!inputIsValid) {
            new AlertDialog.Builder(this).
                    setMessage(validationMessage).
                    setPositiveButton(
                            R.string.button_dismiss,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                }
                            }
                    ).
                    create().
                    show();
        }

        return inputIsValid;
    }

    private AlertDialog savingEmployeeAlert;
    private EmployeeTransition employeeTransition;
}
