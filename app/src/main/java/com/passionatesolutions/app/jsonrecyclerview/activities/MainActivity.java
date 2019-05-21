package com.passionatesolutions.app.jsonrecyclerview.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.passionatesolutions.app.jsonrecyclerview.R;
import com.passionatesolutions.app.jsonrecyclerview.adapters.RecyclerViewAdapter;
import com.passionatesolutions.app.jsonrecyclerview.model.School;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private final String JSON_SCHOOL_URL = "https://data.cityofnewyork.us/resource/s3k6-pzi2.json";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<School> schoolList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        schoolList = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerviewid);

        // Request Url data to update the schoolList in the recyclerView
        jsonrequest();

        setupRecyclerView(schoolList);

    }

    // Get School data from URL and Parse it
    private void jsonrequest() {

        request = new JsonArrayRequest(JSON_SCHOOL_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = null;

                for (int i = 0; i < response.length(); i++){

                    try{
                        jsonObject = response.getJSONObject(i);
                        School school = new School();
                        school.setDbn(jsonObject.getString("dbn"));
                        school.setCity(jsonObject.getString("city"));
                        school.setName(jsonObject.getString("school_name"));
                        schoolList.add(school);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                setupRecyclerView(schoolList);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(request);
    }

    private void setupRecyclerView(List<School> schoolList) {

        RecyclerViewAdapter myadapter = new RecyclerViewAdapter(this, schoolList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(myadapter);

    }
}
