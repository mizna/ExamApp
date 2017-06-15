package com.baabtra.mizna.examapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity {
    RecyclerView recycler;
    CustomAdapter adapter;
    GridLayoutManager layoutmanager;
    List<Countrydata> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        recycler=(RecyclerView)findViewById(R.id.recyclerView);
        System.out.println("Message:Recycler accessed");
        list=new ArrayList<Countrydata>();
        layoutmanager=new GridLayoutManager(HomePage.this,1);
        System.out.println("Message:Layoutmanager instatiated");
        recycler.setLayoutManager(layoutmanager);
       // recycler.setLayoutManager(new LinearLayoutManager(recycler.getContext()));
        System.out.println("Message:Layout manager set");
        server_filled_list();
        System.out.println("Message:After filling list");
        adapter=new CustomAdapter(HomePage.this,list);
        System.out.println("Messsage:After setting adapter");
        recycler.setAdapter(adapter);

    }

    private void server_filled_list() {
        String url="http://androidbegin.com/tutorial/jsonparsetutorial.txt";
        JsonObjectRequest myrequest=new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
               try{
                   //System.out.println("Error is"+response.toString());
                   JSONArray myarray=response.getJSONArray("worldpopulation");
                   JSONObject myobject;
                  for(int i=0;i<myarray.length();i++)
                   {
                       myobject=myarray.getJSONObject(0);
                       Countrydata obj=new Countrydata(myobject.getInt("rank"),myobject.getString("country"),
                               myobject.getString("population"),myobject.getString("flag"));
                       System.out.println("Message:JSON Array "+myobject.getString("country"));
                       list.add(obj);
                       System.out.println("Message:LIST IS "+list.get(i).getCountry().toString());
                   }
               }
               catch (Exception e){
                   e.printStackTrace();
                   System.out.println("Iam here");
               }

                adapter.notifyDataSetChanged();
                System.out.println("Message:Set notify change");

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }
        });
        RequestQueue myqueue= Volley.newRequestQueue(this);
        myqueue.add(myrequest);

    }
}
