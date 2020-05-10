package com.example.brindersaini.brinderjitpiyush_comp304lab4_ex1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import brinderjit.Piyush.dto.Doctor;
import brinderjit.Piyush.dto.Patient;
import brinderjitPiyush.DatabaseHandler.DatabaseHandler;

import static com.example.brindersaini.brinderjitpiyush_comp304lab4_ex1.R.id.doctorSpinner;

public class AddUpdatePatientActivity extends AppCompatActivity {
 private static DatabaseHandler db;
    private Patient patient;

    private EditText firstNameEdt;
    private EditText lastNameEdt;
    private Spinner doctorSpinner;
    private Spinner departmentSpinner;
    private EditText roomEdt;
    private String purpose;
    private ArrayAdapter<Doctor> adapter;
    private ArrayAdapter<String> deparmentAdapter;
    public  AddUpdatePatientActivity(){
        db=new DatabaseHandler(this);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_update_patient);
        intializeViews();
        Intent intent=getIntent();
        purpose=intent.getStringExtra("purpose");
        if(purpose.equals("update"))
        {
            int patientId=intent.getIntExtra("patientId",0);
            patient=db.getPatient(patientId);
            setViews(patient);
            Button btn=(Button) findViewById(R.id.nextBtn);
            btn.setText("Update");


        }
        else
        {
            patient=new Patient();
        }
    }
    public void setViews(Patient patient)
    {

        firstNameEdt.setText(patient.getFirstName());
        lastNameEdt.setText(patient.getLastName());
        Doctor d=db.getDoctor(patient.getDoctorId());
        int i=adapter.getPosition(d);
        doctorSpinner.setSelection(i);
        departmentSpinner.setSelection(deparmentAdapter.getPosition(patient.getDepartment()));
        roomEdt.setText(patient.getRoom());

    }
    public void intializeViews()
    {
        firstNameEdt=(EditText) findViewById(R.id.firstNameEdt);
        lastNameEdt=(EditText) findViewById(R.id.lastNameEdt);
        departmentSpinner=(Spinner) findViewById(R.id.DepartmentSpinner);
        bindDDepartmentSpinner();
        doctorSpinner=(Spinner)findViewById(R.id.doctorSpinner);
        bindDoctorSpinner();
        roomEdt=(EditText) findViewById(R.id.romEdt);
    }
    public void resetViews()
    {
        firstNameEdt.setText("");
        lastNameEdt.setText("");
        departmentSpinner.setSelection(0);
        doctorSpinner.setSelection(0);
        roomEdt.setText("");
    }
    public  void onSubmitClick(View v)
    {


        String firstName=firstNameEdt.getText().toString().trim();
        String lastName=lastNameEdt.getText().toString().trim();
        String room=roomEdt.getText().toString().trim();
        String department=departmentSpinner.getSelectedItem().toString().trim();
        Doctor d=(Doctor)doctorSpinner.getSelectedItem();


        try {
            if (firstName.length() == 0) {
                firstNameEdt.requestFocus();
                firstNameEdt.setError("FIELD CANNOT BE EMPTY");

            } else if (lastName.length() == 0) {
                lastNameEdt.requestFocus();
                lastNameEdt.setError("FIELD CANNOT BE EMPTY");
            }
            else if (room.length() == 0) {
                roomEdt.requestFocus();
                roomEdt.setError("FIELD CANNOT BE EMPTY");
            } else if (department.equals("Select a department")) {
                departmentSpinner.requestFocus();
                Toast toast = Toast.makeText(getApplicationContext(),"Please select a department", Toast.LENGTH_LONG);
                toast.show();
            }
            else if (d.getFirstName().equals("Select a doctor")) {
                doctorSpinner.requestFocus();
                Toast toast = Toast.makeText(getApplicationContext(),"Please select a doctor", Toast.LENGTH_LONG);
                toast.show();
            }

            else {
                patient.setFirstname(firstName);
                patient.setLastname(lastName);
                patient.setDepartment(department);
                patient.setRoom(room);
                patient.setDoctorId(d.getID());
                if(purpose.equals("add"))
                {
                    db.addPatient(patient);
                    Toast toast = Toast.makeText(getApplicationContext(), "Patient added successfully", Toast.LENGTH_LONG);
                    toast.show();
                    resetViews();
                }
                else if(purpose.equals("update")){
                    db.updatePatient(patient);
                    Toast toast = Toast.makeText(getApplicationContext(), "Patient updated successfully", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        }
        catch (Exception e)
        {
            Toast toast = Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG);
            toast.show();
        }
    }
    public void bindDDepartmentSpinner()
    {
        List<String> departments=new ArrayList<String>( );
        departments.add("Select a department");
        departments.add("Microbiology");
        departments.add("Neonatal");
        departments.add("Nephrology");
        departments.add("Neurology");
        departments.add( "Nutrition and dietetics");
        departments.add("Obstetrics and gynaecology");
        departments.add("Occupational therapy");
        deparmentAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, departments);
        departmentSpinner.setAdapter(deparmentAdapter);
        // doctorspin.setSelection(adapter.getPosition(new Doctor("","Select a doctor","","","")));
    }
    public void bindDoctorSpinner()
    {
        List<Doctor> doctors=db.getAllDoctors();
        doctors.add(0,new Doctor("","Select a doctor","","",""));
        adapter = new ArrayAdapter<Doctor>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, doctors);
        doctorSpinner.setAdapter(adapter);
        // doctorspin.setSelection(adapter.getPosition(new Doctor("","Select a doctor","","","")));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }

}
