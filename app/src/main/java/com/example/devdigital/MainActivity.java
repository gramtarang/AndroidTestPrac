package com.example.devdigital;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Network;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    EditText username,password;
    Button callback;
    String message ;

    OkHttpClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.Et_usename);
        password = findViewById(R.id.et_password);
        callback = findViewById(R.id.bt_call);

        callback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Network().execute();

            }
        });
    }
    class Network extends AsyncTask<String,String,String> {


        @Override
        protected String doInBackground(String... strings) {

            Response response ;
            RequestBody formBody =new FormBody.Builder()
                    .add("fingerprint","")
                    .add("imei","")
                    .build();
            Request request = new  Request.Builder()
                    .header("TOKEN","")
                    .header("CLIENT-ID","")
                    .url("")
                    .post(formBody)
                    .build();

            try {
                response=client.newCall(request).execute();
                Log.d("TAG", "doInBackground: "+response);

                String responsebody = response.body().string();

                JSONObject jsonObject =new JSONObject(responsebody);

                JSONObject jsonObject1 =jsonObject.getJSONObject("bunty");


                message=jsonObject.getString("message");






            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }
    }
}
