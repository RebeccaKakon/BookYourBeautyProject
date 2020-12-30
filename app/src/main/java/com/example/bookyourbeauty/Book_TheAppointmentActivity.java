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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;

public class Book_TheAppointmentActivity extends AppCompatActivity {

    DatabaseReference referenceRoot;
    FirebaseDatabase rootNode;
    String emailClient;
    String choosenIdTreatment;
    String choosenIdManager;
    String choosenDate;
    String choosenTime;

    Button ok_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book__the_appointment);
        ok_button = (Button) findViewById(R.id.Ok_button);


        emailClient = getIntent().getStringExtra("email_currentClient");
        System.out.println("in book appo emailClient= "+emailClient);
        choosenIdTreatment = getIntent().getStringExtra("id_choosenTreatment");
        choosenIdManager = getIntent().getStringExtra("id_choosenManager");
        System.out.println("in book appo choosenIdTreatment= "+choosenIdTreatment);
        System.out.println("in book appo choosenIdManager= "+choosenIdManager);

        choosenDate= getIntent().getStringExtra("id_choosenDate");
        System.out.println("in book appo choosenDate= "+choosenDate);

        choosenTime= getIntent().getStringExtra("id_choosenTime");
        System.out.println("in book appo choosenTime= "+choosenTime);


        ok_button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                System.out.println("nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn");
                findIdAppo_And_SetIDClient(emailClient,choosenIdTreatment,choosenIdManager,choosenDate,choosenTime);
                createChannel();
                Intent intent = new Intent(Book_TheAppointmentActivity.this, ClientOptionsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void findIdAppo_And_SetIDClient(String emailClient, String choosenIdTreatment, String choosenIdManager, String choosenDate, String choosenTime) {

        rootNode= FirebaseDatabase.getInstance();
        referenceRoot=rootNode.getReference("Appointment");
        referenceRoot.addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //go through all appointments
                String currManager;
                String currDate;
                String currStartHour;
                for (DataSnapshot currAppo : snapshot.getChildren()) {
                    //if appointment is free add to list
                    currManager= currAppo.child("idManager").getValue().toString();
                    if (currManager.equals(choosenIdManager)) {
                        currDate = currAppo.child("date_app").getValue().toString();
                        if (currDate.equals(choosenDate)) {//if this current date is not in the list- add it
                            currStartHour = currAppo.child("startTime").getValue().toString();
                            if (currStartHour.equals(choosenTime)) {//if this current date is not in the list- add it
                                System.out.println("currAppo.getKey()= "+currAppo.getKey());
                                referenceRoot.child(currAppo.getKey()).child("idClient").setValue(emailClient);
                                System.out.println("before id Treatment&&&&&&&&&&&&&7   idTreatment="+choosenIdTreatment);
                                referenceRoot.child(currAppo.getKey()).child("idTreatment").setValue(choosenIdTreatment);
                                System.out.println("after id Treatment&&&&&&&&&&&&&7");
                                break;
                                // currAppo.child("idTreatment").onUpdate(choosenIdTreatment);//chang the treatment id
                                //chang the client id
                            }
                        }
                    }


                }
                Toast.makeText(Book_TheAppointmentActivity.this, "your add your new appointment seccssed", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(Book_TheAppointmentActivity.this, ClientOptionsActivity.class);
//                startActivity(intent);
//
//                ok_button.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        System.out.println("nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn");
//                        Intent intent = new Intent(Book_TheAppointmentActivity.this, ClientOptionsActivity.class);
//                        startActivity(intent);
//                    }
//                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }

        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createChannel()
    {
        NotificationManager mNotificationManager=getSystemService(NotificationManager.class);
        String id = "CHANNEL";
        CharSequence name = "channel appointment";
        String description = "This is the channel appointment";
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

        String text="Your appointment has been successfully registered!\n Do not forget to come with a mask.";
        Notification notification = notificationBuilder
                .setContentTitle("Appointment confirmation")
                .setSmallIcon(R.drawable.ic_notifications)
                .setContentIntent(pendingLandingIntent)
                .setAutoCancel(true)
                .setStyle(new Notification.BigTextStyle().bigText(text))
                .setContentText(text).build();
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify((int) System.currentTimeMillis(), notification);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_option, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Book_appointment:
                Intent ii= new Intent(this,BookTreatmentActivity.class);
                startActivity(ii);
                return true;
            case R.id.View_appointment:
                Intent i= new Intent(this,viewAppointment.class);
                startActivity(i);
                return true;
            case R.id.Manager_information:
                Intent iii= new Intent(this,viewInfoActivity.class);
                startActivity(iii);
                return true;
            case R.id.Home:
                Intent iiii= new Intent(this,MainActivity.class);
                startActivity(iiii);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}