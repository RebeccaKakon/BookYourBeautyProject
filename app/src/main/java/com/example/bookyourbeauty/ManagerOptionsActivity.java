package com.example.bookyourbeauty;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ManagerOptionsActivity extends AppCompatActivity implements View.OnClickListener{

    Button AddTreatment;
    Button EditSales;
    Button salaryCalculator;
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
        EditSales.setOnClickListener(this);
        salaryCalculator.setOnClickListener(this);

    }

    private void setButtons() {
        AddTreatment= (Button) findViewById(R.id.AddTreatment);
        EditSales= (Button) findViewById(R.id.EditSales);
        salaryCalculator= (Button) findViewById(R.id.SalaryCalculator);
       calendar = (Button) findViewById(R.id.Calender);
    }


    @Override
    public void onClick(View v) {
        if(v==AddTreatment){
            Intent ii= new Intent(this,AddTreatmentActivity.class);
            startActivity(ii);
        }
        else if(v==EditSales){
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
        }
        else if(v==salaryCalculator){
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
        }
        else if(v==calendar){
            Intent i = new Intent(this,choosTimeAndDate.class);
            i.putExtra("email", emailManager);

            startActivity(i);
        }
    }
}