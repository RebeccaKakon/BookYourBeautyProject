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

public class BookDateActivity extends AppCompatActivity {
    Spinner dateOptionSpinner;
    Button continueButton;

    String choosenDate ;
    String emailClient;
    String choosenIdTreatment;
    String choosenIdManager;

    DatabaseReference referenceRoot;
    FirebaseDatabase rootNode;

    List<String> datesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_date);

        emailClient = getIntent().getStringExtra("email_currentClient");
        System.out.println("in book Date emailClient= "+emailClient);

        choosenIdTreatment = getIntent().getStringExtra("id_choosenTreatment");
        choosenIdManager = getIntent().getStringExtra("id_choosenManager");
        dateOptionSpinner = (Spinner) findViewById(R.id.DateOptions_spinner);
        continueButton = (Button) findViewById(R.id.Save);

        initDates();

    }

    private void initDates() {
        System.out.println("initDates");
        datesList = new ArrayList<>();
        rootNode=FirebaseDatabase.getInstance();
        referenceRoot=rootNode.getReference("Appointment");
        referenceRoot.addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //go through all appointments
                String currDate;
                //String currIdClient="";
                for (DataSnapshot currAppo : snapshot.getChildren()) {
                    //if appointment is free add to list
                    currDate= currAppo.child("date_app").getValue().toString();
//                    if (currAppo.child("idClient").exists()) {
//                        currIdClient = currAppo.child("idClient").getValue().toString();
//                    }
                    if (!datesList.contains(currDate) ) {//&& !currIdClient.equals("-") //if this current date is not in the list && the appoinment is not taken by ather client- add it
                        System.out.println("***********************currDate "+currDate);
                        datesList.add(currDate);//add to the list
                    }
                }

                //make adapter to managers spinner
                ArrayAdapter<String> adapterDate = new ArrayAdapter<String>(BookDateActivity.this, android.R.layout.simple_spinner_item, datesList);
                adapterDate.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dateOptionSpinner.setAdapter(adapterDate);
                System.out.println("datesList= "+datesList.toString());
                dateOptionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                        System.out.println("in setOnItemSelectedListener of date spinner+++++++++++++++++++++++++++");
                        choosenDate = (String)parent.getItemAtPosition(position).toString();
                        System.out.println("&&&&choosenDate  =  "+choosenDate);
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
//                continueButton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        System.out.println("in BookTreatment continueButton$$$$$$$$$$$$");
//                        emailClient = getIntent().getStringExtra("email_currentClient");
//                        System.out.println("in book Date emailClient= "+emailClient);
//                        choosenIdTreatment = getIntent().getStringExtra("id_choosenTreatment");
//                        choosenIdManager = getIntent().getStringExtra("id_choosenManager");
//                        System.out.println("in book Date choosenIdTreatment= "+choosenIdTreatment);
//                        System.out.println("in book DAte choosenIdManager= "+choosenIdManager);
//                        System.out.println("in book DAte choosenDate= "+choosenDate);
//
//                        Intent i = new Intent(BookDateActivity.this, BookTimeOfDateActivity.class);
//                        i.putExtra("email_currentClient", emailClient);
//                        i.putExtra("id_choosenTreatment", choosenIdTreatment);
//                        i.putExtra("id_choosenManager", choosenIdManager);
//                        i.putExtra("id_choosenDate", choosenDate);
//
//                        startActivity(i);
//                    }
//                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }

        });
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("in BookDate continueButton$$$$$$$$$$$$");

                Intent i = new Intent(BookDateActivity.this, BookTimeOfDateActivity.class);
                i.putExtra("email_currentClient", emailClient);
                i.putExtra("id_choosenTreatment", choosenIdTreatment);
                i.putExtra("id_choosenManager", choosenIdManager);
                i.putExtra("id_choosenDate", choosenDate);

                startActivity(i);
            }
      });
    }


}