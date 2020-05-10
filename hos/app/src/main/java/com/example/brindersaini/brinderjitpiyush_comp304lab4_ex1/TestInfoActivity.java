package com.example.brindersaini.brinderjitpiyush_comp304lab4_ex1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import brinderjit.Piyush.dto.Nurse;
import brinderjit.Piyush.dto.Patient;
import brinderjit.Piyush.dto.Test;
import brinderjitPiyush.DatabaseHandler.DatabaseHandler;

public class TestInfoActivity extends AppCompatActivity {
private  Test test;
    private static DatabaseHandler db;
    public TestInfoActivity()
    {
        db=new DatabaseHandler(this);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_info);
        Intent intent=getIntent();
        int testId=intent.getIntExtra("testId",0);
        test=db.getTest(testId);
        setDetails();
    }
    public void onEditClick(View v)
    {
        Intent intent=new Intent(this,AddUpdateTestActivity.class);
        intent.putExtra("testId",test.getTestId());
        intent.putExtra("purpose","update");
        startActivity(intent);
    }
    public void setDetails()
    {
        TextView patientName=(TextView)findViewById(R.id.patientNameTxt);
        TextView nurse=(TextView)findViewById(R.id.nurseNameTxt);
        TextView BPL=(TextView)findViewById(R.id.bplTxt);
        TextView BPH=(TextView)findViewById(R.id.bphTxt);
        TextView LDLCholesterol=(TextView)findViewById(R.id.LDLCholesterolTxt);
        TextView HDLCholesterol=(TextView)findViewById(R.id.HDLCholesterolTxt);
        TextView MCH=(TextView)findViewById(R.id.MCHTxt);
        TextView ESR=(TextView)findViewById(R.id.ESRTxt);
        TextView PlateletCount=(TextView)findViewById(R.id.PlateletCountTxt);
        TextView temperature=(TextView)findViewById(R.id.temperatureTxt);
        Patient p=db.getPatient(test.getPatientId());
        patientName.setText(p.getFirstName()+" "+p.getLastName());
        Nurse n=db.getNurse(test.getNurseId());
        nurse.setText(n.getFirstName()+" "+n.getLastName());
        BPL.setText(test.getBPL());
        BPH.setText(test.getBPH());
        LDLCholesterol.setText(test.getLDLCholesterol());
        HDLCholesterol.setText(test.getHDLCholesterol());
        MCH.setText(test.getMCH());
        ESR.setText(test.getESR());
        PlateletCount.setText(test.getPlateletCount());
        temperature.setText(test.getTemperature());

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
