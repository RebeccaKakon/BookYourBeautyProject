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

public class ManagerOptionsActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView addTreatment;
    ImageView addinformation;
    ImageView viewAppointment;
    ImageView workingTime;
    ImageView editTretment;
    ImageView whatsapp_imageView;
    TextView textView_support;

    TextView textView;
    String emailManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_options);
        emailManager= getIntent().getStringExtra("email");
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
                    Toast.makeText(ManagerOptionsActivity.this , "whatsapp is not installed!" , Toast.LENGTH_SHORT).show();
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
        workingTime.setOnClickListener(this);
        addTreatment.setOnClickListener(this);
        addinformation.setOnClickListener(this);
        viewAppointment.setOnClickListener(this);
        editTretment.setOnClickListener(this);
        whatsapp_imageView.setOnClickListener(this);


    }

    private void setButtons() {
        addTreatment= (ImageView) findViewById(R.id.imageView_addTreatment);
        addinformation= (ImageView) findViewById(R.id.imageView_addInfo);
        viewAppointment= (ImageView) findViewById(R.id.imageView_viewAppo);
        workingTime = (ImageView) findViewById(R.id.imageView_addWorkingTime);
        editTretment = (ImageView) findViewById(R.id.imageView_editTreatment);
        whatsapp_imageView= (ImageView) findViewById(R.id.Whatsapp_imageView);
        textView_support= (TextView) findViewById(R.id.TextView_technicalSupport);
        textView = (TextView) findViewById(R.id.TextView_ManagerOption);
    }


    @Override
    public void onClick(View v) {
        if(v==addTreatment){
            Intent ii= new Intent(this,AddTreatmentActivity.class);
            startActivity(ii);
        }
        else if(v==addinformation){
            Intent i = new Intent(this,AddInformation.class);
            i.putExtra("email", emailManager);
            startActivity(i);
        }
        else if(v==viewAppointment){
            Intent i = new Intent(this,viewManagerAppointment.class);
            i.putExtra("email", emailManager);
            startActivity(i);
        }
        else if(v==workingTime){
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
            case R.id.Logout:
                Intent i= new Intent(this,MainActivity.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}