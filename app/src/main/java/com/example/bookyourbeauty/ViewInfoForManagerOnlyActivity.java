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

public class ViewInfoForManagerOnlyActivity extends AppCompatActivity implements View.OnClickListener{
    ListView ViewP;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    DatabaseReference referenceRoot;

    Button delete;
    TextView text_infoManager;
    ArrayList<String> arrayList=new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    String item;
    Info info= new Info();
    String Tnamenew="";
    String idAppo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String managerId= getIntent().getStringExtra("email");

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("ManagerInfo");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_info_for_manager_only);

        delete= (Button) findViewById(R.id.back) ;
        text_infoManager= (TextView) findViewById(R.id.textView_infoForManager) ;

        arrayAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arrayList);
        ViewP =(ListView)findViewById(R.id.viewP);
        ViewP.setAdapter(arrayAdapter);
        delete.setOnClickListener(this);

        referenceRoot=rootNode.getReference("ManagerInfo");
        referenceRoot.addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //go through all appointments

                for (DataSnapshot s : snapshot.getChildren()) {
                    String currenManagerId;

                    //if appointment is free add to list
                    currenManagerId = s.child("mid").getValue().toString();
                    if (!currenManagerId.equals(managerId)) {
                        break;
                    }
                    String ManagerInformation = s.child("information").getValue().toString();

                    info.setinformation(ManagerInformation);
                    info.setinformation(managerId);

                    arrayList.add(ManagerInformation);
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
                referenceRoot=rootNode.getReference("ManagerInfo");
                referenceRoot.addListenerForSingleValueEvent(new ValueEventListener(){
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        //go through all appointments

                        for (DataSnapshot s : snapshot.getChildren()) {
                            String currenInfo;

                            //if appointment is free add to list
                            currenInfo = s.child("information").getValue().toString();
                            if (currenInfo.equals(item)) {
                                referenceRoot.child(item).removeValue();
                                Toast.makeText(ViewInfoForManagerOnlyActivity.this, "your post wase deleted", Toast.LENGTH_SHORT).show();
                                break;


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
        if(v==delete){
            Intent intent= new Intent(this,ClientOptionsActivity.class);
//            intent.putExtra("idAppo",idAppo);
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