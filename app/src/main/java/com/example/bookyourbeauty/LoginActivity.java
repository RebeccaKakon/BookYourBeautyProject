package com.example.bookyourbeauty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity     {//implements View.OnClickListener
    private EditText email;
    private EditText password;
    private Button login;

    DatabaseReference reference;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //setEditText();
        email= (EditText)findViewById(R.id.EmailAddress);
        password= (EditText) findViewById(R.id.Password);
        login= (Button) findViewById(R.id.Login);
      //  listenButtons();

        System.out.println("********************************** ");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emailString= email.getText().toString();
                String passString= password.getText().toString();
                FirebaseDatabase rootNode=FirebaseDatabase.getInstance();
                reference=rootNode.getReference("Clients");
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot postSnapshot : snapshot.getChildren() ) {
                                //from DB
                                String emailClient = (String) postSnapshot.child("email").getValue().toString();
                                String passwordClient = (String) postSnapshot.child("password").getValue().toString();

                                System.out.println("**********************emailString=  " + emailString + "emailClient=  " + emailClient);
                                System.out.println("**********************passwordString=  " + passString + "passClient=  " + passwordClient);

                                if ((emailClient.equals(emailString)) && (passwordClient.equals(passString))) {
                                    System.out.println("***********************in the if********************************");
//                                String currntClientPhone = (String) postSnapshot.child("phone").getValue().toString();
//                                System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkk"+currntClientPhone);
                                    Toast.makeText(LoginActivity.this, "your login was success", Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(LoginActivity.this, ClientOptionsActivity.class);
                                    intent.putExtra("email", emailClient);
                                    startActivity(intent);
                                    break;
                                } else {
                                    Toast.makeText(LoginActivity.this, "you typ a Wrong email or password", Toast.LENGTH_SHORT).show();
                                }
                                Intent intent2 = new Intent(v.getContext(), EnterClientActivity.class);
                                startActivity(intent2);
                                }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });




//                FirebaseDatabase rootNode=FirebaseDatabase.getInstance();
//                System.out.println("***************************************************88after getInstance ");
//
//                reference=rootNode.getReference();
//                auth= FirebaseAuth.getInstance();
//                Client client = new Client();
//                String emailString= email.getText().toString();
//                String passString= password.getText().toString();

//                auth.signInWithEmailAndPassword(emailString, passString)
//                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                if (task.isSuccessful()) {
//                                    // Sign in success, update UI with the signed-in user's information
//                                    Log.d("TAG", "signInWithEmail:success");
//                                    FirebaseUser user = auth.getCurrentUser();
//                                    updateUI(user);
//                                } else {
//                                    // If sign in fails, display a message to the user.
//                                    Log.w("TAG", "signInWithEmail:failure", task.getException());
//                                    Toast.makeText(LoginActivity.this, "Authentication failed.",
//                                            Toast.LENGTH_SHORT).show();
//                                    updateUI(null);
//                                }
//
//                                // ...
//                            }
//                        });

//                Intent intent = new Intent(v.getContext(), ClientOptionsActivity.class);
//                startActivity(intent);
            }
        });
    }

   // }
    private void setEditText() {
        email= (EditText)findViewById(R.id.EmailAddress);
        password= (EditText) findViewById(R.id.Password);
        login= (Button) findViewById(R.id.Login);

    }
    //private void listenButtons() {
        //login.setOnClickListener(this);
   // }

  //  @Override
    //public void onClick(View v) {
//        if(v==login){
//            Intent i = new Intent(this,MainActivity.class);//ClientOptionsActivity
//            startActivity(i);
//        }
   // }


}
