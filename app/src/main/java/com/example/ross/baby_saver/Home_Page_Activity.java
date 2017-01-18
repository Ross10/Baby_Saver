package com.example.ross.baby_saver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Home_Page_Activity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton todo,calender,gallery,gps,lockUser,tracking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__page_);

        todo = (ImageButton)findViewById(R.id.toDoIB);
        calender = (ImageButton)findViewById(R.id.calenderIB);
        gallery = (ImageButton)findViewById(R.id.galleryIB);
        gps = (ImageButton)findViewById(R.id.gpsIB);
        lockUser = (ImageButton)findViewById(R.id.lockUserIB);
        tracking = (ImageButton)findViewById(R.id.trackingIB);

        todo.setOnClickListener(this);
        calender.setOnClickListener(this);
        gallery.setOnClickListener(this);
        gps.setOnClickListener(this);
        lockUser.setOnClickListener(this);
        tracking.setOnClickListener(this);



    }


    @Override
    public void onClick(View v) {
        Intent homeScreen = null;

        switch(v.getId()){

            case R.id.toDoIB:
                break;

            case R.id.calenderIB:
                break;

            case R.id.galleryIB:
                homeScreen = new Intent(this,Album_Activity.class);
                break;

            case R.id.gpsIB:
                break;

            case R.id.lockUserIB:
                homeScreen = new Intent(this,Login_Activity.class);
                break;

            case R.id.trackingIB:
                break;


        }
        startActivity(homeScreen);

    }
}
