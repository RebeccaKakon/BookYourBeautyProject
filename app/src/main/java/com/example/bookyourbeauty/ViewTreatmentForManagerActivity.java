package com.example.bookyourbeauty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

public class ViewTreatmentForManagerActivity extends AppCompatActivity implements View.OnClickListener {

    ListView ViewP;
    ArrayList<String> arrayList=new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    String item;
    Treatment T= new Treatment();
    Button back;
    Button editPrice;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    DatabaseReference referenceRoot;
    String tname;
    String Memail;

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
        Memail= getIntent().getStringExtra("email");

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Treatments");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_treatment_for_manager);
        back= (Button) findViewById(R.id.back) ;
        editPrice= (Button) findViewById(R.id.editPrice) ;

        arrayAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arrayList);
        ViewP =(ListView)findViewById(R.id.viewP);
        ViewP.setAdapter(arrayAdapter);
        back.setOnClickListener(this);
        editPrice.setOnClickListener(this);


        referenceRoot=rootNode.getReference("Treatments");
        referenceRoot.addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //go through all appointments

                for (DataSnapshot s : snapshot.getChildren()) {
                    String tempTreatment=s.child("treatmentName").getValue().toString();;

                    //if appointment is free add to list

                    if (tempTreatment.equals("stam")) {
                        break;
                    }
                    String treatmentName = s.child("treatmentName").getValue().toString();
                    String treatmentPrice = s.child("price").getValue().toString();


                    T.setTreatmentName(treatmentName);
                    T.setprice(treatmentPrice);

                    arrayList.add(T.toString());
                    arrayAdapter.notifyDataSetChanged();
                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        ViewP.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                item=(String)adapterView.getItemAtPosition(i);//This will give you the same result of viewHolder.LL.setOnClickListener as you are doing
                tname=id_func(item,"treatment=");
//                System.out.println("item====== "+item);

            }
        });

    }

    @Override
    public void onClick(View v) {
        if(v== editPrice) {
            Intent intent = new Intent(this, EditTreatment.class);
                intent.putExtra("treatment_name", tname);
//            intent.putExtra("Tneame",Tname);
                startActivity(intent);
//            if (Memail.equals("yarden@gmail.com")) {
//
//                Intent intent = new Intent(this, EditTreatment.class);
//                intent.putExtra("treatment_name", tname);
////            intent.putExtra("Tneame",Tname);
//                startActivity(intent);
//            }
//            else
//                Toast.makeText(getApplicationContext(),"only yarden can change the date", Toast.LENGTH_SHORT).show();
        }
        if (v == back) {
            Intent intent = new Intent(this, ClientOptionsActivity.class);
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