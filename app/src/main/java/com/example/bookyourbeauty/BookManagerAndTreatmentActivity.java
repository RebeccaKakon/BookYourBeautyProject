package com.example.bookyourbeauty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BookManagerAndTreatmentActivity extends AppCompatActivity implements View.OnClickListener {
//    Spinner treatmentOptionSpinner;
    Spinner managersSpinner;

    Button continueButton;

//    String choosenTreatment ;
//    String choosenIdTreatment;
    String choosenManager ;
    String choosenIdManager;
    DatabaseReference referenceRoot;
    Appointment newAppo=new Appointment();

//    List<String> treatmentsList;
    List<String> managerList;
    FirebaseDatabase rootNode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_manager_and_treatment);


        managersSpinner = (Spinner) findViewById(R.id.manager_Spinner);
//        treatmentOptionSpinner = (Spinner) findViewById(R.id.treatmentOption_spinner);
        continueButton = (Button) findViewById(R.id.ContinueButton);
//        initTreatments();
        initManager();

//        continueButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String emailClient = getIntent().getStringExtra("email_currntClient");
//                Intent i = new Intent(BookManagerAndTreatmentActivity.this, BookDateActivity.class);
//                i.putExtra("email_currntClient", emailClient);
//                i.putExtra("id_choosenTreatment", choosenIdTreatment);
//                i.putExtra("id_choosenManager", choosenIdManager);
//                startActivity(i);
//            }
//        });
    }

//    private void initTreatments() {
//
//        treatmentsList = new ArrayList<>();
//        rootNode=FirebaseDatabase.getInstance();
//        referenceRoot=rootNode.getReference("Treatments");
//        referenceRoot.addListenerForSingleValueEvent(new ValueEventListener(){
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                //go through all appointments
//                String currentTreatmentName;
//                for (DataSnapshot currTreatment : snapshot.getChildren()) {
//                    //if appointment is free add to list
//                    currentTreatmentName= currTreatment.child("treatmentName").getValue().toString();
//                    if (!treatmentsList.contains(currentTreatmentName)) {//if this current treatment is not in the list- add it
//                        System.out.println("***********************currentTreatmentName "+currentTreatmentName);
//                        treatmentsList.add(currentTreatmentName);//add to the list
//                    }
//                }
//
//                //make adapter to managers spinner
//                ArrayAdapter<String> adapterTreatment = new ArrayAdapter<String>(BookManagerAndTreatmentActivity.this, android.R.layout.simple_spinner_item, treatmentsList);
//                adapterTreatment.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                treatmentSpinner.setAdapter(adapterTreatment);
//                System.out.println("treatments list= "+treatmentsList.toString());
//                treatmentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                    @Override
//                    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
//                        System.out.println("in setOnItemSelectedListener of treatment spinner+++++++++++++++++++++++++++");
//                        choosenTreatment = (String)parent.getItemAtPosition(position).toString();
//                        System.out.println("&&&&choosenTreatment  =  "+choosenTreatment);
//                        setChoosenIdTreatment(choosenTreatment); //we found the id of the chosen manager and set it to the id opp manager
//
//                    }
//                    @Override
//                    public void onNothingSelected(AdapterView<?> parent) {
//                    }
//                });
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//        });
//
//    }

    private void initManager() {
        managerList = new ArrayList<>();
        rootNode=FirebaseDatabase.getInstance();
        referenceRoot=rootNode.getReference("Managers");
        referenceRoot.addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //go through all appointments
                String currentManagerName;
                for (DataSnapshot currManager : snapshot.getChildren()) {
                    //if appointment is free add to list
                    currentManagerName= currManager.child("first_name").getValue().toString();
                    if (!managerList.contains(currentManagerName)) {//!currentManagerName.equals("")
                        System.out.println("***********************currentManagerName "+currentManagerName);
                        managerList.add(currentManagerName);
                    }
                }

                //make adapter to managers spinner
                ArrayAdapter<String> adapterManager = new ArrayAdapter<String>(BookManagerAndTreatmentActivity.this, android.R.layout.simple_spinner_item, managerList);
                adapterManager.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                managersSpinner.setAdapter(adapterManager);
                System.out.println("manager list= "+managerList.toString());
                managersSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                        System.out.println("in setOnItemSelectedListener of manager spinner+++++++++++++++++++++++++++");
                        choosenManager = (String)parent.getItemAtPosition(position).toString();
                        System.out.println("&&&&choosenManager  =  "+choosenManager);
                        setChoosenIdManager(choosenManager); //we found the id of the chosen manager and set it to the id opp manager
//****************************************
//                        tempManager= new Manager(choosenManager);
//                        newAppo.setIdManager(tempManager.getEmail());//find andreturn the id manager by the name of the manager by manager class function
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private void setChoosenIdManager(String choosenManagerName) {//to chang the id manager by the choosen name manager
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
                    if (currentManagerName.equals(choosenManagerName)) {
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
    }
//    private void setChoosenIdTreatment(String choosenTreatmentName) {//get id treatment by the choosen name treatment
//        FirebaseDatabase rootNode=FirebaseDatabase.getInstance();
//        referenceRoot=rootNode.getReference("Treatments");
//        referenceRoot.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                System.out.println("***********************before thr for ");
//                //go through all appointments
//                String currentTreatmentName;
//                String idTreatment="";
//                for (DataSnapshot currTreatment : snapshot.getChildren()) {
//                    //if appointment is free add to list
//                    System.out.println("***********************in the for!! ");
//                    currentTreatmentName = currTreatment.child("treatmentName").getValue().toString();
//                    if (currentTreatmentName.equals(choosenTreatmentName)) {
//                        idTreatment = currTreatment.child("idTreatment").getValue().toString();
//                        System.out.println("%%%%%%%%%%%%%%%%idTreatment= "+idTreatment);
//                    }
//                }
//                System.out.println("****************idTreatment= "+idTreatment);
//                choosenIdTreatment=idTreatment;
//                System.out.println("****************choosenIdTreatment= "+choosenIdTreatment);
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//        });
//    }

    @Override
    public void onClick(View v) {
        if (v==continueButton) {
            String emailClient = getIntent().getStringExtra("email_currntClient");
            Intent i = new Intent(BookManagerAndTreatmentActivity.this, BookDateActivity.class);
            i.putExtra("email_currntClient", emailClient);
//            i.putExtra("id_choosenTreatment", choosenIdTreatment);
            i.putExtra("id_choosenManager", choosenIdManager);
            startActivity(i);
        }
    }

}