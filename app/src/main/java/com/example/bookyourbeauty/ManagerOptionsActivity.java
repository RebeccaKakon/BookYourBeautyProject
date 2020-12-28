package com.example.bookyourbeauty;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ManagerOptionsActivity extends AppCompatActivity implements View.OnClickListener{

    Button AddTreatment;
    Button Addinformation;
    Button viewAppointment;
    Button calendar;
    Button editTretment;
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
        editTretment.setOnClickListener(this);

    }

    private void setButtons() {
        AddTreatment= (Button) findViewById(R.id.AddTreatment);
        Addinformation= (Button) findViewById(R.id.information);
        viewAppointment= (Button) findViewById(R.id.viewAppointment);
        calendar = (Button) findViewById(R.id.Calender);
        editTretment = (Button) findViewById(R.id.editTretment);
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
        else if(v==editTretment){
            Intent intent=new Intent(this,ViewTreatmentForManagerActivity.class);
            intent.putExtra("email", emailManager);
            startActivity(intent);
//            openDialog();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_option, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Home:
                Intent i= new Intent(this,MainActivity.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


//    public void openDialog(){
//        Dialog dialog= new Dialog();
////        dialog.setContenView(R.layout.layout_dialog);
//        dialog.setCancelable(true);
//        Button edditTreatment=(Button) findViewById(R.id.edditTreatment);
//        Button viewTreatment=(Button) findViewById(R.id.viewTreatment);
//
//        edditTreatment.setOnClickListener((view) -> {
//            Intent intent=new Intent(this,EditTreatment.class);
//            startActivity(intent);
//
//        });
//        viewTreatment.setOnClickListener((view) -> {
//            Intent intent=new Intent(this,ViewTreatmentForManagerActivity.class);
//            startActivity(intent);
//
//        });
//
//        dialog.show(getSupportFragmentManager(), "treatment");
//    }
}