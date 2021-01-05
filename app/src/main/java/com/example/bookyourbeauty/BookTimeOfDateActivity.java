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

public class BookTimeOfDateActivity extends AppCompatActivity {
    Spinner timeSpinner;
    Button continueButton;

    FirebaseDatabase rootNode;
    DatabaseReference referenceRoot;

    String emailClient;
    String choosenIdTreatment;
    String choosenIdManager;
    String choosenDate;
    String choosenTime ;


    List<String> timesList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_time_of_date);

        timeSpinner = (Spinner) findViewById(R.id.Time_spinner);
        continueButton = (Button) findViewById(R.id.Save);

        initTimes();

    }

    private void initTimes() {
        emailClient = getIntent().getStringExtra("email_currentClient");
        System.out.println("in book time emailClient= "+emailClient);
        choosenIdTreatment = getIntent().getStringExtra("id_choosenTreatment");
        choosenIdManager = getIntent().getStringExtra("id_choosenManager");
        System.out.println("in book Time choosenIdTreatment= "+choosenIdTreatment);
        System.out.println("in book Time choosenIdManager= "+choosenIdManager);
        choosenDate= getIntent().getStringExtra("id_choosenDate");
        System.out.println("in book Time choosenDate= "+choosenDate);


        System.out.println("initTimes");

        timesList = new ArrayList<>();
        rootNode=FirebaseDatabase.getInstance();
        referenceRoot=rootNode.getReference("Appointment");
        referenceRoot.addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //go through all appointments
                String currDate;
                String currStartHour;
                String currIdClient;
                for (DataSnapshot currAppo : snapshot.getChildren()) {
                    //if appointment is free add to list
                    currDate= currAppo.child("date_app").getValue().toString();
                   // currIdClient=currAppo.child("idClient").getValue().toString();
                    if (currDate.equals(choosenDate)) {//if this current date is not in the list- add it
                        currStartHour= currAppo.child("startTime").getValue().toString();
                        if (!timesList.contains(currStartHour)) { //&& !currIdClient.equals("-") //to make sure we dont add more then one && the appoinment is not taken by ather client
                            timesList.add(currStartHour);//add to the list
                        }
                    }
                }

                //make adapter to managers spinner
                ArrayAdapter<String> adapterTime = new ArrayAdapter<String>(BookTimeOfDateActivity.this, android.R.layout.simple_spinner_item, timesList);
                adapterTime.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                timeSpinner.setAdapter(adapterTime);
                System.out.println("timesList= "+timesList.toString());
                timeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                        System.out.println("in setOnItemSelectedListener of time spinner+++++++++++++++++++++++++++");
                        choosenTime = (String)parent.getItemAtPosition(position).toString();
                        System.out.println("&&&&choosenTime  =  "+choosenTime);
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
                continueButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        System.out.println("in BookTime continueButton$$$$$$$$$$$$");
//                String emailClient = getIntent().getStringExtra("email_currntClient");
//                String choosenIdTreatment = getIntent().getStringExtra("id_choosenTreatment");
//                String choosenIdManager = getIntent().getStringExtra("id_choosenManager");

                        Intent i = new Intent(BookTimeOfDateActivity.this, Book_TheAppointmentActivity.class);
                        i.putExtra("email_currentClient", emailClient);
                        i.putExtra("id_choosenTreatment", choosenIdTreatment);
                        i.putExtra("id_choosenManager", choosenIdManager);
                        i.putExtra("id_choosenDate", choosenDate);
                        i.putExtra("id_choosenTime", choosenTime);

                        startActivity(i);
                    }
                });

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }

        });
        //onclick

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