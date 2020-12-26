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
                        System.out.println("newTreatment.getTreatmentName()=  "+newTreatment.getTreatmentName());
                        rootReference.child(id).setValue(newTreatment); //add to firebase//child("Treatments")

                        Toast.makeText(AddTreatmentActivity.this, "your add treatment was seccssed", Toast.LENGTH_SHORT).show();

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
}
