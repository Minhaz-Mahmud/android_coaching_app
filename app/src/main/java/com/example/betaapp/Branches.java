package com.example.betaapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Branches extends AppCompatActivity {

    Toolbar toolbar;
    ListView listView;
    ArrayList<String>branch_names;
    ArrayList<Address>addresses;

    int position =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branches);
        toolbar=findViewById(R.id.tlbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        listView=findViewById(R.id.list);

        extractData();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                sendInfo(i);
            }
        });
    }

    private void sendInfo(int i) {
        Address foundAddress= addresses.get(i);
        String Thana=foundAddress.getThana();
        String HouseNo=foundAddress.getHouseNo();
        String Mobile= foundAddress.getMobile();
        String RoadNo= foundAddress.getRoadNo();
        String Upzilla= foundAddress.getUpzilla();

      //  Toast.makeText(this, Thana+"  123 "+RoadNo, Toast.LENGTH_SHORT).show();


        Intent intent=new Intent(Branches.this, BranchDetails.class);
        intent.putExtra("Thana",Thana);
        intent.putExtra("HouseNo",HouseNo);
        intent.putExtra("Mobile",Mobile);
        intent.putExtra("RoadNo", RoadNo);
        intent.putExtra("Upazila",Upzilla);

        startActivity(intent);
    }

    private void extractData() {
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        String url="https://api.myjson.online/v1/records/66d9bd53-ac6c-4027-8d8b-f448af1a6587";
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
            branch_names=new ArrayList<>();
            addresses=new ArrayList<>();
            JSONObject jsonObject=new JSONObject(response);
            JSONObject jsonObject1=jsonObject.getJSONObject("data");
            JSONArray jsonArray=jsonObject1.getJSONArray("branches");

            for(int i=0;i<jsonArray.length();i++){

                JSONObject jsonObject2=jsonArray.getJSONObject(i);
                String Name=jsonObject2.getString("name");

                JSONObject jsonObject3=jsonObject2.getJSONObject("address");

                String thana = jsonObject3.getString("thana");
                String roadNo = jsonObject3.getString("road_no");
                String upzilla = jsonObject3.getString("upazila");
                String houseNo = jsonObject3.getString("house_no");
                String contactNo = jsonObject3.getString("contact_no");

              //  Toast.makeText(this, thana+"  123 "+roadNo, Toast.LENGTH_SHORT).show();



                Address address = new Address(thana,roadNo,upzilla,houseNo,contactNo);
                addresses.add(address);
                branch_names.add(Name);


            }
            ArrayAdapter<String> adapter=new ArrayAdapter<>(Branches.this,R.layout.list_item_layout,branch_names);
            listView.setAdapter(adapter);

        } catch (JSONException e) {

            e.printStackTrace();
        }
    }
}