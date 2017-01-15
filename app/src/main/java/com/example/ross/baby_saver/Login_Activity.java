package com.example.ross.baby_saver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Login_Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_login_);
    }



    public void connect(View v){
        Toast.makeText(this, "Connected", Toast.LENGTH_LONG).show();



    }


    public void register(View v){
        Toast.makeText(this, "register", Toast.LENGTH_LONG).show();
        Intent i = new Intent(this,Register_Activity.class);
        startActivity(i);



    }


    public void forgotPassword(View v){
        Toast.makeText(this, "forgot", Toast.LENGTH_LONG).show();


    }
}
