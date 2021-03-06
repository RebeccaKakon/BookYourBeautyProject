package com.example.bookyourbeauty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
        Spinner managersSpinner;
        Button continueButton;

        String choosenManager ;
        String choosenIdManager;

    String emailClient;
    String choosenIdTreatment;

    FirebaseDatabase rootNode;
    DatabaseReference referenceRoot;

        Appointment newAppo=new Appointment();

        List<String> managerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_manager);
        managersSpinner = (Spinner) findViewById(R.id.manager_Spinner);
        continueButton = (Button) findViewById(R.id.Save);
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
                emailClient = getIntent().getStringExtra("email_currentClient");
                System.out.println("in book manager emailClient= "+emailClient);
                choosenIdTreatment = getIntent().getStringExtra("id_choosenTreatment");
                System.out.println("in book Manager choosenIdTreatment= "+choosenIdTreatment);

                Intent i = new Intent(BookManagerActivity.this, BookDateActivity.class);
                i.putExtra("email_currentClient", emailClient);
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
                        break;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_option, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Book_appointment:
                Intent ii= new Intent(this,BookTreatmentActivity.class);
                startActivity(ii);
                return true;
            case R.id.View_appointment:
                Intent i= new Intent(this,viewAppointment.class);
                startActivity(i);
                return true;
            case R.id.Manager_information:
                Intent iii= new Intent(this,viewInfoActivity.class);
                startActivity(iii);
                return true;
            case R.id.Home:
                Intent iiii= new Intent(this,ClientOptionsActivity.class);
                startActivity(iiii);
                return true;
            case R.id.Logout:
                Intent iiiii= new Intent(this,MainActivity.class);
                startActivity(iiiii);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}