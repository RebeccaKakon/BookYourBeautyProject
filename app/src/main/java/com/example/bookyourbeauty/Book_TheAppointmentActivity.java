package com.example.bookyourbeauty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Book_TheAppointmentActivity extends AppCompatActivity {


    DatabaseReference referenceRoot;
    FirebaseDatabase rootNode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book__the_appointment);

        String emailClient = getIntent().getStringExtra("email_currntClient");
        String choosenIdTreatment = getIntent().getStringExtra("id_choosenTreatment");
        String choosenIdManager = getIntent().getStringExtra("id_choosenManager");
        String choosenDate= getIntent().getStringExtra("id_choosenDate");
        String choosenTime= getIntent().getStringExtra("id_choosenTime");

        findIdAppo_And_SetIDClient(emailClient,choosenIdTreatment,choosenIdManager,choosenDate,choosenTime);
    }

    private void findIdAppo_And_SetIDClient(String emailClient, String choosenIdTreatment, String choosenIdManager, String choosenDate, String choosenTime) {

        rootNode= FirebaseDatabase.getInstance();
        referenceRoot=rootNode.getReference("Appointment");
        referenceRoot.addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //go through all appointments
                String currManager;
                String currDate;
                String currStartHour;
                for (DataSnapshot currAppo : snapshot.getChildren()) {
                    //if appointment is free add to list
                    currManager= currAppo.child("idManager").getValue().toString();
                    if (currManager.equals(choosenIdManager)) {
                        currDate = currAppo.child("date_app").getValue().toString();
                        if (currDate.equals(choosenDate)) {//if this current date is not in the list- add it
                            currStartHour = currAppo.child("startTime").getValue().toString();
                            if (currStartHour.equals(choosenTime)) {//if this current date is not in the list- add it
                             // currAppo.child("idTreatment").onUpdate(choosenIdTreatment);//chang the treatment id
                                //chang the client id
                            }
                        }
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }

        });

    }
}