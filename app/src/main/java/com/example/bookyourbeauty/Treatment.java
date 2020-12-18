package com.example.bookyourbeauty;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Treatment<countIdTreatment> {
    private Calendar time;
    private String treatmentName;
    private int idTreatment;
    static int countIdTreatment=0;

    public Treatment(){
        this.time=new GregorianCalendar();
    }

    public Treatment(Calendar currTime,String currName, int currId){
        this.time=currTime;
        this.treatmentName=currName;
        this.idTreatment=currId;
    }
    public Treatment(String currName){
        this.treatmentName=currName;
        this.time=new GregorianCalendar();
        this.time.add(Calendar.MINUTE,30);
        this.idTreatment=countIdTreatment;
        countIdTreatment++;
    }

    public Calendar getTime() {
        return time;
    }

    public void setTime(Calendar cuurTime) {
        this.time = cuurTime;
    }

    public String getTreatmentName() {
        return treatmentName;
    }

    public void setTreatmentName(String currName) {
        this.treatmentName = currName;
    }

    public int getIdTreatment() {
        return idTreatment;
    }

    public void setIdTreatment(int currId) {
        this.idTreatment = currId;
    }

    @Override
    public String toString() {
        return "Treatment{" +
                "time of the treatment='" + time + '\'' +
                ", Treatment name='" + treatmentName + '\'' +
                ", Treatment id='" + idTreatment ;
    }


}
