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

public class AddTreatmentActivity extends AppCompatActivity {
    private Button addTreatment;
    private EditText treatmentName;
    private String choosentreatmentName;
    String choosenPrice;
    private DatabaseReference rootReference;
    private FirebaseDatabase rootNode;
    EditText treatmenPrice;

    Treatment newTreatment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_treatment);
        addTreatment = (Button) findViewById(R.id.AddTreatment);
        treatmentName = (EditText) findViewById(R.id.TreatmentName);
        treatmenPrice = (EditText) findViewById(R.id.treatmenPrice);



        addTreatment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("***********************start onClick ");
                rootNode=FirebaseDatabase.getInstance();

                choosentreatmentName= treatmentName.getText().toString();
                choosenPrice= treatmenPrice.getText().toString();


                rootReference=rootNode.getReference();
                newTreatment = new Treatment(choosentreatmentName,choosenPrice);

                rootNode=FirebaseDatabase.getInstance();
                rootReference=rootNode.getReference("Treatments");
                rootReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot postSnapshot : snapshot.getChildren() ) {
                            //from DB
                            String treatmentTempName = (String) postSnapshot.child("treatmentName").getValue().toString();
                            if ((treatmentTempName.equals(choosentreatmentName)) ) {
                                Toast.makeText(AddTreatmentActivity.this, "you already treatment with the same name", Toast.LENGTH_SHORT).show();
                                break;
                            }
                        }

                        String id=choosentreatmentName;
                        newTreatment.settimeT("60 minuts");
                        System.out.println("newTreatment.getTreatmentName()=  "+newTreatment.getTreatmentName());
                        rootReference.child(id).setValue(newTreatment); //add to firebase//child("Treatments")

                        Toast.makeText(AddTreatmentActivity.this, "your add treatment was succeed", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(AddTreatmentActivity.this, ManagerOptionsActivity.class);
                        startActivity(intent);
                    }

                   @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
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
                Intent ii= new Intent(this,AddTreatmentActivity.class);
                startActivity(ii);
                return true;
            case R.id.Add_information:
                Intent i= new Intent(this,AddInformation.class);
                startActivity(i);
                return true;
            case R.id.View_appointment:
                Intent iii= new Intent(this,viewManagerAppointment.class);
                startActivity(iii);
                return true;
            case R.id.Add_working_time:
                Intent iiiii= new Intent(this,choosTimeAndDate.class);
                startActivity(iiiii);
                return true;
            case R.id.Edit_treatment:
                Intent iiiiii= new Intent(this,ViewTreatmentForManagerActivity.class);
                startActivity(iiiiii);
                return true;
            case R.id.Home:
                Intent iiii= new Intent(this,ManagerOptionsActivity.class);
                startActivity(iiii);
                return true;
            case R.id.Logout:
                Intent intent= new Intent(this,MainActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
