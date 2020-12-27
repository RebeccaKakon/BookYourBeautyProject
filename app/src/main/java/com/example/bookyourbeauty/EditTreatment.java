package com.example.bookyourbeauty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditTreatment extends AppCompatActivity implements View.OnClickListener {

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    DatabaseReference referenceRoot;
    Button done;
    Button edit;
    EditText newprice;
    String choosenPrice;
    public String id_func (String name,String search){
        System.out.println("name= "+name);
        int index_id=name.indexOf(search);
        index_id+=search.length();
        String answer="";
        while(name.charAt(index_id)!=',' && index_id<name.length()-1 && name.charAt(index_id)!=' '){
            System.out.print(name.charAt(index_id));
            answer+=name.charAt(index_id);
            index_id++;
        }
        System.out.println("id of busniess item is ????="+answer);
        return answer;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Treatments");
        String tname= getIntent().getStringExtra("treatment_name");
        System.out.println("^^^^^^^^^ name passed  : "+tname);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_treatment);
        done= (Button) findViewById(R.id.done) ;
        edit= (Button) findViewById(R.id.edit) ;
        newprice= (EditText) findViewById(R.id.newprice) ;



        done.setOnClickListener(this);
//        edit.setOnClickListener(this);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosenPrice = newprice.getText().toString();

                referenceRoot = rootNode.getReference("Treatments");
                referenceRoot.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                choosenPrice= newprice.getText().toString();
                        System.out.println("&&&&&& choosenPrice : " + choosenPrice);

                        //go through all appointments
                        String currentName;
                        for (DataSnapshot s : snapshot.getChildren()) {
                            //if appointment is free add to list
                            currentName = s.child("treatmentName").getValue().toString();
                            if (currentName.equals(tname)) {
                                referenceRoot.child(s.getKey()).child("price").setValue(choosenPrice);
//                        referenceRoot.child(s.getKey()).child("idTreatment") .setValue("-");

                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });

            }

        });
    }

    @Override
    public void onClick(View v) {
        if(v==done){ Intent intent= new Intent(this,ManagerOptionsActivity.class);
        startActivity(intent);
        }
        }
}
