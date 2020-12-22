//package com.example.bookyourbeauty;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.ListView;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.ArrayList;
//
//public class viewAppointment extends AppCompatActivity  implements View.OnClickListener{
//
//    ListView ViewP;
//    FirebaseDatabase rootNode;
//    DatabaseReference reference;
//    Button go;
//    ArrayList<String> arrayList=new ArrayList<>();
//    ArrayAdapter<String> arrayAdapter;
//    String item;
//    DatabaseReference referenceRoot;
//    viewAppo vie;
//    String Tnamenew="";
//
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        String clientId= getIntent().getStringExtra("email");
//
//        rootNode = FirebaseDatabase.getInstance();
//        reference = rootNode.getReference("Appointment");
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_view_appointment);
//        go= (Button) findViewById(R.id.Go) ;
//
//        System.out.println("new page");
//        arrayAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arrayList);
//        ViewP =(ListView)findViewById(R.id.viewP);
//        ViewP.setAdapter(arrayAdapter);
//        go.setOnClickListener(this);
//
//
//
//
//        referenceRoot=rootNode.getReference("Appointment");
//        referenceRoot.addListenerForSingleValueEvent(new ValueEventListener(){
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                //go through all appointments
//                String currentIdClient;
//                for (DataSnapshot s : snapshot.getChildren()) {
//                    //if appointment is free add to list
//                    currentIdClient= s.child("idClient").getValue().toString();
//                    if (currentIdClient.equals(clientId)) {
//                        String startTime=s.child("startTime").getValue().toString();
//                        String date_app=s.child("date_app").getValue().toString();
//                        String Tid=s.child("tretmantId").getValue().toString();
//                        referenceRoot=rootNode.getReference("Treatments");
//                        referenceRoot.addListenerForSingleValueEvent(new ValueEventListener(){
//                            @Override
//                            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                //go through all appointments
//                                String currentTreatid;
//                                for (DataSnapshot s : snapshot.getChildren()) {
//                                    //if appointment is free add to list
//                                    currentTreatid= s.child("idTreatment").getValue().toString();
//                                    if (currentTreatid.equals(Tid)) {
//                                        String Tname=s.child("treatmentName").getValue().toString();
////                                        arrayList.add(Tname);
//                                        Tnamenew=Tname;
////                                        nameTreatmentList.add(Tname);
//                                    }
//                                }
//
////                                arrayList.add(Tname);
//                                vie.date_app=date_app;
//                                vie.startTime=startTime;
//                                vie.Tid=Tid;
//                                vie.Tname=Tnamenew;
//                                arrayList.add(vie.toString());
//
//                                arrayAdapter.notifyDataSetChanged();
//
//                            }
//                            @Override
//                            public void onCancelled(@NonNull DatabaseError error) {
//                            }
//                        });
////                        nameTreatmentList.add(s.child("tretmantId").getValue().toString());
//                    }
//                }
//
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//        });
//
//        ViewP.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                item=(String)adapterView.getItemAtPosition(i);//This will give you the same result of viewHolder.LL.setOnClickListener as you are doing
////                id_of_business_item=id_func(item,"id_of_business=");
////                num_item=id_func(item,"Num of Product=");
//                System.out.println("item========");
//            }
//        });
//
//    }
//
//    @Override
//    public void onClick(View v) {
//        if(v==go){
//            Intent intent= new Intent(this,DeleteAppointment.class);
//            intent.putExtra("item",item);
////            intent.putExtra("id_of_business_item",id_of_business_item);
////            intent.putExtra("id_of_client",id_of_client);
//            startActivity(intent);
//
//
//
//        }
//    }
//}
//
//
