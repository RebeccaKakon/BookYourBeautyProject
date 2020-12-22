package com.example.bookyourbeauty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class choosTimeAndDate extends AppCompatActivity {
    EditText date;
    EditText start;
    EditText end;
    Button save;
    private FirebaseAuth auth;
    DatabaseReference rootReference;
    FirebaseDatabase rootNode;

    Appointment newAppointment;
    String choosenDate;
    String choosenStartTime;
    String choosenEndTime;
    String managerid;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choos_time_and_date);
        managerid= getIntent().getStringExtra("email");
        save = (Button) findViewById(R.id.saveCreat);
        date= (EditText) findViewById(R.id.chooseDate);
        start= (EditText) findViewById(R.id.startTime);
        end= (EditText) findViewById(R.id.endTime);



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("*********start onClick ");
                FirebaseDatabase rootNode=FirebaseDatabase.getInstance();

//                choosenDate= date.getText().toString();
//                choosenStartTime= start.getText().toString();
//                choosenEndTime= end.getText().toString();

                rootReference=rootNode.getReference();
                auth=FirebaseAuth.getInstance();
//                FirebaseUser user = auth.getCurrentUser();
//                managerid=auth.getCurrentUser().getUid();

//                newAppointment = new Appointment(choosenDate,choosenStartTime,choosenEndTime,managerid);
                newAppointment = new Appointment();
                String choosenDate= date.getText().toString();
                String choosenStartTime= start.getText().toString();
                String choosenEndTime= end.getText().toString();

                newAppointment.setdate_app(choosenDate);
                newAppointment.setStartTime(choosenStartTime);
                newAppointment.setEndTime(choosenEndTime);
                newAppointment.setIdManager(managerid);
              //  newAppointment.setIdClient(null); //// new
             //   newAppointment.settretmantId(null);///new



                rootNode=FirebaseDatabase.getInstance();
                rootReference=rootNode.getReference("Appointment");
                rootReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot postSnapshot : snapshot.getChildren() ) {
                            //from DB
                            String TempDate= (String)postSnapshot.child("date_app").getValue().toString();
//                            String start = (String) Objects.requireNonNull(postSnapshot.child("start").getValue()).toString();
//                            String appointmenEndTime = (String) Objects.requireNonNull(postSnapshot.child("appointmenEndtTime").getValue()).toString();

                            if ((TempDate.equals(choosenDate)) ) {
                                Toast.makeText(choosTimeAndDate.this, "you already have an appoinment on this date", Toast.LENGTH_SHORT).show();
                                break;
                            }
//                            if ((appointmenStartTime.equals(choosenStartTime)) ) {
//                                Toast.makeText(choosTimeAndDate.this, "you already have an appoinment on this time", Toast.LENGTH_SHORT).show();
//                                break;
//                            }
//                            if ((appointmenEndTime.equals(choosenEndTime)) ) {
//                                Toast.makeText(choosTimeAndDate.this, "you already have an appoinment on this time", Toast.LENGTH_SHORT).show();
//                                break;
//                            }
                        }//ifnish to check if there is allready date like it

                        //not found- you can add this new treatment
//                        FirebaseUser user = auth.getCurrentUser();
//                        String id = user.getUid();
                        newAppointment.setIdAppo();

                        String id="appointment"+newAppointment.getIdAppo();

                        rootReference.child(id).setValue(newAppointment); //add to firebase//child("Treatments")
                        Toast.makeText(choosTimeAndDate.this, "your add appointment was seccssed", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(choosTimeAndDate.this, ManagerOptionsActivity.class);
                        startActivity(intent);
                    }// finish onDataChange



                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });



            }

        });

    }


}