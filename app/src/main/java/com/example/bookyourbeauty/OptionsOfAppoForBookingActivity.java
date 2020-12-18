package com.example.bookyourbeauty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

//ask meitar what is this class for????

public class OptionsOfAppoForBookingActivity extends AppCompatActivity implements View.OnClickListener {
    //the deitels of the founden appontment
    private int newAppoId;
    private String newAppoidClient;
    private String newAppoidManager;//idManager
    private int newAppoidTreatment;//idTreatment

    private String newAppoStartT;
    private String newAppoEndT;
    //End of the deitels of the founden appontment

    Appointment newAppo;

    private FirebaseAuth auth;
    DatabaseReference rootReference;

    private Button ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("start on creat new*********");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_of_appo_for_booking);
        ok = (Button) findViewById(R.id.Search);
        System.out.println("start to get values*********");

        String emailClient = getIntent().getStringExtra("email");
        String choosenTreatmentS = getIntent().getStringExtra("choosenTreatment");
        String choosenStartTimeS = getIntent().getStringExtra("choosenStartTime");
        String choosenEndTimeS = getIntent().getStringExtra("choosenEndTime");
        String choosenDateS = getIntent().getStringExtra("choosenDate");

        System.out.println("choosenDate  =  "+choosenDateS+"  choosenTreatmentString  =  "+choosenTreatmentS+"  choosenStartTimeS  =  "+choosenStartTimeS+" choosenEndTimeS  =  "+choosenEndTimeS);
        Treatment treatment= new Treatment(choosenTreatmentS);
        newAppoidTreatment=treatment.getIdTreatment(); //********************

        //System.out.println("@@@@@@@@@@@@emailClient=     "+emailClient);
        newAppoidClient=auth.getCurrentUser().getUid(); //********************
        System.out.println("@@@@@@@@@@@@newAppoidClient=     "+newAppoidClient);
        searchApointment();

//        ok.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                System.out.println("***********************start onClick ");
//
//            }
//        });
    }

    private void searchApointment(){
        System.out.println("***********************start to search ");
        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();

        rootReference = rootNode.getReference();
        auth = FirebaseAuth.getInstance();
        String date= "ooo";
        String date2= "ooo";
        String date34= "ooo";
        String date3= "ooo";

        newAppo = new Appointment(date,date2,date34,date3);


        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!HELP!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!//
//        newAppoId=;
//        newAppoidClient=;
//        newAppoidManager=;
//        newAppoidTreatment=;
//
//        newAppoStartT=;
//        newAppoEndT=;

        newAppo.setStartTime(newAppoStartT);
        newAppo.setEndTime(newAppoEndT);
        newAppo.setIdAppo();
        newAppo.setIdClient(newAppoidClient);
        newAppo.setIdManager(newAppoidManager);
//        newAppo.setidAppointment(newAppoidTreatment);

//                auth.createUserWithEmailAndPassword(manager.getEmail(), manager.getPassword())//?????
//                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                if (task.isSuccessful()) {
//                                    // Sign in success, update UI with the signed-in user's information
//                                    Log.d("TAG", "createUserWithEmail:success");
//                                    Toast.makeText(getApplicationContext(),"Authentication success", Toast.LENGTH_SHORT).show();
//                                    FirebaseUser user = auth.getCurrentUser();
//                                    String id = user.getUid();
//                                    reference.child("Appointment").child(id).setValue(newAppo);
//                                    //updateUI(user);
//                                } else {
//                                    // If sign in fails, display a message to the user.
//                                    Log.d("TAG", "createUserWithEmail:failure", task.getException());
//                                    Toast.makeText(getApplicationContext(),"Authentication failed", Toast.LENGTH_SHORT).show();
//                                }
//
//                                // ...
//                            }
//                        });


        Intent intent = new Intent(getApplicationContext(), ClientOptionsActivity.class);
        startActivity(intent);

//        listenButtons();
    }

    @Override
    public void onClick(View v) {
        if (v==ok)
        {

        }
    }
}


