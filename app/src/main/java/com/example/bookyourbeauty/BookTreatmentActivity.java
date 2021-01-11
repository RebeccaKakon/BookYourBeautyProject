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

public class BookTreatmentActivity extends AppCompatActivity {//implements View.OnClickListener
    Spinner treatmentOptionSpinner;
    Button continueButton;

    String choosenTreatment ;
    String choosenIdTreatment;
    String emailClient;

    FirebaseDatabase rootNode;
    DatabaseReference referenceRoot;

    List<String> treatmentsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_treatment);

        emailClient= getIntent().getStringExtra("email_currentClient");
        System.out.println("in book treatment emailClient= "+emailClient);

        treatmentOptionSpinner = (Spinner) findViewById(R.id.treatmentOption_spinner);
        continueButton = (Button) findViewById(R.id.Save);

        initTreatments();

    }

    private void initTreatments() {
        treatmentsList = new ArrayList<>();
        rootNode=FirebaseDatabase.getInstance();
        referenceRoot=rootNode.getReference("Treatments");
        referenceRoot.addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //go through all appointments
                String currentTreatmentName;
                for (DataSnapshot currTreatment : snapshot.getChildren()) {
                    //if appointment is free add to list
                    currentTreatmentName= currTreatment.child("treatmentName").getValue().toString();
                    if (!treatmentsList.contains(currentTreatmentName)) {//if this current treatment is not in the list- add it
                        treatmentsList.add(currentTreatmentName);//add to the list
                    }
                }

                //make adapter to managers spinner
                ArrayAdapter<String> adapterTreatment = new ArrayAdapter<String>(BookTreatmentActivity.this, android.R.layout.simple_spinner_item, treatmentsList);
                adapterTreatment.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                treatmentOptionSpinner.setAdapter(adapterTreatment);
                System.out.println("treatments list= "+treatmentsList.toString());
                treatmentOptionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                        System.out.println("in setOnItemSelectedListener of treatment spinner+++++++++++++++++++++++++++");
                        choosenTreatment = (String)parent.getItemAtPosition(position).toString();
                        System.out.println("&&&&choosenTreatment  =  "+choosenTreatment);
                        setChoosenIdTreatment(choosenTreatment); //

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
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { System.out.println("in book Treatment choosenIdTreatment= "+choosenIdTreatment);
            Intent i = new Intent(BookTreatmentActivity.this, BookManagerActivity.class);
            i.putExtra("email_currentClient", emailClient);
            i.putExtra("id_choosenTreatment", choosenTreatment);
            startActivity(i);
            }
        });
    }

    private void setChoosenIdTreatment(String choosenTreatmentName) {//get id treatment by the choosen name treatment
        rootNode=FirebaseDatabase.getInstance();
        referenceRoot=rootNode.getReference("Treatments");
        referenceRoot.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                System.out.println("***********************before thr for ");
                //go through all appointments
                String currentTreatmentName;
                String idTreatment="";
                for (DataSnapshot currTreatment : snapshot.getChildren()) {
                    //if appointment is free add to list
                    System.out.println("***********************in the for!! ");
                    currentTreatmentName = currTreatment.child("treatmentName").getValue().toString();
                    if (currentTreatmentName.equals(choosenTreatmentName)) {
                        idTreatment = currTreatment.child("idTreatment").getValue().toString();
                        System.out.println("%%%%%%%%%%%%%%%%idTreatment= "+idTreatment);
                    }
                }
                System.out.println("****************idTreatment= "+idTreatment);
                choosenIdTreatment=idTreatment;
                System.out.println("****************choosenIdTreatment= "+choosenIdTreatment);
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