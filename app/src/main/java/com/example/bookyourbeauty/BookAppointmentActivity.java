package com.example.bookyourbeauty;

//import android.content.Intent;
//import android.os.Build;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widet.EditText;
//import android.widget.EditText;
//import android.widget.Spinner;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BookAppointmentActivity extends AppCompatActivity {//implements View.OnClickListener

    Spinner treatmentOptionSpinner;
    Spinner managersSpinner;
//    Spinner dateOptionSpinner;
    Spinner startTimeSpinner;
    Spinner endTimeSpinner;
    EditText dateOption;
    Button search;
    private FirebaseAuth mAuth;

    //the deitels of the choosen booking appontment
    String choosenManager ;
    String choosenTreatment ;
    String  choosenDate;
    String choosenStartTime ;
    String choosenEndTime;
    //End of the deitels of the choosen booking appontment

    private FirebaseAuth auth;
    DatabaseReference referenceRoot;

    List<String> managerList;
    protected void onCreate(Bundle savedInstanceState) {
        //initialization
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);
        Intent iin= getIntent();
        //get extras from register/login activity
//        Bundle b = iin.getExtras();
        managersSpinner = (Spinner) findViewById(R.id.manager_Spinner);
        treatmentOptionSpinner = (Spinner) findViewById(R.id.treatmentOption_spinner);
        startTimeSpinner = (Spinner) findViewById(R.id.startTime_Spinner);
        endTimeSpinner = (Spinner) findViewById(R.id.endTime_Spinner);
        dateOption=(EditText) findViewById(R.id.DateOption);
        search = (Button) findViewById(R.id.Search);

        mAuth = FirebaseAuth.getInstance();
        myActivate();
    }
    //activate views &buttons
    private void myActivate() {
        System.out.println("@@@@@@@@@@@@@@@@@@@start my activate");

        //take all available haircuts from firebase
        managerList = new ArrayList<>();
        String empty;

//        ManagersFB managerr = new ManagersFB();
        //check if there are any appointments in FB
        FirebaseDatabase rootNode=FirebaseDatabase.getInstance();
        referenceRoot=rootNode.getReference("Managers");
        referenceRoot.addListenerForSingleValueEvent(new ValueEventListener(){

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                System.out.println("***********************before thr for ");
                //go through all appointments
                String currentManagerName;
                for (DataSnapshot s : snapshot.getChildren()) {
                    //if appointment is free add to list
                    System.out.println("***********************in the for!! ");
                    currentManagerName= s.child("first_name").getValue().toString();
                    if (!currentManagerName.equals("")) {
                        managerList.add(currentManagerName);
                    }
                }
                //make adapter to connect between the spinner to appointment list
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(BookAppointmentActivity.this, android.R.layout.simple_spinner_dropdown_item, managerList);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                managersSpinner.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        //activate spinner
        managersSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String appointmentSelected =parent.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

//        haircutList = new ArrayList<>();
//        hairCutsFB haircuts = new hairCutsFB();
//        DatabaseReference dr2 = haircuts.allHairCuts();
//        dr2.addListenerForSingleValueEvent(new ValueEventListener() {
//            //go through all haircuts
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                //add all haircuts to list
//                for (DataSnapshot s : snapshot.getChildren()) {
//                    haircutList.add(s.getValue(hairCut.class));
//                }
//                //make adapter to connect between the spinner to haircuts list
//                ArrayAdapter<hairCut> adapter = new ArrayAdapter<hairCut>(selectAppointmentActivity.this, android.R.layout.simple_spinner_dropdown_item, haircutList);
//                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                spinnerHaircut.setAdapter(adapter);
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//        });
//        //activate spinner
//        spinnerHaircut.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                hairCut haircut = (hairCut) parent.getSelectedItem();
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//            }
//        });
//
//        select.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String appointmentID = spinnerAppointment.getSelectedItem().toString();
//                String haircut = spinnerHaircut.getSelectedItem().toString();
//                String haircutID = haircut.substring(0, haircut.indexOf(','));
//                appointmentFB app = new appointmentFB();
//                app.getAppointmendByID(appointmentID).child("name").setValue((fName + " " + lName));
//                app.getAppointmendByID(appointmentID).child("haircut").setValue(haircutID);
//                app.getAppointmendByID(appointmentID).child("phone").setValue(phone);
//                Toast.makeText(getApplicationContext(),"selected successful", Toast.LENGTH_LONG).show();
//            }
//        });
//
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(selectAppointmentActivity.this,profileActivity.class);
//                i.putExtra("firstName", fName);
//                i.putExtra("lastName", lName);
//                i.putExtra("phone", phone);
//                startActivity(i);
//            }
//        });
     }
}





//
//
//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_book_appointment);
//
//        managersSpinner = (Spinner) findViewById(R.id.manager_Spinner);
//        treatmentOptionSpinner = (Spinner) findViewById(R.id.treatmentOption_spinner);
//        startTimeSpinner = (Spinner) findViewById(R.id.startTime_Spinner);
//        endTimeSpinner = (Spinner) findViewById(R.id.endTime_Spinner);
//        dateOption=(EditText) findViewById(R.id.DateOption);
//        search = (Button) findViewById(R.id.Search);
//
//
//        //kinds of managers
//        String[] managersArray= {"manager",""};
//        ArrayAdapter<String> adpManager= new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,managersArray);
//        adpManager.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        managersSpinner.setAdapter(adpManager);
//
//        managersSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                choosenManager = (String) parent.getItemAtPosition(position);
//                System.out.println("&&&&choosenManager  =  "+choosenManager);
//
//            }
//            public void onNothingSelected(AdapterView<?> parent) {
//            }
//        });
//
//
//
//        //kinds of treatments
//        String[] treatmentOptionArray= {"treatmentOption","Nails gel polish", "Eyebrow wax","Legs and hands wax","Manicure","Pedicure"};
//        ArrayAdapter<String> adpTreatment= new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,treatmentOptionArray);
//        adpTreatment.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        treatmentOptionSpinner.setAdapter(adpTreatment);
//
//        treatmentOptionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                choosenTreatment = (String) parent.getItemAtPosition(position);
//                System.out.println("&&&&choosenTreatment  =  "+choosenTreatment);
//
//            }
//            public void onNothingSelected(AdapterView<?> parent) {
//            }
//        });
//
//        //date
//        choosenDate= dateOption.getText().toString();
//        System.out.println("&&&&choosenDate  =  "+choosenDate);
//
//        //start time
//        String[] startTimeArray= {"start Time","7:00","8:00", "9:00","10:00","11:00","12:00","13:00",
//                "14:00","15:00","16:00"};
//        ArrayAdapter<String> adpStartT= new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,startTimeArray);
//        adpStartT.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        startTimeSpinner.setAdapter(adpStartT);
//
//        startTimeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                choosenStartTime = (String) parent.getItemAtPosition(position);
//                System.out.println("&&&&choosenStartTime  =  "+choosenStartTime);
//
//            }
//            public void onNothingSelected(AdapterView<?> parent) {
//            }
//        });
//
//        //end time
//        String[] endTimeArray= {"end Time","8:00", "9:00","10:00","11:00","12:00","13:00",
//                "14:00","15:00","16:00"};
//        ArrayAdapter<String> adpEndT= new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,endTimeArray);
//        adpEndT.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        endTimeSpinner.setAdapter(adpEndT);
//        endTimeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                choosenEndTime = (String) endTimeSpinner.getAdapter().getItem(position);
//                System.out.println("&&&&choosenEndTime  =  "+choosenEndTime);
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//
//
//        search.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                System.out.println("***********************start onClick ");
//                String emailClient = getIntent().getStringExtra("email");
//
//                Intent intent =new Intent(getApplicationContext(),OptionsOfAppoForBookingActivity.class);//v.getContext()
//                intent.putExtra("email", emailClient);
//                intent.putExtra("choosenTreatment",choosenTreatment);
//                intent.putExtra("choosenDate",choosenDate);
//                intent.putExtra("choosenStartTime",choosenStartTime);
//                intent.putExtra("choosenEndTime",choosenEndTime);
//                startActivity(intent);
//            }
//        });
//
//    }
//}