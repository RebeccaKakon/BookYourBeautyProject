package com.example.bookyourbeauty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddInformation extends AppCompatActivity {//implements View.OnClickListener

    Button wieInfoo;
    EditText addInfo;
    Button add;
    DatabaseReference rootReference;
    FirebaseDatabase rootNode;
    Info info;
    private FirebaseAuth auth;
    String newInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String managerId= getIntent().getStringExtra("email");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_information);

        addInfo = (EditText) findViewById(R.id.addInfo);
        add = (Button) findViewById(R.id.add);
        wieInfoo= (Button) findViewById(R.id.wieInfoo);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase rootNode=FirebaseDatabase.getInstance();

                rootReference=rootNode.getReference();
                auth=FirebaseAuth.getInstance();
                info = new Info();
                String managerinfo= addInfo.getText().toString();


                info.setinformation(managerinfo);
                info.setMId(managerId);
                wieInfoo.setOnClickListener(this);



                rootNode=FirebaseDatabase.getInstance();
                rootReference=rootNode.getReference("ManagerInfo");
                rootReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot postSnapshot : snapshot.getChildren() ) {
                            //from DB
                            String Temp= (String)postSnapshot.child("information").getValue().toString();
//
                            if ((Temp.equals(managerinfo)) ) {
                                Toast.makeText(AddInformation.this, "you already have this info", Toast.LENGTH_SHORT).show();
                                break;
                            }

                        }

//                        newAppointment.setIdAppo();

                        String id=managerinfo;

                        rootReference.child(id).setValue(info); //add to firebase//child("Treatments")
                        Toast.makeText(AddInformation.this, "your info was seccssfully added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AddInformation.this, ManagerOptionsActivity.class);
                        startActivity(intent);
                    }



                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });



            }

        });
        wieInfoo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AddInformation.this, ViewInfoForManagerOnlyActivity.class);
                i.putExtra("email", managerId);
                startActivity(i);

            }



        });
    }
//    @Override
//    public void onClick(View v) {
//        if(v==wieInfoo){
//            Intent intent= new Intent(this,ViewInfoForManagerOnlyActivity.class);
//
//            startActivity(intent);
//
//
//
//        }
//    }
}