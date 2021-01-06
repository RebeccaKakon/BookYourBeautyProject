package com.example.bookyourbeauty;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.GregorianCalendar;

import androidx.annotation.NonNull;

public class Treatment<countIdTreatment> {
    private Calendar time;
    private String treatmentName;
    private int idTreatment;
    private String price;
    private String timeT;

    static int countIdTreatment=0;

    public Treatment(){

//        this.time=new GregorianCalendar();
    }

    public Treatment(Calendar currTime,String currName, int currId, String price){
        this.time=currTime;
        this.treatmentName=currName;
        this.idTreatment=currId;
        this.price= price;
    }
    public Treatment(String currName, String price){
        this.treatmentName=currName;
        this.time=new GregorianCalendar();
        this.time.add(Calendar.MINUTE,30);
        this.idTreatment=countIdTreatment;
        countIdTreatment++;
        this.price=price;
                //countIdTreatment;
        //countIdTreatment++;
    }
    public String  getprice() {
        return price;
    }

    public void setprice(String price) {
        this.price = price;
    }

    private int findIdTreatment(String treatmentName) {
        Treatment tempTreatment=new Treatment();
        final int[] lastCount = {0};
        FirebaseDatabase rootNode=FirebaseDatabase.getInstance();
        DatabaseReference referenceRoot = rootNode.getReference("Treatments");
        referenceRoot.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //go through all appointments
                String currIdTreatment;
                String currTreatmentName;

                for (DataSnapshot currAppo : snapshot.getChildren()) {
                    //if appointment is free add to list
                    currIdTreatment = currAppo.child("idTreatment").getValue().toString();
                    currTreatmentName = currAppo.child("treatmentName").getValue().toString();

                    if (treatmentName.equals(currTreatmentName)) {
                        lastCount[0] = Integer.parseInt(currIdTreatment);
                        System.out.println("lastCount[0]= "+lastCount[0]);
                    }
                }
                tempTreatment.setIdTreatment(lastCount[0]);
                System.out.println("tempTreatment= "+tempTreatment.getIdTreatment());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }

        });
        System.out.println("return tempTreatment.getIdTreatment()= "+ tempTreatment.getIdTreatment());
        return tempTreatment.getIdTreatment();


    }
    public String gettimeT() {
        return timeT;
    }

    public void settimeT(String timeT) {
        this.timeT = timeT;
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
        return "treatment="+treatmentName + " \n" + "price=" +price +" \n"+ "id="+idTreatment;
    }

    public String toStringView() {
        return treatmentName + " \n" + "price=" +price +" \n"+ timeT;
    }


}
