package com.example.bookyourbeauty;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ManagerOptionsActivity extends AppCompatActivity implements View.OnClickListener{

    Button AddTreatment;
    Button Addinformation;
    Button viewAppointment;
    Button calendar;
    String emailManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_options);
        emailManager= getIntent().getStringExtra("email");
        setButtons();
        listenButtons();
    }


    private void listenButtons() {
        calendar.setOnClickListener(this);
        AddTreatment.setOnClickListener(this);
        Addinformation.setOnClickListener(this);
        viewAppointment.setOnClickListener(this);

    }

    private void setButtons() {
        AddTreatment= (Button) findViewById(R.id.AddTreatment);
        Addinformation= (Button) findViewById(R.id.information);
        viewAppointment= (Button) findViewById(R.id.viewAppointment);
        calendar = (Button) findViewById(R.id.Calender);
    }


    @Override
    public void onClick(View v) {
        if(v==AddTreatment){
            Intent ii= new Intent(this,AddTreatmentActivity.class);
            startActivity(ii);
        }
        else if(v==Addinformation){
            Intent i = new Intent(this,AddInformation.class);
            i.putExtra("email", emailManager);
            startActivity(i);
        }
        else if(v==viewAppointment){
            Intent i = new Intent(this,viewManagerAppointment.class);
            i.putExtra("email", emailManager);
            startActivity(i);
        }
        else if(v==calendar){
            Intent i = new Intent(this,choosTimeAndDate.class);
            i.putExtra("email", emailManager);

            startActivity(i);
        }
    }
}