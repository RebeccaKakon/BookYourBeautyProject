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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class viewManagerAppointment extends AppCompatActivity implements View.OnClickListener {
    ListView ViewP;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    Button go;
    TextView text_futueAppo;
    ArrayList<String> arrayList=new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    String item;
    DatabaseReference referenceRoot;
    viewAppo vie= new viewAppo();
    String Tnamenew="";
    String idAppo;

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
        String managerId= getIntent().getStringExtra("email");

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Appointment");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_manager_appointment);
        go= (Button) findViewById(R.id.back) ;
        text_futueAppo= (TextView) findViewById(R.id.textView_futureAppo
        ) ;
        System.out.println("new page");
        arrayAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arrayList);
        ViewP =(ListView)findViewById(R.id.viewP);
        ViewP.setAdapter(arrayAdapter);
        go.setOnClickListener(this);




        referenceRoot=rootNode.getReference("Appointment");
        referenceRoot.addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //go through all appointments
                String currentIdManager;
                for (DataSnapshot s : snapshot.getChildren()) {
                    //if appointment is free add to list
                    currentIdManager= s.child("idManager").getValue().toString();
                    if (currentIdManager.equals(managerId)) {
                        String startTime=s.child("startTime").getValue().toString();
                        String date_app=s.child("date_app").getValue().toString();
                        String Tid=s.child("idTreatment").getValue().toString(); //name
                        String Aid=s.child("idAppo").getValue().toString();
                        String Cid=s.child("idClient").getValue().toString();
                        String Mid=s.child("idManager").getValue().toString();

                        referenceRoot=rootNode.getReference("Treatments");
                        referenceRoot.addListenerForSingleValueEvent(new ValueEventListener(){
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                //go through all appointments
                                String currentTreatid;
                                for (DataSnapshot s : snapshot.getChildren()) {
                                    //if appointment is free add to list
                                    currentTreatid= s.child("treatmentName").getValue().toString();
                                    if (currentTreatid.equals(Tid)) {
                                        String Tname=s.child("treatmentName").getValue().toString();
//                                        arrayList.add(Tname);
                                        Tnamenew=Tname;
//                                        nameTreatmentList.add(Tname);
                                    }
                                }

//                                arrayList.add(Tname);
                                vie.Aid=Aid;
                                vie.clientName=Cid;
                                vie.managerName=Mid;
                                vie.date_app=date_app;
                                vie.startTime=startTime;
                                vie.Tid=Tid;
                                vie.Tname=Tnamenew;
                                arrayList.add(vie.toString());
                                arrayAdapter.notifyDataSetChanged();

                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
//                        nameTreatmentList.add(s.child("tretmantId").getValue().toString());
                    }
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
                idAppo=id_func(item,"Appo id: ");
//                num_item=id_func(item,"Num of Product=");
                System.out.println("item kkkk========"+item);
                System.out.println("id app ooo========"+idAppo);
//                System.out.println("Aid========"+idAppo);
            }
        });

    }

    @Override
    public void onClick(View v) {
        if(v==go){
            referenceRoot=rootNode.getReference("Appointment");
            referenceRoot.addListenerForSingleValueEvent(new ValueEventListener(){
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    //go through all appointments
                    String currentIdAppo;
                    for (DataSnapshot s : snapshot.getChildren()) {
                        //if appointment is free add to list
                        currentIdAppo= s.child("idAppo").getValue().toString();
                        System.out.println("currid appoooo========"+currentIdAppo);
                        System.out.println("id appoooo want to delete========"+idAppo);
                        if (currentIdAppo.equals(idAppo)) {
                            referenceRoot.child(idAppo).removeValue();
                            Toast.makeText(getApplicationContext(),"delete was success", Toast.LENGTH_SHORT).show();

                        }
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
            Intent intent= new Intent(this,ManagerOptionsActivity.class);
            intent.putExtra("idAppo",idAppo);
//            intent.putExtra("id_of_business_item",id_of_business_item);
//            intent.putExtra("id_of_client",id_of_client);
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