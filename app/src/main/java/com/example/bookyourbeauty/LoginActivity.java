package com.example.bookyourbeauty;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

    String emailString;
    String passString;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setEditText();
//        email= (EditText)findViewById(R.id.EmailAddress);
//        password= (EditText) findViewById(R.id.Password);
//        login= (Button) findViewById(R.id.Login);
      //  listenButtons();

        System.out.println("********************************** ");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                emailString= email.getText().toString();
                passString= password.getText().toString();
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
                                    Toast.makeText(LoginActivity.this, "your login was success", Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(LoginActivity.this, ClientOptionsActivity.class);
                                   System.out.println("email client in login= "+emailClient);
                                    intent.putExtra("email_currentClient", emailClient);
                                    startActivity(intent);
                                    break;
                                } else {
                                    Toast.makeText(LoginActivity.this, "you type a Wrong email or password", Toast.LENGTH_SHORT).show();
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
    private void setEditText() {
        email= (EditText)findViewById(R.id.EmailAddress);
        password= (EditText) findViewById(R.id.Password);
        login= (Button) findViewById(R.id.Login);

    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.home_option, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.Home:
//                Intent i = new Intent(this, MainActivity.class);
//                startActivity(i);
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }


}
