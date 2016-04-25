package com.example.salsa.parking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText ET_Name,ET_Pass;
    String name,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ET_Name=(EditText)findViewById(R.id.name);
        ET_Pass=(EditText)findViewById(R.id.password);

    }

    public void userReg(View view){

        startActivity(new Intent(this,Register.class));
    }

    public void userLogin(View view){

            name=ET_Name.getText().toString();
            password=ET_Pass.getText().toString();
            String method="login";
            BackgroundTask backgroundTask=new BackgroundTask(this);
            backgroundTask.execute(method,name,password);


    }




}
