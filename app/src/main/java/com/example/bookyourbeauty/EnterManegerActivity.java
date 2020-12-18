package com.example.bookyourbeauty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EnterManegerActivity extends AppCompatActivity implements View.OnClickListener {

    Button login;
    Button register;
    TextView welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_maneger);
        setButtons();
        listenButtons();
    }


    private void listenButtons() {
        login.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    private void setButtons() {
        welcome= (TextView)findViewById(R.id.welcome);
        login= (Button) findViewById(R.id.login);
        register= (Button) findViewById(R.id.register);
    }


    @Override
    public void onClick(View v) {
        if(v==register){
            Intent ii= new Intent(this,RegisterManagerActivity.class);
            startActivity(ii);
        }
        else if(v==login){
            Intent i = new Intent(this,LoginManagerActivity.class);
            startActivity(i);
        }
    }

}