package com.example.bookyourbeauty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class choosTimeAndDate extends AppCompatActivity {
    EditText date;
    Button save;

    FirebaseDatabase rootNode;
    DatabaseReference rootReference;

    Appointment newAppointment;
    String managerId;
    String choosenEndHour;
    String choosenStartHour;


    Spinner startHour_spinner;
Spinner endHour_spinner;
int start;
int end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choos_time_and_date);
////*********origin choose
//        managerid = getIntent().getStringExtra("email");
//        save = (Button) findViewById(R.id.saveCreat);
//        date = (EditText) findViewById(R.id.chooseDate);
//        //start= (EditText) findViewById(R.id.startTime);
//        //end= (EditText) findViewById(R.id.endTime);
//
//
//        save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                System.out.println("*********start onClick ");
//                FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
//
////                choosenDate= date.getText().toString();
////                choosenStartTime= start.getText().toString();
////                choosenEndTime= end.getText().toString();
//
//                rootReference = rootNode.getReference();
//                auth = FirebaseAuth.getInstance();
////                FirebaseUser user = auth.getCurrentUser();
////                managerid=auth.getCurrentUser().getUid();
//
////                newAppointment = new Appointment(choosenDate,choosenStartTime,choosenEndTime,managerid);
//                newAppointment = new Appointment();
//                String choosenDate = date.getText().toString();
//               // String choosenStartTime = start.getText().toString();
//             //   String choosenEndTime = end.getText().toString();
//
//                newAppointment.setdate_app(choosenDate);
//                newAppointment.setStartTime(choosenStartTime);
//                newAppointment.setEndTime(choosenEndTime);
//                newAppointment.setIdManager(managerid);
//                //   newAppointment.setIdClient(null); //// new
//                //  newAppointment.settretmantId(null);///new
//
//
//                rootNode = FirebaseDatabase.getInstance();
//                rootReference = rootNode.getReference("Appointment");
//                rootReference.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        for (DataSnapshot postSnapshot : snapshot.getChildren()) {
//                            //from DB
//                            String TempDate = (String) postSnapshot.child("date_app").getValue().toString();
////                            String start = (String) Objects.requireNonNull(postSnapshot.child("start").getValue()).toString();
////                            String appointmenEndTime = (String) Objects.requireNonNull(postSnapshot.child("appointmenEndtTime").getValue()).toString();
//
//                            if ((TempDate.equals(choosenDate))) {
//                                Toast.makeText(choosTimeAndDate.this, "you already have an appoinment on this date", Toast.LENGTH_SHORT).show();
//                                break;
//                            }
////                            if ((appointmenStartTime.equals(choosenStartTime)) ) {
////                                Toast.makeText(choosTimeAndDate.this, "you already have an appoinment on this time", Toast.LENGTH_SHORT).show();
////                                break;
////                            }
////                            if ((appointmenEndTime.equals(choosenEndTime)) ) {
////                                Toast.makeText(choosTimeAndDate.this, "you already have an appoinment on this time", Toast.LENGTH_SHORT).show();
////                                break;
////                            }
//                        }
//                        //not found- you can add this new treatment
////                        FirebaseUser user = auth.getCurrentUser();
////                        String id = user.getUid();
//                        newAppointment.setIdAppo();
//
//                        String id = "appointment" + newAppointment.getIdAppo();
//
//                        rootReference.child(id).setValue(newAppointment); //add to firebase//child("Treatments")
//                        Toast.makeText(choosTimeAndDate.this, "your add appointment was seccssed", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(choosTimeAndDate.this, ManagerOptionsActivity.class);
//                        startActivity(intent);
//                    }
//
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
//
//
//            }
//
//        });
//
//    }
//}//*********end_origin choose

        managerId= getIntent().getStringExtra("email");
        save = (Button) findViewById(R.id.saveCreat);
        date= (EditText) findViewById(R.id.chooseDate);
        startHour_spinner = (Spinner) findViewById(R.id.StartHour_spinner);
        endHour_spinner= (Spinner) findViewById(R.id.EndHour_spinner);

        String[] hoursArray= {"select hour","10","11","12","13","14","15","16","17","18","19","20","21"};
        ArrayAdapter<String> adpHour= new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,hoursArray);
        adpHour.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        startHour_spinner.setAdapter(adpHour);
        endHour_spinner.setAdapter(adpHour);

//        String[] minsArray= {"select mins","00"};
//        ArrayAdapter<String> adpMins= new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,minsArray);
//        adpMins.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        startMins_spinner.setAdapter(adpMins);
//        endMins_spinner.setAdapter(adpMins);

        startHour_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                choosenStartHour= (String) parent.getItemAtPosition(position);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

//        startMins_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                choosenStartMins= (String) parent.getItemAtPosition(position);
//            }
//            public void onNothingSelected(AdapterView<?> parent) {
//            }
//        });

        endHour_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                choosenEndHour= (String) parent.getItemAtPosition(position);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

//        endMins_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                choosenEndMins= (String) parent.getItemAtPosition(position);
//            }
//            public void onNothingSelected(AdapterView<?> parent) {
//            }
//        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("*********start onClick ");
                FirebaseDatabase rootNode=FirebaseDatabase.getInstance();
                rootReference=rootNode.getReference();

                newAppointment = new Appointment();
                String choosenDate= date.getText().toString();
                 start= Integer.parseInt(choosenStartHour);
                 end= Integer.parseInt(choosenEndHour);

                 newAppointment.setIdClient("-");
                newAppointment.setIdTreatment("-");
                newAppointment.setdate_app(choosenDate);
                newAppointment.setStartTime(choosenStartHour);
                int tempEnd= Integer.parseInt(choosenStartHour)+1;
                newAppointment.setEndTime(String.valueOf(tempEnd));
                newAppointment.setIdManager(managerId);


                rootNode=FirebaseDatabase.getInstance();
                rootReference=rootNode.getReference("Appointment");
//                rootReference.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        for (DataSnapshot postSnapshot : snapshot.getChildren() ) {
//                            System.out.println("in loop for date********************");
//
//                            //from DB
//                            String TempDate= (String)postSnapshot.child("date_app").getValue().toString();
////                            String start = (String) Objects.requireNonNull(postSnapshot.child("start").getValue()).toString();
////                            String appointmenEndTime = (String) Objects.requireNonNull(postSnapshot.child("appointmenEndtTime").getValue()).toString();
//
//                            if ((TempDate.equals(choosenDate)) ) {
//                                Toast.makeText(choosTimeAndDate.this, "you already have an appoinment on this date", Toast.LENGTH_SHORT).show();
//                                break;
//                            }
////                            if ((appointmenStartTime.equals(choosenStartTime)) ) {
////                                Toast.makeText(choosTimeAndDate.this, "you already have an appoinment on this time", Toast.LENGTH_SHORT).show();
////                                break;
////                            }
////                            if ((appointmenEndTime.equals(choosenEndTime)) ) {
////                                Toast.makeText(choosTimeAndDate.this, "you already have an appoinment on this time", Toast.LENGTH_SHORT).show();
////                                break;
////                            }
//                        }//ifnish to check if there is allready date like it
//
//                        //not found- you can add this new treatment
////                        FirebaseUser user = auth.getCurrentUser();
////                        String id = user.getUid();
//
//                        newAppointment.setIdAppo();
//
//                        String id="appointment"+newAppointment.getIdAppo();
//
//                        rootReference.child(id).setValue(newAppointment); //add to firebase//child("Treatments")
////                        int start= Integer.parseInt(choosenStartHour);
////                        int end= Integer.parseInt(choosenEndHour);
//                        int currEnd;
//                        if (end-start>1) {
//                            start=start+1;
//                            currEnd=start+1;
//                            while ((end-start)!=0)
//                            {
////                                newAppointment.setdate_app(choosenDate);
//                                newAppointment.setStartTime(String.valueOf(start));
//                                newAppointment.setEndTime(String.valueOf(currEnd));
//                                newAppointment.setIdManager(managerid);
//                                newAppointment.setIdAppo();
//                                id="appointment"+newAppointment.getIdAppo();
//                                rootReference.child(id).setValue(newAppointment);
//                                currEnd++;
//                            }
//                        }
//
//
//                        Toast.makeText(choosTimeAndDate.this, "your add appointment was seccssed", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(choosTimeAndDate.this, ManagerOptionsActivity.class);
//                        startActivity(intent);
//                    }// finish onDataChange
//
//
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });

                newAppointment.setIdAppo();
                String id="appointment"+newAppointment.getIdAppo();

                rootReference=rootNode.getReference("Appointment");
                rootReference.child(id).setValue(newAppointment); //add to firebase//child("Treatments")
                int currEnd=start+1;
                if (end-start>1) {
                    System.out.println("111111111111111111start= "+start+" end= "+end+" currEnd= "+currEnd);
                    start=start+1;
                    currEnd=start+1;
                    System.out.println("start= "+start+" end= "+end+" currEnd= "+currEnd);
                    System.out.println("String.valueOf(start)= "+String.valueOf(start)+" String.valueOf(currEnd)= "+String.valueOf(currEnd));

                            while (end>start)
                            {
                                System.out.println("start= "+start+" end= "+end+" currEnd= "+currEnd);
                                System.out.println("in the while");
                                newAppointment.setdate_app(choosenDate);
                                newAppointment.setIdClient("-");
                                newAppointment.setIdTreatment("-");
                                newAppointment.setStartTime(String.valueOf(start));
                                newAppointment.setEndTime(String.valueOf(currEnd));
                                newAppointment.setIdManager(managerId);
                                newAppointment.setIdAppo();
                                id="appointment"+newAppointment.getIdAppo();

                                rootNode=FirebaseDatabase.getInstance();
                                rootReference=rootNode.getReference();
                                rootReference=rootNode.getReference("Appointment");

                                rootReference.child(id).setValue(newAppointment);

                                start=currEnd;
                                currEnd++;
                            }
                    }


                Toast.makeText(choosTimeAndDate.this, "your add appointment was seccssed", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(choosTimeAndDate.this, ManagerOptionsActivity.class);
                startActivity(intent);

            }

        });

    }


}