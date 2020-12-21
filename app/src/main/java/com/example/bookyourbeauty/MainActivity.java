package com.example.bookyourbeauty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView maneger;
    ImageView client;
    TextView welcome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setImageView();
        listenImageView();
    }

    private void listenImageView() {
        maneger.setOnClickListener(this);
        client.setOnClickListener(this);
    }

    private void setImageView() {
        welcome= (TextView)findViewById(R.id.Welcome);
        maneger= (ImageView) findViewById(R.id.Manager_imageView);
        client= (ImageView) findViewById(R.id.Client_imageView);
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