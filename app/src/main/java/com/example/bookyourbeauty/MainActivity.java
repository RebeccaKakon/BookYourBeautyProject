package com.example.bookyourbeauty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button maneger;
    Button client;
    TextView welcome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setButtons();
        listenButtons();
    }

    private void listenButtons() {
        maneger.setOnClickListener(this);
        client.setOnClickListener(this);
    }

    private void setButtons() {
        welcome= (TextView)findViewById(R.id.welcome);
        maneger= (Button) findViewById(R.id.maneger);
        client= (Button) findViewById(R.id.client);
    }


    @Override
    public void onClick(View v) {
        if(v==maneger){
            Intent ii= new Intent(this,EnterManegerActivity.class);
            startActivity(ii);
        }
        else if(v==client){
            Intent i = new Intent(this,EnterClientActivity.class);
            startActivity(i);
        }
    }
}