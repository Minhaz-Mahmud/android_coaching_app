package com.example.betaapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.betaapp.Show.PostAdapter;
import com.example.betaapp.Show.VolleySingleton;
import com.example.betaapp.Show.item;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ShowTeacher extends AppCompatActivity {

//    Toolbar toolbar;

    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private List<item> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_teacher);

//        toolbar=findViewById(R.id.tlbar);
//        setSupportActionBar(toolbar);
//        ActionBar actionBar=getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recyler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        requestQueue = VolleySingleton.getmInstance(ShowTeacher.this).getRequestQueue();
        mList = new ArrayList<>();
        extractData();
    }


    private void extractData() {
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        String url="https://api.myjson.online/v1/records/ed49c5dd-791b-4f2e-abfd-d633f79b0b37";
        StringRequest stringRequest= new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                jsonParse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(stringRequest);
    }

    private void jsonParse(String response) {
        try {

            JSONObject jsonObject=new JSONObject(response);

            JSONArray jsonArray=jsonObject.getJSONArray("data");
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject1=jsonArray.getJSONObject(i);
                String name= jsonObject1.getString("name");
                String education= jsonObject1.getString("education");
                String imgUrl= jsonObject1.getString("imageUrl");
                item post = new item(imgUrl,name,education);
                mList.add(post);

            }

            PostAdapter adapter = new PostAdapter(ShowTeacher.this , mList);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        } catch (JSONException e) {

            e.printStackTrace();
        }
    }
}