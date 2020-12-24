package com.example.bookyourbeauty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DeleteAppointment extends AppCompatActivity implements View.OnClickListener{
    viewAppo vie;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    DatabaseReference referenceRoot;
    TextView isDelete;
    Button finish;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String idAppo= getIntent().getStringExtra("idAppo");
        System.out.println("idAppo========"+idAppo);
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Appointment");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_appointment);
        isDelete= (TextView) findViewById(R.id.isDelete) ;
        finish= (Button) findViewById(R.id.finish) ;
        finish.setOnClickListener(this);


//        rootNode = FirebaseDatabase.getInstance();
//        reference = rootNode.getReference("Appointment");
//        reference.child("idClient").setValue("-");
//        reference.child("idTreatment").setValue("-");

        referenceRoot=rootNode.getReference("Appointment");
        referenceRoot.addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //go through all appointments
                String currentIdAppo;
                for (DataSnapshot s : snapshot.getChildren()) {
                    //if appointment is free add to list
                    currentIdAppo= s.child("idAppo").getValue().toString();
                    if (currentIdAppo.equals(idAppo)) {
                        referenceRoot.child(s.getKey()).child("idClient") .setValue("-");
                        referenceRoot.child(s.getKey()).child("idTreatment") .setValue("-");

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }

    @Override
    public void onClick(View v) {
        if(v==finish){
            Intent intent= new Intent(this,ClientOptionsActivity.class);
            startActivity(intent);



        }
    }
}