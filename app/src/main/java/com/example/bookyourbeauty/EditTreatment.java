package com.example.bookyourbeauty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
                Intent iiii = new Intent(this, ManagerOptionsActivity.class);
                startActivity(iiii);
                return true;
            case R.id.Logout:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
