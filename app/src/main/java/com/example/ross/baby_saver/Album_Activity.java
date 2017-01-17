package com.example.ross.baby_saver;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Time;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Sayag on 16/01/2017.
 */


public class Album_Activity extends AppCompatActivity
{
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_ACTIVITY_CALLBACK = 2;
    private Bitmap imageBitmap;
    private ImageView cameraIV;
    private File directory;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        //TODO load the album from the internal storage
        cameraIV = (ImageView)findViewById(R.id.cameraImageView);


        //testing area
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        directory = cw.getDir("BabySaver", Context.MODE_PRIVATE);
        String path = directory.getPath();
        loadImageFromStorage(path);

        ////////////////////////////////////////////////////////////
//        File mydir = this.getDir("BabySaver", Context.MODE_PRIVATE); //Creating an internal dir;
//        if (!mydir.exists())
//        {
//            mydir.mkdirs();
//        }
    }



    public void openCamera(View v)
    {
        Intent takePicIntante = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); // create a new intent for cameraIV transfer between activities.
        if(takePicIntante.resolveActivity(getPackageManager()) != null)
            startActivityForResult(takePicIntante,REQUEST_IMAGE_CAPTURE);// callback function
    }


    //this function will call right after the startActivityForResult and it will happend after the camera will finish her job and want to transfer the data to our activity.
    protected void onActivityResult(int requestCode, int resultCode, Intent data) // override the result function must implement with startActivityForResult
    {
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap)extras.get("data");
            //cameraIV.setImageBitmap(imageBitmap);
            //TODO Here i need to save images into the local storage
            // TODO refresh the album and redisplayed it.


            //saveToInternalStorage(imageBitmap);
            String path = directory.getPath();

            loadImageFromStorage(path);


        }
        else if(requestCode==REQUEST_ACTIVITY_CALLBACK){

            Toast.makeText(this, "No image was captured, Try again", Toast.LENGTH_SHORT).show();
        }


    }





    private String saveToInternalStorage(Bitmap bitmapImage){

        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+1:00"));
        Date currentLocalTime = cal.getTime();
        DateFormat date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
// you can get seconds by adding  "...:ss" to it
        //date.setTimeZone(TimeZone.getTimeZone("GMT+1:00"));

        String imageID = date.format(currentLocalTime);


        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory,imageID);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }







    private void loadImageFromStorage(String path)
    {


        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                System.out.println(file.getName());
            }
        }


        try {
            File f=new File(path, "profile.jpg");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            ImageView img=(ImageView)findViewById(R.id.iv1);
            img.setImageBitmap(b);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

    }


}



