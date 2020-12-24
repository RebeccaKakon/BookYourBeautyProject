package com.example.bookyourbeauty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Book_TheAppointmentActivity extends AppCompatActivity {

    DatabaseReference referenceRoot;
    FirebaseDatabase rootNode;
    String emailClient;
    String choosenIdTreatment;

    Button ok_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book__the_appointment);
        ok_button = (Button) findViewById(R.id.Save);


        emailClient = getIntent().getStringExtra("email_currentClient");
        System.out.println("in book appo emailClient= "+emailClient);
        choosenIdTreatment = getIntent().getStringExtra("id_choosenTreatment");
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
                                System.out.println("currAppo.getKey()= "+currAppo.getKey());
                                referenceRoot.child(currAppo.getKey()).child("idClient").setValue(emailClient);
                                System.out.println("before id Treatment&&&&&&&&&&&&&7   idTreatment="+choosenIdTreatment);
                                referenceRoot.child(currAppo.getKey()).child("idTreatment").setValue(choosenIdTreatment);
                                System.out.println("after id Treatment&&&&&&&&&&&&&7");
                                break;
                                // currAppo.child("idTreatment").onUpdate(choosenIdTreatment);//chang the treatment id
                                //chang the client id
                            }
                        }
                    }
                }
                Toast.makeText(Book_TheAppointmentActivity.this, "your add your new appointment seccssed", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(Book_TheAppointmentActivity.this, ClientOptionsActivity.class);
//                startActivity(intent);

                ok_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Book_TheAppointmentActivity.this, ClientOptionsActivity.class);
                        startActivity(intent);
                    }
                });



            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }

        });

    }
}