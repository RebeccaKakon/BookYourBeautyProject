package com.example.bookyourbeauty;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ClientOptionsActivity extends AppCompatActivity implements View.OnClickListener  {
    //Button profile;
    ImageView bookAppo;
    ImageView viewAppo;
    ImageView viewManagerInfo;
    ImageView treatmentMenu;
    //Button whatsapp;
    ImageView whatsapp_imageView;
    TextView textView_support;
    TextView textViewC;

    String emailClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_options);

        emailClient = getIntent().getStringExtra("email_currentClient");
        System.out.println("in client options emailClient= "+emailClient);

        setButtons();
        listenButtons();

        whatsapp_imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean installed = isAppInstalled("com.whatsapp");
                String num = "+972549195952";
                String text = "Hey, I need help with BookYourBeauty app";

                if(installed)
                {
                 Intent i = new Intent(Intent.ACTION_VIEW);
                 i.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + num + "&text=" + text));
                 startActivity(i);
                }
                else{
                    Toast.makeText(ClientOptionsActivity.this , "whatsapp is not installed!" , Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private boolean isAppInstalled(String s) {
        PackageManager pk = getPackageManager();
        boolean is_installed;

        try {
            pk.getPackageInfo(s, pk.GET_ACTIVITIES);
            is_installed = true;

        } catch (PackageManager.NameNotFoundException e) {
            is_installed = false;
            e.printStackTrace();
        }

        return is_installed;
    }

    private void listenButtons() {
      //  profile.setOnClickListener(this);
        bookAppo.setOnClickListener(this);
        viewAppo.setOnClickListener(this);
        viewManagerInfo.setOnClickListener(this);
        treatmentMenu.setOnClickListener(this);
        whatsapp_imageView.setOnClickListener(this);


    }

    private void setButtons() {
      //  profile= (Button) findViewById(R.id.profile);
        bookAppo= (ImageView) findViewById(R.id.imageView_bookAppo);
        viewAppo= (ImageView) findViewById(R.id.imageView_viewAppo);
        viewManagerInfo= (ImageView) findViewById(R.id.imageView_managerInfo);
        treatmentMenu= (ImageView) findViewById(R.id.imageView_treatmentMenu);
       // whatsapp= (Button) findViewById(R.id.whatsapp);
        whatsapp_imageView= (ImageView) findViewById(R.id.Whatsapp_imageView);
        textView_support= (TextView) findViewById(R.id.TextView_technicalSupport);
        textViewC= (TextView) findViewById(R.id.TextView_clientOption);

    }


    @Override
    public void onClick(View v) {
//        if(v==profile){
//            Intent iii= new Intent(this,MainActivity.class);//ClientProfileActivity
//            startActivity(iii);
//        }
//        else
        if(v==bookAppo){
            Intent ii = new Intent(this,BookTreatmentActivity.class);
            ii.putExtra("email_currentClient", emailClient);
            startActivity(ii);
        }
        else if(v==viewAppo){
            System.out.println("2222222222222222222222in client options emailClient= "+emailClient);
            Intent i = new Intent(this,viewAppointment.class);//viewAppointment
            i.putExtra("email_currentClient", emailClient);///////// new
            startActivity(i);
        }
        else if(v==treatmentMenu){
            Intent i = new Intent(this,viewTforClient.class);//viewAppointment
            i.putExtra("email_currentClient", emailClient);///////// new
            startActivity(i);
        }
        else if(v==viewManagerInfo){
            Intent i = new Intent(this,viewInfoActivity.class);//viewAppointment
            i.putExtra("email_currentClient", emailClient);///////// new
            startActivity(i);
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
            case R.id.Logout:
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}