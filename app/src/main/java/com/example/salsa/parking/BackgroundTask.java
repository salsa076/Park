package com.example.salsa.parking;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import android.content.Intent;

/**
 * Created by salsa on 22/04/2016.
 */
public class BackgroundTask extends AsyncTask<String,Void,String> {
   AlertDialog alertDialog;
    Context ctx;

    BackgroundTask(Context ctx) {
        this.ctx = ctx;


    }

    @Override
    protected void onPreExecute() {


       alertDialog=new AlertDialog.Builder(ctx).create();
        alertDialog.setTitle("Login Information...");
    }

    @Override
    protected String doInBackground(String... params) {
        String reg_url = "http://192.168.56.1/webapp/register.php";
        String login_url = "http://192.168.56.1/webapp/login.php";
        String method=params[0];
        if (method.equals("register")) {

            String name = params[1];
            String pass = params[2];
            String num = params[3];
            try {
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("name", "UTF-8") +"="+ URLEncoder.encode(name, "UTF-8") + "&" +
                        URLEncoder.encode("password", "UTF-8") +"="+ URLEncoder.encode(pass, "UTF-8") + "&" +
                        URLEncoder.encode("numb", "UTF-8") +"="+ URLEncoder.encode(num, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                InputStream IS = httpURLConnection.getInputStream();
                IS.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        else {
            if (method.equals("login")) {

                String name = params[1];
                String password = params[2];
                try {
                    URL url = new URL(login_url);
                    HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream=httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                    String data=URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"+
                            URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();

                    InputStream inputStream=httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                    String response="";
                    String line="";

                    while ((line=bufferedReader.readLine())!=null)
                    {
                        response+=line;


                    }

                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return response;


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

        return "Registration Sucess...";
    }



    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        if (result.equals("Registration Sucess..."))
        {
            Toast.makeText(ctx,result,Toast.LENGTH_LONG).show();
        }


       else if( result.equals("Login Information..."))
        {
            Intent intent=new Intent(ctx,Menu.class);
            ctx.startActivity(intent);
        }
        else  {

            alertDialog.setMessage(result);
            alertDialog.show();
        }

    }
}
