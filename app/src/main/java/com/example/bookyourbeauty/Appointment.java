package com.example.bookyourbeauty;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;

public class Appointment {
    public int idAppo;
    private String idClient;
    private String  idManager;
    private String idTreatment;
    private String date_app;
    private String startTime;
    private String endTime;

    public Appointment(){
    }

    public Appointment(String choosenDate, String choosenStartTime, String choosenEndTime, String manaherId,String clientId,String treatmentId) {
        this.date_app=choosenDate;
        this.startTime=choosenStartTime;
        this.endTime=choosenEndTime;
       // this.idAppo=countIdAppointment;

//        this.countIdAppointment++;

        this.idAppo=restartTheCount();
        System.out.println("in the build = "+this.idAppo);
        this.idManager= manaherId;
        this.idClient=clientId;
        this.idTreatment=treatmentId;
    }

    private int restartTheCount() {
        Appointment tenpAppo=new Appointment();
        final int[] lastCount = {0};
        FirebaseDatabase rootNode=FirebaseDatabase.getInstance();
        DatabaseReference referenceRoot = rootNode.getReference("Appointment");
        referenceRoot.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //go through all appointments
                String currIdAppo;
                for (DataSnapshot currAppo : snapshot.getChildren()) {
                    //if appointment is free add to list
                    currIdAppo = currAppo.child("idAppo").getValue().toString();
                    if (Integer.parseInt(currIdAppo)> lastCount[0]) {
                        lastCount[0] = Integer.parseInt(currIdAppo);
                        System.out.println("lastCount[0]= "+lastCount[0]);
                    }
                }
                lastCount[0]++;
                tenpAppo.setIdAppo(lastCount[0]);
                System.out.println("tenpAppo= "+tenpAppo.getIdAppo());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }

        });
        System.out.println("return tenpAppo= "+tenpAppo.getIdAppo());
        return tenpAppo.getIdAppo();
    }

    private void setIdAppo(int id) {
        this.idAppo=id;
    }


//    public Appointment(int idApo,String idClient, String idManager,int idTreatment,String date,String startTime,String endTime){
//        this.idAppo=idApo;
//        this.idClient=idClient;
//        this.idManager=idManager;
//        this.idTreatment=idTreatment;
//        this.date=date;
//        this.startTime=startTime;
//        this.endTime=endTime;
//    }

    public void setdate_app(String date_app) {
        this.date_app = date_app;
    }
    public String getdate_app() {
        return date_app;
    }

    public String getStartTime() {
        return startTime;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getIdAppo() {
        return idAppo;
    }
    public void setIdAppo() {
//        this.idAppo = countIdAppointment;
//        countIdAppointment++;

        this.idAppo=restartTheCount();//set the id
        System.out.println("in the setIdAppo = "+this.idAppo);
    }

    public String getIdClient() {
        return idClient;
    }
    public void setIdClient(String currId) {
        this.idClient = currId;
    }

    public String getIdManager() {
        return idManager;
    }
    public void setIdManager(String currId) {
        this.idManager = currId;
    }

    public String getIdTreatment() {
        return idTreatment;
    }
    public void setIdTreatment(String currId) {
        this.idTreatment = currId;
    }

//    public int getidAppointment() {
//        return idAppointment;
//    }
//
//    public void setidAppointment(int currId) {
//        this.idAppointment = currId;
//    }

    @Override
    public String toString() {
        return "Appointment{" +
                "start time of the treatment='" + startTime + '\'' +
                "end time of the treatment='" + endTime + '\'' +
                ", Appointment id='" + idAppo + '\'' +
                ", Client id='" + idClient + '\'' +
                ", Manager id='" + idManager + '\'' +
                ", Treatment id='"  ;
    }


}
