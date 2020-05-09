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

import java.util.List;

import brinderjit.Piyush.dto.Doctor;
import brinderjit.Piyush.dto.Nurse;
import brinderjit.Piyush.dto.Patient;
import brinderjit.Piyush.dto.Test;
import brinderjitPiyush.DatabaseHandler.DatabaseHandler;

import static com.example.brindersaini.brinderjitpiyush_comp304lab4_ex1.R.id.firstNameEdt;

public class AddUpdateTestActivity extends AppCompatActivity {

    private EditText patientEdt, bplEdt,bphEdt,ldlCholestrolEdt,hdlCholestrolEdt,mchEdt,esrEdt,plateletCountEdt,temperatureEdt;
    private Spinner nurseId;
    private static DatabaseHandler db;
    private Test  test;
    private String purpose;
    private ArrayAdapter<Nurse> adapter;
    public AddUpdateTestActivity(){
        db=new DatabaseHandler(this);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_update_test);
        Intent intent=getIntent();
        purpose=intent.getStringExtra("purpose");

        initialiseViews();
        bindNurseSpinner();
        if(purpose.equals("update"))
        {
            int testId=intent.getIntExtra("testId",0);


            test=db.getTest(testId);
            this.test.setTestId(testId);
            this.test.setPatientId(test.getPatientId());
            setViews(test);
        }
        else if(purpose.equals("add"))
        {
            test=new Test();
            int patientId=intent.getIntExtra("patientId",0);
            setPatientName(patientId);
            this.test.setPatientId(patientId);
            Button submitBtn=(Button)findViewById(R.id.updateBtn);
            submitBtn.setText("Add");
        }
    }
    public void setPatientName(int id)
    {
        Patient patient=db.getPatient(id);
        patientEdt.setText(patient.getFirstName()+" "+patient.getLastName());
    }
    public void initialiseViews(){

        patientEdt=(EditText)findViewById(R.id.patientNameEdt);
        patientEdt.setFocusable(false);
        patientEdt.setClickable(false);
        bplEdt=(EditText)findViewById(R.id.BPLEdt);
        bphEdt=(EditText)findViewById(R.id.BPHEdt);
        ldlCholestrolEdt=(EditText)findViewById(R.id.LDLCholestrolEdt);
        hdlCholestrolEdt=(EditText)findViewById(R.id.HDLCholestrolEdt);
        mchEdt=(EditText)findViewById(R.id.MCHEdt);
        esrEdt=(EditText)findViewById(R.id.ESREdt);
        plateletCountEdt=(EditText)findViewById(R.id.PlateletCountEdt);
        temperatureEdt=(EditText)findViewById(R.id.temperatureEdt);
        nurseId=(Spinner)findViewById(R.id.NurseSpinner);
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
    public void setViews(Test test)
    {
        setPatientName(test.getPatientId());
        bplEdt.setText(test.getBPL());
        bphEdt.setText(test.getBPH());
        ldlCholestrolEdt.setText(test.getLDLCholesterol());
        hdlCholestrolEdt.setText(test.getHDLCholesterol());
        mchEdt.setText(test.getMCH());
        esrEdt.setText(test.getESR());
        plateletCountEdt.setText(test.getPlateletCount());
        temperatureEdt.setText(test.getTemperature());
        nurseId.setSelection(adapter.getPosition(db.getNurse(test.getNurseId())));
    }
    public void bindNurseSpinner()
    {
        List<Nurse> nurse=db.getAllNurses();
        nurse.add(0,new Nurse("","Select a nurse","","",""));
        adapter = new ArrayAdapter<Nurse>(this, android.R.layout.simple_spinner_dropdown_item, nurse);
        nurseId.setAdapter(adapter);
        // doctorspin.setSelection(adapter.getPosition(new Doctor("","Select a doctor","","","")));
    }
    public void onSubmitClick(View view){
        Patient patient=new Patient();

        String bpl=bplEdt.getText().toString().trim();
        String bph=bphEdt.getText().toString().trim();
        String ldlCholestrol=ldlCholestrolEdt.getText().toString().trim();
        String hdlCholestrol=hdlCholestrolEdt.getText().toString().trim();
        String mch=mchEdt.getText().toString().trim();
        String esr=esrEdt.getText().toString().trim();
        String plateletCount=plateletCountEdt.getText().toString().trim();
        String temperature=temperatureEdt.getText().toString().trim();
        Nurse n=(Nurse) nurseId.getSelectedItem();

        try {
            if (bpl.length() == 0) {
                bplEdt.requestFocus();
                bplEdt.setError("FIELD CANNOT BE EMPTY");

            } else if (bph.length() == 0) {
                bphEdt.requestFocus();
                bphEdt.setError("FIELD CANNOT BE EMPTY");
            }
            else if (ldlCholestrol.length() == 0) {
                ldlCholestrolEdt.requestFocus();
                ldlCholestrolEdt.setError("FIELD CANNOT BE EMPTY");
            }
            else if (hdlCholestrol.length() == 0) {
                hdlCholestrolEdt.requestFocus();
                hdlCholestrolEdt.setError("FIELD CANNOT BE EMPTY");
            }
            else if (mch.length() == 0) {
                mchEdt.requestFocus();
                mchEdt.setError("FIELD CANNOT BE EMPTY");
            }
            else if (esr.length() == 0) {
                esrEdt.requestFocus();
                esrEdt.setError("FIELD CANNOT BE EMPTY");
            }
            else if (plateletCount.length() == 0) {
                plateletCountEdt.requestFocus();
                plateletCountEdt.setError("FIELD CANNOT BE EMPTY");
            }else if (temperature.length() == 0) {
                temperatureEdt.requestFocus();
                temperatureEdt.setError("FIELD CANNOT BE EMPTY");
            }
            else if (n.getFirstName().equals("Select a nurse")) {
                nurseId.requestFocus();
                Toast toast = Toast.makeText(getApplicationContext(),"Please select a nurse", Toast.LENGTH_LONG);
                toast.show();
            }


            else {
                test.setBPH(bph);
                test.setBPL(bpl);
                test.setLDLCholesterol(ldlCholestrol);
                test.setHDLCholesterol(hdlCholestrol);
                test.setMCH(mch);
                test.setESR(esr);
                test.setPlateletCount(plateletCount);
                test.setTemperature(temperature);
                test.setNurseId(n.getID());
                if(purpose.equals("update")){
                    db.updateTest(test);
                    Toast toast = Toast.makeText(getApplicationContext(),"Test updated successfully", Toast.LENGTH_LONG);
                    toast.show();
                }
               else if(purpose.equals("add")){
                    db.addTest(test);
                    Toast toast = Toast.makeText(getApplicationContext(),"Test added successfully", Toast.LENGTH_LONG);
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
}
