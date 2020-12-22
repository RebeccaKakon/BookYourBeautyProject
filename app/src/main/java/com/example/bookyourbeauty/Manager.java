package com.example.bookyourbeauty;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;

public class Manager {
    private String email;
    private String first_name;
    private String last_name;
    private String password;

    public Manager(){}

    public Manager (String first_name, String email, String password,String last_name){
        this.first_name=first_name;
        this.email=email;
        this.last_name=last_name;
        this.password=password;
    }
    public Manager(String currManagerName) {
        System.out.println(" in Manager constractor with a name");
        System.out.println("***********************the get currManagerName=  "+currManagerName);

        final String[] idM = new String[1];
        System.out.println("getIdManagerByManagerName");
        FirebaseDatabase rootNode=FirebaseDatabase.getInstance();
        DatabaseReference referenceRoot = rootNode.getReference("Managers");
        referenceRoot.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //go through all appointments
                String currentManagerName;
                String idManager="";
                for (DataSnapshot s : snapshot.getChildren()) {
                    //if appointment is free add to list
                    currentManagerName = s.child("first_name").getValue().toString();
                    if (currentManagerName.equals(currManagerName)) {
                        idManager = s.child("email").getValue().toString();
                        System.out.println("%%%%%%%%%%%%%%%%idManager= "+idManager);
                    }
                }
                idM[0] =idManager;
                System.out.println("****************currManagerName= "+currManagerName);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        System.out.println("****************idM= "+ idM[0]);

        this.email= idM[0];
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    @Override
    public String toString() {
        return "Client{" +
                "email='" + email + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


}
