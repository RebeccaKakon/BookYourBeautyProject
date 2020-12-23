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

    String emailClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_options);

        emailClient = getIntent().getStringExtra("email_currentClient");
        System.out.println("in client options emailClient= "+emailClient);

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
            System.out.println("2222222222222222222222in client options emailClient= "+emailClient);

            Intent ii = new Intent(this,BookTreatmentActivity.class);
            ii.putExtra("email_currentClient", emailClient);
            startActivity(ii);
        }
        else if(v==viewAppo){
            Intent i = new Intent(this,ClientOptionsActivity.class);//viewAppointment
            i.putExtra("email_currentClient", emailClient);///////// new
            startActivity(i);
        }
    }
}