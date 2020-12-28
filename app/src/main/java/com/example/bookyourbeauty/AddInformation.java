package com.example.bookyourbeauty;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_manger, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Add_treatment:
                Intent ii = new Intent(this, AddTreatmentActivity.class);
                startActivity(ii);
                return true;
            case R.id.Add_information:
                Intent i = new Intent(this, AddInformation.class);
                startActivity(i);
                return true;
            case R.id.View_appointment:
                Intent iii = new Intent(this, viewManagerAppointment.class);
                startActivity(iii);
                return true;
            case R.id.Add_working_time:
                Intent iiiii = new Intent(this, choosTimeAndDate.class);
                startActivity(iiiii);
                return true;
            case R.id.Edit_treatment:
                Intent iiiiii = new Intent(this, ViewTreatmentForManagerActivity.class);
                startActivity(iiiiii);
                return true;
            case R.id.Home:
                Intent iiii = new Intent(this, MainActivity.class);
                startActivity(iiii);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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