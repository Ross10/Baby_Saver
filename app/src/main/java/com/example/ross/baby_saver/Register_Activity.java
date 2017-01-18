package com.example.ross.baby_saver;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Map;

public class Register_Activity extends AppCompatActivity {
    private EditText fullname,passwd,email,clue,recPasswd;
    private Button registerBtn;
    private boolean isReg = false;
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_);

        fullname = (EditText)findViewById(R.id.fullNameEditText);
        passwd = (EditText)findViewById(R.id.passwdEditText);
        recPasswd = (EditText)findViewById(R.id.confirmPasswdEditText);
        email = (EditText)findViewById(R.id.emailText);

        registerBtn = (Button)findViewById(R.id.registerBtn);
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);



    }


    public void registerPressed(View v){


       boolean isValid =  checkValidation();

        if(isValid){
//            String saveUserInput = "Full name : " + fullname.getText().toString() + " email : " + email.getText().toString() + " password : " + passwd.getText().toString() + " hint : " + clue.getText().toString();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("Email",email.getText().toString());
            editor.putString("Password",passwd.getText().toString());
            editor.commit();
            Toast.makeText(this, "Thanks - your Data has been saved", Toast.LENGTH_LONG).show();

            sendToLogin();


        }

    }

    private void sendToLogin() {
        Intent login = new Intent(this,Login_Activity.class);
        startActivity(login);

    }


    private boolean checkValidation(){
        int counter =0;
        if(fullname.getText().toString().equals("")){
            fullname.setError("השדה הזה הינו חובה");
            counter++;
        }

        if(passwd.getText().toString().equals("")){
            passwd.setError("השדה הזה הינו חובה");
            counter++;
        }


        if(recPasswd.getText().toString().equals("")){
            recPasswd.setError("השדה הזה הינו חובה");
            counter++;
        }


        if(email.getText().toString().equals("")){
            email.setError("השדה הזה הינו חובה");
            counter++;
        }

        if(!(passwd.getText().toString().equals(recPasswd.getText().toString()))){
            passwd.setError("הסיסמאות אינן זהות");
            recPasswd.setError("הסיסמאות אינן זהות");
            counter++;
        }

        if(counter>0){
            return false;
        }

        return true;
    }

}
