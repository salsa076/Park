package com.example.salsa.parking;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Created by salsa on 22/04/2016.
 */
public class Register extends Activity {
        EditText ET_NAME,ET_PASS,ET_NUM;
        String name,password,numb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        ET_NAME=(EditText)findViewById(R.id.name);
        ET_PASS=(EditText)findViewById(R.id.pass);
        ET_NUM=(EditText)findViewById((R.id.num));
    }


    public void userReg(View view){

        name=ET_NAME.getText().toString();
        password=ET_PASS.getText().toString();
        numb=ET_NUM.getText().toString();

        String method="register";
        BackgroundTask backgroundTask=new BackgroundTask(this);
        backgroundTask.execute(method,name,password,numb);
        finish();

    }
}
