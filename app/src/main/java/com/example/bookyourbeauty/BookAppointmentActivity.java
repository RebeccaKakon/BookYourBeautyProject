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

import android.widget.Spinner;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class BookAppointmentActivity extends AppCompatActivity {//implements View.OnClickListener

    Spinner treatmentOptionSpinner;
    Spinner managersSpinner;
//    Spinner dateOptionSpinner;
    Spinner startTimeSpinner;
    Spinner endTimeSpinner;
    EditText dateOption;
    Button search;


    //the deitels of the choosen booking appontment
    String choosenManager ;
    String choosenTreatment ;
    String  choosenDate;
    String choosenStartTime ;
    String choosenEndTime;
    //End of the deitels of the choosen booking appontment

    private FirebaseAuth auth;
    DatabaseReference reference;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        managersSpinner = (Spinner) findViewById(R.id.manager_Spinner);
        treatmentOptionSpinner = (Spinner) findViewById(R.id.treatmentOption_spinner);
        startTimeSpinner = (Spinner) findViewById(R.id.startTime_Spinner);
        endTimeSpinner = (Spinner) findViewById(R.id.endTime_Spinner);
        dateOption=(EditText) findViewById(R.id.DateOption);
        search = (Button) findViewById(R.id.Search);


        //kinds of managers
        String[] managersArray= {"manager",""};
        ArrayAdapter<String> adpManager= new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,managersArray);
        adpManager.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        managersSpinner.setAdapter(adpManager);

        managersSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                choosenManager = (String) parent.getItemAtPosition(position);
                System.out.println("&&&&choosenManager  =  "+choosenManager);

            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });



        //kinds of treatments
        String[] treatmentOptionArray= {"treatmentOption","Nails gel polish", "Eyebrow wax","Legs and hands wax","Manicure","Pedicure"};
        ArrayAdapter<String> adpTreatment= new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,treatmentOptionArray);
        adpTreatment.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        treatmentOptionSpinner.setAdapter(adpTreatment);

        treatmentOptionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                choosenTreatment = (String) parent.getItemAtPosition(position);
                System.out.println("&&&&choosenTreatment  =  "+choosenTreatment);

            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //date
        choosenDate= dateOption.getText().toString();
        System.out.println("&&&&choosenDate  =  "+choosenDate);

        //start time
        String[] startTimeArray= {"start Time","7:00","8:00", "9:00","10:00","11:00","12:00","13:00",
                "14:00","15:00","16:00"};
        ArrayAdapter<String> adpStartT= new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,startTimeArray);
        adpStartT.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        startTimeSpinner.setAdapter(adpStartT);

        startTimeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                choosenStartTime = (String) parent.getItemAtPosition(position);
                System.out.println("&&&&choosenStartTime  =  "+choosenStartTime);

            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //end time
        String[] endTimeArray= {"end Time","8:00", "9:00","10:00","11:00","12:00","13:00",
                "14:00","15:00","16:00"};
        ArrayAdapter<String> adpEndT= new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,endTimeArray);
        adpEndT.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        endTimeSpinner.setAdapter(adpEndT);
        endTimeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                choosenEndTime = (String) endTimeSpinner.getAdapter().getItem(position);
                System.out.println("&&&&choosenEndTime  =  "+choosenEndTime);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("***********************start onClick ");
                String emailClient = getIntent().getStringExtra("email");

                Intent intent =new Intent(getApplicationContext(),OptionsOfAppoForBookingActivity.class);//v.getContext()
                intent.putExtra("email", emailClient);
                intent.putExtra("choosenTreatment",choosenTreatment);
                intent.putExtra("choosenDate",choosenDate);
                intent.putExtra("choosenStartTime",choosenStartTime);
                intent.putExtra("choosenEndTime",choosenEndTime);
                startActivity(intent);
            }
        });

    }
}