package brinderjitPiyush.DatabaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import brinderjit.Piyush.dto.Doctor;
import brinderjit.Piyush.dto.Nurse;
import brinderjit.Piyush.dto.Patient;
import brinderjit.Piyush.dto.Test;

/**
 * Created by Brinder Saini on 01/11/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "HOSPITALDB";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_Patient =
            "create table IF NOT EXISTS Patient(patientId integer primary key autoincrement, "
                    + "firstname text not null,"
                    + "lastname text not null,"
                    + "department text not null,"
                    + "doctorId text not null,"
                    + "room text not null"
                    +");";

    private static final String DATABASE_Test =
            "create table IF NOT EXISTS Test(testId integer primary key autoincrement, "
                    + "patientId integer not null,"
                    + "nurseId text not null,"
                    +"BPL text not null,"
                    +"BPH text not null,"
                    +"LDLCholesterol text not null,"
                    +"HDLCholesterol text not null,"
                    +"MCH text not null,"
                    +"ESR text not null,"
                    +"PlateletCount text not null,"
                    +"temperature text not null"
                    +");";
    private static final String DATABASE_Nurse =
            "create table IF NOT EXISTS Nurses(nurseId text primary key, "
                    + "firstname text not null,"
                    + "lastname text not null,"
                    + "department text not null,"
                    + "password text not null"
                    +");";
    private static final String DATABASE_Doctor =
            "create table IF NOT EXISTS Doctors(doctorId text primary key, "
                    + "firstname text not null,"
                    + "lastname text not null,"
                    + "department text not null,"
                    + "password text not null"
                    +");";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            db.execSQL(DATABASE_Doctor);
            db.execSQL(DATABASE_Nurse);
            db.execSQL(DATABASE_Patient);
            db.execSQL(DATABASE_Test);
            Log.d("Insert: ", "inside oncreate try");
        }
        catch (SQLException e)
        {
            Log.d("Insert: ", "inside oncreate catch");
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + "Patient");
        db.execSQL("DROP TABLE IF EXISTS " + "Test");
        db.execSQL("DROP TABLE IF EXISTS " + "Nurse");
        db.execSQL("DROP TABLE IF EXISTS " + "Doctor");
        // Create tables again
        onCreate(db);
    }

    //Doctor CRUDS
    public void addDoctor(Doctor doctor) {
        SQLiteDatabase db;

        db= this.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put("doctorId", doctor.getID());
            values.put("firstname", doctor.getFirstName());
            values.put("lastname", doctor.getLastName());
            values.put("lastname", doctor.getLastName());
            values.put("department", doctor.getDepartment());
            values.put("password", doctor.getPassword());
            // Inserting Row
            db.insertOrThrow("Doctors", null, values);
        }
        catch (Exception e)
        {
            Log.d("Error",e.getMessage());
        }
        finally {
            db.close();
        }
        // Closing database connection
    }


    public Doctor getDoctor(String id) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query("Doctors", new String[] { "doctorId",
                        "firstName", "lastName","department","password" }, "doctorId=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Doctor doctor = new Doctor(cursor.getString(0),
                cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getString(4));

        return doctor;}


    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<Doctor>();
        // Select All Query
        String selectQuery = "SELECT  * FROM Doctors";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {
            do {
                Doctor doctor = new Doctor(cursor.getString(0),
                        cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getString(4));

                doctors.add(doctor);
            } while (cursor.moveToNext());
        }


        return doctors;}


    public int getDoctorCount() {

        String countQuery = "SELECT  * FROM Doctors";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count=cursor.getCount();
        cursor.close();
        return count;
    }

    public int updateDoctor(Doctor doctor) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("firstname", doctor.getFirstName());
        values.put("lastname", doctor.getLastName());
        values.put("lastname", doctor.getLastName());
        values.put("department", doctor.getDepartment());
        values.put("password", doctor.getPassword());

        // updating row
        return db.update("Doctors", values, "doctorId" + " = ?",
                new String[] { String.valueOf(doctor.getID()) });
    }


    public void deleteDoctor(Doctor doctor) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete("Doctors", "doctorId" + " = ?",
                    new String[]{String.valueOf(doctor.getID())});

            db.close();
        }catch (Exception e){

        }
    }

    //Nurse CRUDS
    public void addNurse(Nurse nurse) {
        SQLiteDatabase db = this.getWritableDatabase();
try {
    ContentValues values = new ContentValues();
    values.put("nurseId", nurse.getID());
    values.put("firstname", nurse.getFirstName());
    values.put("lastname", nurse.getLastName());
    values.put("lastname", nurse.getLastName());
    values.put("department", nurse.getDepartment());
    values.put("password", nurse.getPassword());
    // Inserting Row
    db.insert("Nurses", null, values);
}
catch (Exception e){

}
finally {
    db.close();
}
        // Closing database connection
    }


    public Nurse getNurse(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query("Nurses", new String[] { "nurseId",
                        "firstName", "lastName","department","password" }, "nurseId" + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Nurse nurse = new Nurse(cursor.getString(0),
                cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getString(4));

        return nurse;
    }


    public List<Nurse> getAllNurses() {
        List<Nurse> nurses = new ArrayList<Nurse>();
        // Select All Query
        String selectQuery = "SELECT  * FROM Nurses";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {
            do {
                Nurse nurse = new Nurse(cursor.getString(0),
                        cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getString(4));

                nurses.add(nurse);
            } while (cursor.moveToNext());
        }


        return nurses;}


    public int getNurseCount() {

        String countQuery = "SELECT  * FROM Nurses";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }

    public int updateNurse(Nurse nurse) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("firstname", nurse.getFirstName());
        values.put("lastname", nurse.getLastName());
        values.put("lastname", nurse.getLastName());
        values.put("department", nurse.getDepartment());
        values.put("password", nurse.getPassword());

        // updating row
        return db.update("Nurses", values, "nurseId" + " = ?",
                new String[] { String.valueOf(nurse.getID()) });
    }


    public void deleteNurse(Nurse nurse) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("Nurses", "nurseId" + " = ?",
                new String[] { String.valueOf(nurse.getID()) });
        db.close();
    }

    //Patient CRUDS
    public void addPatient(Patient patient) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("firstname", patient.getFirstName());
        values.put("lastname", patient.getLastName());
        values.put("lastname", patient.getLastName());
        values.put("department", patient.getDepartment());
        values.put("doctorId", patient.getDoctorId());
        values.put("room", patient.getRoom());

        // Inserting Row
        db.insert("Patient", null, values);
        db.close(); // Closing database connection
    }


    public Patient getPatient(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query("Patient", new String[] { "patientId",
                        "firstName", "lastName","department","doctorId","room" }, "patientId" + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Patient patient = new Patient(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));

        return patient;
    }


    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<Patient>();
        // Select All Query
        String selectQuery = "SELECT  * FROM Patient";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {
            do {
                Patient patient = new Patient(Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));

               patients.add(patient);
            } while (cursor.moveToNext());
        }


        return patients;}


    public int getPatientCount() {

        String countQuery = "SELECT  * FROM Patient";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }

    public int updatePatient(Patient patient) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("firstname", patient.getFirstName());
        values.put("lastname", patient.getLastName());
        values.put("lastname", patient.getLastName());
        values.put("department", patient.getDepartment());
        values.put("doctorId", patient.getDoctorId());
        values.put("room", patient.getRoom());

        // updating row
        return db.update("Patient", values, "patientId" + " = ?",
                new String[] { String.valueOf(patient.getID()) });
    }


    public void deletePatient(Patient patient) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("Patient", "patientId" + " = ?",
                new String[] { String.valueOf(patient.getID()) });
        db.close();
    }


    public void addTest(Test test) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put("patientId", test.getPatientId());
            values.put("nurseId", test.getNurseId());
            values.put("BPL", test.getBPL());
            values.put("BPH", test.getBPH());
            values.put("LDLCholesterol", test.getLDLCholesterol());
            values.put("HDLCholesterol", test.getHDLCholesterol());
            values.put("MCH", test.getMCH());
            values.put("ESR", test.getESR());
            values.put("PlateletCount", test.getPlateletCount());
            values.put("temperature", test.getTemperature());
            // Inserting Row
            db.insertOrThrow("Test", null, values);
        }catch (Exception e){

        }
        db.close(); // Closing database connection
    }
    public List<Test> getTestByPatient(int id)
    {
        List<Test> tests = new ArrayList<Test>();
        try {

            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.query("Test", new String[]{"testid","patientId", "nurseId", "BPL", "BPH",
                            "LDLCholesterol", "HDLCholesterol", "MCH", "ESR", "PlateletCount", "temperature"}, "patientId" + "=?",
                    new String[]{String.valueOf(id)}, null, null, null, null);
            if (cursor.moveToFirst()) {
                do {

                    Test test = new Test(Integer.parseInt(cursor.getString(0)),
                            Integer.parseInt(cursor.getString(1)),cursor.getString(2) , cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10));

                    tests.add(test);
                } while (cursor.moveToNext());
            }
        }
        catch (Exception e){
            String me=e.getMessage();
        }
        return tests;
    }

    public Test getTest(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query("Test", new String[] {"testId" ,"patientId","nurseId","BPL","BPH",
                        "LDLCholesterol", "HDLCholesterol","MCH","ESR","PlateletCount","temperature" }, "testId" + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Test test = new Test(Integer.parseInt(cursor.getString(0)),
                Integer.parseInt(cursor.getString(1)), cursor.getString(2), cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8),cursor.getString(9),cursor.getString(10));

        return test;
    }


    public List<Test> getAllTests() {
        List<Test> tests = new ArrayList<Test>();
        // Select All Query
        String selectQuery = "SELECT  * FROM Test";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {
            do {
                Test test = new Test(Integer.parseInt(cursor.getString(0)),
                        Integer.parseInt(cursor.getString(1)), cursor.getString(2), cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8),cursor.getString(9),cursor.getString(10));

                tests.add(test);
            } while (cursor.moveToNext());
        }


        return tests;}


    public int getTestCount() {

        String countQuery = "SELECT  * FROM Test";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }

    public int updateTest(Test test) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("patientId", test.getPatientId());
        values.put("nurseId", test.getNurseId());
        values.put("BPL", test.getBPL());
        values.put("BPH", test.getBPH());
        values.put("LDLCholesterol", test.getLDLCholesterol());
        values.put("HDLCholesterol", test.getHDLCholesterol());
        values.put("MCH", test.getMCH());
        values.put("ESR", test.getESR());
        values.put("PlateletCount", test.getPlateletCount());
        values.put("temperature", test.getTemperature());

        // updating row
        return db.update("Test", values, "testId" + " = ?",
                new String[] { String.valueOf(test.getTestId()) });
    }


    public void deleteTest(Test test) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("Test", "testId" + " = ?",
                new String[] { String.valueOf(test.getTestId()) });
        db.close();
    }



}
