package com.example.bookyourbeauty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class viewInfoActivity extends AppCompatActivity implements View.OnClickListener{
    ListView ViewP;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    Button back;
    ArrayList<String> arrayList=new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    String item;
    DatabaseReference referenceRoot;
    Info info= new Info();
    String Tnamenew="";
    String idAppo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("ManagerInfo");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_info);
        back= (Button) findViewById(R.id.delete) ;
        arrayAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arrayList);
        ViewP =(ListView)findViewById(R.id.viewP);
        ViewP.setAdapter(arrayAdapter);
        back.setOnClickListener(this);

        referenceRoot=rootNode.getReference("ManagerInfo");
        referenceRoot.addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //go through all appointments

                for (DataSnapshot s : snapshot.getChildren()) {
                    String currenInfo;

                    //if appointment is free add to list
                    currenInfo = s.child("information").getValue().toString();
                    if (currenInfo.equals("stam")) {
                        break;
                    }
                    String ManagerInformation = s.child("information").getValue().toString();
                    String managerId =s.child("mid").getValue().toString();

                    info.setinformation(ManagerInformation);
                    info.setMId(managerId);

                    arrayList.add(info.toString());
                    arrayAdapter.notifyDataSetChanged();
                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

//        ViewP.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                item=(String)adapterView.getItemAtPosition(i);//This will give you the same result of viewHolder.LL.setOnClickListener as you are doing
//                idAppo=id_func(item,"AppoNumm");
////                num_item=id_func(item,"Num of Product=");
//                System.out.println("item========"+item);
//                System.out.println("Aid========"+idAppo);
//            }
//        });

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