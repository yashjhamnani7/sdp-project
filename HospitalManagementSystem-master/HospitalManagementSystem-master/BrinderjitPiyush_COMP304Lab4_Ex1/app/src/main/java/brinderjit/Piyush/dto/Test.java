package brinderjit.Piyush.dto;

/**
 * Created by Brinder Saini on 01/11/2017.
 */

public class Test {
    private int testId;
    private int patientId;
    private String nurseId;
    private String BPL;
    private String BPH;
    private String LDLCholesterol;
    private String HDLCholesterol;
    private String MCH;
    private String ESR;
    private String PlateletCount;
    private String temperature;
    public Test()
    {

    }
    public Test(int testId,int patientId,String nurseId,String BPL,String BPH,String LDLCholesterol,String HDLCholesterol,String MCH,String ESR,String PlateletCount,String temperature)
    {
        this.testId=testId;
        this.patientId=patientId;
        this.nurseId=nurseId;
        this.BPL=BPL;
        this.BPH=BPH;
        this.LDLCholesterol=LDLCholesterol;
        this.HDLCholesterol=HDLCholesterol;
        this.MCH=MCH;
        this.ESR=ESR;
        this.PlateletCount=PlateletCount;
        this.temperature=temperature;

    }
    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getNurseId() {
        return nurseId;
    }

    public void setNurseId(String nurseId) {
        this.nurseId = nurseId;
    }

    public String getBPL() {
        return BPL;
    }

    public void setBPL(String BPL) {
        this.BPL = BPL;
    }

    public String getBPH() {
        return BPH;
    }

    public void setBPH(String BPH) {
        this.BPH = BPH;
    }

    public String getLDLCholesterol() {
        return LDLCholesterol;
    }

    public void setLDLCholesterol(String LDLCholesterol) {
        this.LDLCholesterol = LDLCholesterol;
    }

    public String getHDLCholesterol() {
        return HDLCholesterol;
    }

    public void setHDLCholesterol(String HDLCholesterol) {
        this.HDLCholesterol = HDLCholesterol;
    }

    public String getMCH() {
        return MCH;
    }

    public void setMCH(String MCH) {
        this.MCH = MCH;
    }

    public String getESR() {
        return ESR;
    }

    public void setESR(String ESR) {
        this.ESR = ESR;
    }

    public String getPlateletCount() {
        return PlateletCount;
    }

    public void setPlateletCount(String plateletCount) {
        PlateletCount = plateletCount;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
    public String toString() {
        return "Test "+getTestId();
    }

}

