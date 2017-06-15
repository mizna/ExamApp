package com.baabtra.mizna.examapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    EditText unameView,pwordView;
    TextView alertView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onLoginClicked(View v){
        unameView=(EditText)findViewById(R.id.username);
        pwordView=(EditText)findViewById(R.id.password);
        String uname=unameView.getText().toString().trim();
        String pword=pwordView.getText().toString().trim();
        String url="http://13.76.209.146/BM-44259/Exam_webservice/testlogin.php?phone="+uname+"&pword="+pword;
        JsonArrayRequest myrequest=new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject myobject;
                try{
                    myobject=response.getJSONObject(0);
                    if(myobject.getString("ResponseCode").equals("404")){
                        alertView=(TextView)findViewById(R.id.alertview);
                        alertView.setText("Invalid Credentials!!");
                    }
                    else
                    {
                        Intent intent=new Intent(MainActivity.this,HomePage.class);
                        System.out.println("This is mainactivity");
                        startActivity(intent);
                    }
                }
                catch(Exception e){
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }
        });
        RequestQueue myqueue= Volley.newRequestQueue(this);
        myqueue.add(myrequest);}
}
