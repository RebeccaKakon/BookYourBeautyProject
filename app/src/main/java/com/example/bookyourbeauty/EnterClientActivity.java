package com.example.bookyourbeauty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EnterClientActivity extends AppCompatActivity implements View.OnClickListener {

    Button login;
    Button register;
    TextView welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_client);
        setButtons();
        listenButtons();
    }

    private void listenButtons() {
        login.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    private void setButtons() {
        welcome= (TextView)findViewById(R.id.welcome);
        login= (Button) findViewById(R.id.login);
        register= (Button) findViewById(R.id.register);
    }


    @Override
    public void onClick(View v) {
        if(v==register){
            Intent ii= new Intent(this,RegisterActivity.class);
            startActivity(ii);
        }
        else if(v==login){
            Intent i = new Intent(this,LoginActivity.class);
            startActivity(i);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_option, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Home:
                Intent i= new Intent(this,MainActivity.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}