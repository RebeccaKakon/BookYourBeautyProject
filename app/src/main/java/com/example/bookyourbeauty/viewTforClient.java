package com.example.bookyourbeauty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class viewTforClient extends AppCompatActivity implements View.OnClickListener {
    ListView ViewP;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    Button back;
    ArrayList<String> arrayList=new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    String item;
    DatabaseReference referenceRoot;
    Treatment treatment= new Treatment();
    String Tnamenew="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Treatments");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tfor_client);
        back= (Button) findViewById(R.id.back) ;
        arrayAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arrayList);
        ViewP =(ListView)findViewById(R.id.viewP);
        ViewP.setAdapter(arrayAdapter);
        back.setOnClickListener(this);

        referenceRoot=rootNode.getReference("Treatments");
        referenceRoot.addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //go through all appointments

                for (DataSnapshot s : snapshot.getChildren()) {
                    String currenT;

                    //if appointment is free add to list
                    currenT = s.child("treatmentName").getValue().toString();
                    if (currenT.equals("stam")) {
                        break;
                    }
                    String Tname = s.child("treatmentName").getValue().toString();
                    String Tprice = s.child("price").getValue().toString();

                    treatment.setTreatmentName(Tname);
                    treatment.setprice(Tprice);
                    treatment.settimeT("60 minuts");

                    arrayList.add(treatment.toStringView());
                    arrayAdapter.notifyDataSetChanged();
                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }
    @Override
    public void onClick(View v) {
        if(v==back){
            Intent intent= new Intent(this,ClientOptionsActivity.class);
//            intent.putExtra("idAppo",idAppo);
//            intent.putExtra("id_of_business_item",id_of_business_item);
//            intent.putExtra("id_of_client",id_of_client);
            startActivity(intent);



        }
    }
}