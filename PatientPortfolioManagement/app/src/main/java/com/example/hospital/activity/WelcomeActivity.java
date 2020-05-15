package com.example.hospital.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import hospital.object.dto.Doctor;
import hospital.object.dto.Nurse;
import hospitaldb.DatabaseHandler.DatabaseHandler;

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
         Doctor doctor = new Doctor("Dr.Sonali", "Sonali", "Dixit", "Neurology", "dixitsonali");
           // db.deleteDoctor(doctor);
           db.addDoctor(doctor);
           Doctor doctor1 = new Doctor("Dr.Yash", "Yash", "Jhamnani", "Microbiology", "yash");
           // db.deleteDoctor(doctor);
           db.addDoctor(doctor1);
           Doctor doctor2 = new Doctor("Dr.Sunayana", "Sunayana", "Sahoo", "Nephrology", "sunayana");
           // db.deleteDoctor(doctor);
           db.addDoctor(doctor2);
           Doctor doctor3 = new Doctor("Dr.Anand", "Anand", "Kumar", "Nephrology", "anand");
           // db.deleteDoctor(doctor);
           db.addDoctor(doctor3);
           Nurse nurse = new Nurse("Sonali", "Sonali", "Dixit", "Neurology", "dixitsonali");
           db.addNurse(nurse);
           Nurse nurse1 = new Nurse("Sunayana", "Sunayana", "Sahoo", "Microbiology", "sunayana");
           db.addNurse(nurse1);
           Nurse nurse2 = new Nurse("Pragati", "Pragti", "Kumari", "Nephrology", "pragati");
           db.addNurse(nurse2);
           Nurse nurse3 = new Nurse("Kajal", "Kajal", "Kumari", "Nephrology", "kajal");
           db.addNurse(nurse3);

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
                                "com.hospital", Context.MODE_PRIVATE);
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
                                "com.hospital", Context.MODE_PRIVATE);
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
