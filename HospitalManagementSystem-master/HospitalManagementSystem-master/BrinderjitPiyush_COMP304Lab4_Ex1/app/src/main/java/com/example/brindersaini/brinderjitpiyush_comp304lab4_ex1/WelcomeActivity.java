package com.example.brindersaini.brinderjitpiyush_comp304lab4_ex1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.FileNotFoundException;

import brinderjit.Piyush.dto.Doctor;
import brinderjit.Piyush.dto.Nurse;
import brinderjit.Piyush.dto.Patient;
import brinderjit.Piyush.dto.Test;
import brinderjitPiyush.DatabaseHandler.DatabaseHandler;

public class WelcomeActivity extends AppCompatActivity {
    DatabaseHandler db;
public WelcomeActivity()
{
     db= new DatabaseHandler(this);
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

       try {
         //  Doctor doctor = new Doctor("brinderjit1", "Brinderjit", "Singh", "Neurology", "waheguru");
          // db.deleteDoctor(doctor);
         //   db.addDoctor(doctor);
          // Nurse nurse = new Nurse("piyush1", "Piyush", "Sharma", "Neurology", "waheguru");
           // db.addNurse(nurse);

        }
        catch (Exception e)
        {

        }

    }
    public void onLoginClick(View view){
        EditText usernameEdt=(EditText) findViewById(R.id.editUserName);
        EditText passwordEdt=(EditText) findViewById(R.id.editPassword);
        Spinner typeSpin=(Spinner)findViewById(R.id.TypeDropDown);
        String username=usernameEdt.getText().toString().trim();
        String password=passwordEdt.getText().toString().trim();
        String type=typeSpin.getSelectedItem().toString().trim();



        try {


            if (usernameEdt.length() == 0) {
                usernameEdt.requestFocus();
                usernameEdt.setError("FIELD CANNOT BE EMPTY");

            } else if (passwordEdt.length() == 0) {
                passwordEdt.requestFocus();
                passwordEdt.setError("FIELD CANNOT BE EMPTY");
            } else if (type.equals("Select Login type")) {
                typeSpin.requestFocus();
                Toast toast = Toast.makeText(getApplicationContext(),"Please select a login type", Toast.LENGTH_LONG);
                toast.show();

            }
            else {
                if(type.equals("Doctor")) {

                    Doctor doctor = db.getDoctor(username);
                    if (doctor.getFirstName().equals(null) || doctor.getFirstName().equals("")) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Incorrect username", Toast.LENGTH_LONG);
                        toast.show();
                    } else if (doctor.getFirstName()!= null && doctor.getFirstName() != "" && doctor.getPassword().equals(password)) {
                        SharedPreferences prefs = this.getSharedPreferences(
                                "com.example.brindersaini", Context.MODE_PRIVATE);
                        prefs.edit().putString("UserName",username).commit();
                        prefs.edit().putString("Type",type).commit();
                        Intent intent = new Intent(this, PatientInfoActivity.class);
                        startActivity(intent);
                        Toast toast = Toast.makeText(getApplicationContext(), "Loged In", Toast.LENGTH_LONG);
                        toast.show();
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Incorrect password", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
               else if(type.equals("Nurse")) {

                    Nurse nurse = db.getNurse(username);
                    if (nurse.getFirstName().equals(null) || nurse.getFirstName().equals("")) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Incorrect nurse username", Toast.LENGTH_LONG);
                        toast.show();
                    } else if (nurse.getFirstName() != null && nurse.getFirstName() != "" && nurse.getPassword().equals( password)) {
                        SharedPreferences prefs = this.getSharedPreferences(
                                "com.example.brindersaini", Context.MODE_PRIVATE);
                        prefs.edit().putString("UserName",username).commit();
                        prefs.edit().putString("Type",type).commit();
                        Toast toast = Toast.makeText(getApplicationContext(), "Loged nurse In", Toast.LENGTH_LONG);
                        toast.show();
                        Intent intent = new Intent(this, PatientInfoActivity.class);
                        startActivity(intent);
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Incorrect nurse password", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }

            }
        }
       catch (Exception e)
       {
           Toast toast2 = Toast.makeText(getApplicationContext(),"Incorrect username",Toast.LENGTH_SHORT);
           toast2.show();
       }
       // Toast toast2 = Toast.makeText(getApplicationContext(),doctor.getFirstName(),Toast.LENGTH_SHORT);
       // toast2.show();
    }
}
