package com.example.bookyourbeauty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ClientOptionsActivity extends AppCompatActivity implements View.OnClickListener  {
    Button profile;
    Button bookAppo;
    Button viewAppo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_options);
        setButtons();
        listenButtons();
    }
    private void listenButtons() {
        profile.setOnClickListener(this);
        bookAppo.setOnClickListener(this);
        viewAppo.setOnClickListener(this);

    }

    private void setButtons() {
        profile= (Button) findViewById(R.id.profile);
        bookAppo= (Button) findViewById(R.id.bookAppointment);
        viewAppo= (Button) findViewById(R.id.viewAppointment);
    }


    @Override
    public void onClick(View v) {
        if(v==profile){
            Intent iii= new Intent(this,MainActivity.class);//ClientProfileActivity
            startActivity(iii);
        }
        else if(v==bookAppo){
            String emailClient = getIntent().getStringExtra("email_currentClient");
            Intent ii = new Intent(this,BookTreatmentActivity.class);
            ii.putExtra("email_currentClient", emailClient);
            startActivity(ii);
        }
        else if(v==viewAppo){
            String emailClient = getIntent().getStringExtra("email_currentClient");/////////new
            Intent i = new Intent(this,ClientOptionsActivity.class);//viewAppointment
            i.putExtra("email_currentClient", emailClient);///////// new
            startActivity(i);
        }
    }
}