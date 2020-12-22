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

public class BookManagerActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_book_manager);
        managersSpinner = (Spinner) findViewById(R.id.manager_Spinner);
        continueButton = (Button) findViewById(R.id.ContinueButton);
        initManager();

    }

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
                ArrayAdapter<String> adapterManager = new ArrayAdapter<String>(BookManagerActivity.this, android.R.layout.simple_spinner_item, managerList);
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
        System.out.println(" finish initManagers");

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("in BookManager continueButton$$$$$$$$$$$$");
                String emailClient = getIntent().getStringExtra("email_currntClient");
                String choosenIdTreatment = getIntent().getStringExtra("choosenIdTreatment");

                Intent i = new Intent(BookManagerActivity.this, BookDateActivity.class);
                i.putExtra("email_currntClient", emailClient);
                i.putExtra("id_choosenTreatment", choosenIdTreatment);
                i.putExtra("id_choosenManager", choosenIdManager);

                startActivity(i);
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

}