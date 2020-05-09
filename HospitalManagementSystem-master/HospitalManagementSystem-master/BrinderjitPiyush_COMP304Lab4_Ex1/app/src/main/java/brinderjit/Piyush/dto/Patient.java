package brinderjit.Piyush.dto;

/**
 * Created by Brinder Saini on 01/11/2017.
 */

public class Patient {
    private int patientId;
    private String firstname;
    private String lastname;
    private String department;

    private String doctorId;
    private String room;


    // Empty constructor
    public Patient(){

    }
    // constructor
    public Patient(int Id, String firstname, String lastname,String department,String doctorId,String room){
        this.patientId = Id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.department=department;

        this.doctorId=doctorId;
        this.room=room;
    }
    // getting ID
    public int getID(){
        return this.patientId;
    }

    // setting id
    public void setID(int id){
        this.patientId = id;
    }

    // getting name
    public String getFirstName(){
        return this.firstname;
    }

    // setting name
    public void setFirstname(String name){
        this.firstname = name;
    }
    public String getLastName(){
        return this.lastname;
    }

    // setting name
    public void setLastname(String name){
        this.lastname = name;
    }

    public String getDepartment(){
        return this.department;
    }

    public void setDepartment(String department){
        this.department = department;
    }

    public String getDoctorId(){
        return this.doctorId;
    }


    public void setDoctorId(String doctorId){
        this.doctorId = doctorId;
    }
    public String getRoom(){
        return this.room;
    }


    public void setRoom(String room){
        this.room= room;
    }
    public String toString() {
        return getFirstName()+" "+getLastName();
    }
}
