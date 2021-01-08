package com.example.bookyourbeauty;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import android.widget.TextView;
import org.w3c.dom.Text;

import com.google.android.gms.common.api.Api;
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

public class RegisterManagerActivity extends AppCompatActivity {
    EditText email;
    EditText first_name;
    EditText last_name;
    EditText password;
    Button save;
    TextView textRegister;

    Manager manager;

    private FirebaseAuth auth;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_manager);
//        setEditText();
        email = (EditText) findViewById(R.id.EmailAddress);
        first_name = (EditText) findViewById(R.id.FirstName);
        last_name = (EditText) findViewById(R.id.LastName);
        password = (EditText) findViewById(R.id.Password);
        save = (Button) findViewById(R.id.SaveNewManager);
        textRegister=(TextView) findViewById(R.id.textView_RegisterManager);


        //save.setOnClickListener((View.OnClickListener) this);
        System.out.println("********************************** ");

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("***********************start onClick ");
                FirebaseDatabase rootNode=FirebaseDatabase.getInstance();

                reference=rootNode.getReference();
                auth=FirebaseAuth.getInstance();
                manager = new Manager();
                String firstName= first_name.getText().toString();
                String emaill= email.getText().toString();
                String pass= password.getText().toString();
                String lastname= last_name.getText().toString();
                manager.setEmail(emaill);
                manager.setPassword(pass);
                manager.setFirst_name(firstName);
                manager.setLast_name(lastname);
                auth.createUserWithEmailAndPassword(manager.getEmail(), manager.getPassword())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @RequiresApi(api = Build.VERSION_CODES.O)
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("TAG", "createUserWithEmail:success");
                                    Toast.makeText(getApplicationContext(),"Authentication success", Toast.LENGTH_SHORT).show();
                                    FirebaseUser user = auth.getCurrentUser();
                                    String id = user.getUid();
                                    reference.child("Managers").child(id).setValue(manager);
                                    createChannel();
                                    //updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.d("TAG", "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(getApplicationContext(),"Authentication failed", Toast.LENGTH_SHORT).show();
                                }

                                // ...
                            }
                        });


                Intent intent = new Intent(v.getContext(), LoginManagerActivity.class);
                startActivity(intent);

//        listenButtons();
            }
        });
    }

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
//                Intent i= new Intent(this,MainActivity.class);
//                startActivity(i);
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createChannel()
    {
        NotificationManager mNotificationManager=getSystemService(NotificationManager.class);
        String id = "CHANNEL";
        CharSequence name = "Welcome channel for manager";
        String description = "This is the welcome channel";
        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel mChannel = new NotificationChannel(id, name, importance);
        mChannel.setDescription(description);
        mNotificationManager.createNotificationChannel(mChannel);
        addNotification(id);
    }

    private void addNotification(String channel)
    {
        Notification.Builder notificationBuilder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            notificationBuilder = new Notification.Builder(this, channel);
        }
        else
        {
            notificationBuilder = new Notification.Builder(this);
        }

        Intent landingIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingLandingIntent = PendingIntent.getActivity(this, 0,
                landingIntent,0);
        String text="Glad you joined us!\n Do not forget to update your activity days (:";

        Notification notification = notificationBuilder
                .setContentTitle("Welcome to BookYourBeauty")
                .setSmallIcon(R.drawable.ic_notifications)
                .setContentIntent(pendingLandingIntent)
                .setAutoCancel(true)
                .setStyle(new Notification.BigTextStyle().bigText(text))
                .setContentText(text).build();
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify((int) System.currentTimeMillis(), notification);
    }


}