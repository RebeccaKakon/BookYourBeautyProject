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
    Spinner DateSpinner;
//    EditText dateOption;
    Button search;
    private FirebaseAuth mAuth;

    //the deitels of the choosen booking appontment
    String choosenManager ;
    String choosenIdManager="";

    String choosenTreatment ;
    String  choosenDate;
    String choosenStartTime ;
    String choosenEndTime;
    //End of the deitels of the choosen booking appontment

    private FirebaseAuth auth;
    DatabaseReference referenceRoot;
    Appointment newAppo;

    List<String> managerList;
    List<String> dateList;

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
        DateSpinner = (Spinner) findViewById(R.id.Date_Spinner);
        search = (Button) findViewById(R.id.Search);

        mAuth = FirebaseAuth.getInstance();
        myActivate();
    }
    //activate views &buttons
    private void myActivate() {
        System.out.println("@@@@@@@@@@@@@@@@@@@start my activate");
        managerList = new ArrayList<>();
        FirebaseDatabase rootNode=FirebaseDatabase.getInstance();
        referenceRoot=rootNode.getReference("Managers");
        referenceRoot.addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
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
//                ArrayAdapter<String> adapter = new ArrayAdapter<String>(BookAppointmentActivity.this, android.R.layout.simple_spinner_dropdown_item, managerList);
//                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                managersSpinner.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        //kinds of managers
        //make adapter to connect between the spinner to appointment list
        ArrayAdapter<String> adapterManager = new ArrayAdapter<String>(BookAppointmentActivity.this, android.R.layout.simple_spinner_dropdown_item, managerList);
        adapterManager.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        managersSpinner.setAdapter(adapterManager);

        managersSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("in setOnItemSelectedListener of manager spinner+++++++++++++++++++++++++++");
                choosenManager = (String) parent.getItemAtPosition(position);
                System.out.println("&&&&choosenManager  =  "+choosenManager);
              //  setIdForCurrentManager(choosenManager); //we found the id of the chosen manager and set it to the id opp manager
//****************************************

                String returnId;
                FirebaseDatabase rootNode=FirebaseDatabase.getInstance();
                referenceRoot=rootNode.getReference("Managers");
                referenceRoot.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        System.out.println("***********************before thr for ");
                        //go through all appointments
                        String currentManagerName;
                        String idManager="";

                        for (DataSnapshot s : snapshot.getChildren()) {
                            //if appointment is free add to list
                            System.out.println("***********************in the for!! ");
                            currentManagerName = s.child("first_name").getValue().toString();
                            if (currentManagerName.equals(choosenManager)) {
                                idManager = s.child("email").getValue().toString();
                                System.out.println("%%%%%%%%%%%%%%%%idManager= "+idManager);
                            }
                        }
                        System.out.println("****************idManager= "+idManager);
                        newAppo.setIdManager(idManager);
                        choosenIdManager=idManager;
                        System.out.println("****************choosenIdManager= "+choosenIdManager);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
         //**********************************
            }
                        public void onNothingSelected(AdapterView<?> parent) {
            }
        });
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        dateList = new ArrayList<>();
       // String[] datesArray= {"dates"};
        referenceRoot=rootNode.getReference("Appointment");
        referenceRoot.addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //go through all appointments
                String currentIdManager;
                for (DataSnapshot currAppo : snapshot.getChildren()) {
                    currentIdManager= currAppo.child("idManager").getValue().toString();
                    System.out.println("currentIdManager=  "+currentIdManager+" choosenIdManager= "+choosenIdManager);
                    if (currentIdManager.equals(choosenIdManager) ) { //&& the appo aliveble
                        String currDate=currAppo.child("date_app").getValue().toString();
                        System.out.println("currDate= "+currDate);
                        if (!dateList.contains(currDate)) {//if the date is not in the list
                            dateList.add(currDate);
                        } else {
                            System.out.println("this date allready found and im the list");
                        }
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        ArrayAdapter<String> adapterDate = new ArrayAdapter<String>(BookAppointmentActivity.this, android.R.layout.simple_spinner_dropdown_item, dateList);//
        adapterDate.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        DateSpinner.setAdapter(adapterDate);

        DateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                choosenDate = (String) parent.getItemAtPosition(position);
                System.out.println("&&&&choosenDate  =  "+choosenDate);
                newAppo.setdate_app(choosenDate); //chang the date of the new appo
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

     }

    private void setIdForCurrentManager(String choosenManager) {//to chang the id manager by the choosen name manager
//        String returnId;
//        FirebaseDatabase rootNode=FirebaseDatabase.getInstance();
//        referenceRoot=rootNode.getReference("Managers");
//        referenceRoot.addListenerForSingleValueEvent(new ValueEventListener() {
//
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                System.out.println("***********************before thr for ");
//                //go through all appointments
//                String currentManagerName;
//                String idManager="";
//
//                for (DataSnapshot s : snapshot.getChildren()) {
//                    //if appointment is free add to list
//                    System.out.println("***********************in the for!! ");
//                    currentManagerName = s.child("first_name").getValue().toString();
//                    if (currentManagerName.equals(choosenManager)) {
//                        idManager = s.child("email").getValue().toString();
//                        System.out.println("%%%%%%%%%%%%%%%%idManager= "+idManager);
//                    }
//                }
//                System.out.println("****************idManager= "+idManager);
//                newAppo.setIdManager(idManager);
//                choosenIdManager=idManager;
//                System.out.println("****************choosenIdManager= "+choosenIdManager);
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
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
//        DateSpinner = (Spinner) findViewById(R.id.endTime_Spinner);
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
//        DateSpinner.setAdapter(adpEndT);
//        DateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                choosenEndTime = (String) DateSpinner.getAdapter().getItem(position);
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