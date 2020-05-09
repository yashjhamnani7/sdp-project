package brinderjit.Piyush.dto;

/**
 * Created by Brinder Saini on 01/11/2017.
 */

public class Nurse {
    //private variables
    private String nurseId;
    private String firstname;
    private String lastname;
    private String department;
    private String password;

    // Empty constructor
    public Nurse(){

    }
    // constructor
    public Nurse(String nurseId, String firstname, String lastname,String department,String password){
        this.nurseId = nurseId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.department=department;
        this.password=password;
    }



    // getting ID
    public String getID(){
        return this.nurseId;
    }

    // setting id
    public void setID(String id){
        this.nurseId = id;
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
    // getting phone number
    public String getDepartment(){
        return this.department;
    }

    // setting phone number
    public void setDepartment(String department){
        this.department = department;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return this.password;
    }
    @Override
    public String toString() {
        return getFirstName()+" "+getLastName();
    }
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Nurse){
            Nurse c = (Nurse) obj;
            if(c.getID().equals(getID())) return true;
        }

        return false;
    }
}
