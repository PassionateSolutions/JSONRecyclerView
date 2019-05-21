package com.passionatesolutions.app.jsonrecyclerview.activities;

import android.app.Instrumentation;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.passionatesolutions.app.jsonrecyclerview.R;
import com.passionatesolutions.app.jsonrecyclerview.adapters.RecyclerViewAdapterSAT;
import com.passionatesolutions.app.jsonrecyclerview.model.SATscore;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SATActivity extends AppCompatActivity {

    private final String JSON_SAT_URL = "https://data.cityofnewyork.us/resource/f9bf-2cp4.json";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<SATscore> satList;
    private RecyclerView recyclerView;
    private RecyclerViewAdapterSAT myadapter;
    private SearchView searchView;
    private String schoolDbn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sat);

        satList = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerviewSAT);

        searchView = findViewById(R.id.searchID);

        // implement SAT score data into recyclerView
        jsonrequest();

        // Gets text from first activity
        schoolDbn = getIntent().getExtras().getString("dbn");

        // put schoolDbn data from previous activity into SearchView
        searchView.onActionViewExpanded();
        searchView.setQuery(schoolDbn, true);
        // if I had more time I would make the text "Click search icon to search"
        // only appear when the keyboard was on screen.


        // code to allow list to be searchable
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                myadapter.getFilter().filter(s);
                // clears keyboard after text has been submitted
                searchView.clearFocus();
                // return true allows text to be submitted to search
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s2) {
                myadapter.getFilter().filter(s2);
                return true;
            }
        });


        //--------------------------------

        // Programmatically presses the enter key for testing


//            new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Instrumentation inst = new Instrumentation();
//                    for ( int i = 0; i < 10; ++i ) {
//                        inst.sendKeyDownUpSync(KeyEvent.KEYCODE_ENTER);
//                        Thread.sleep(100);
//                    }
//                }
//                catch(InterruptedException e){
//                }
//            }
//        }).start();

        //--------------------------------

        setupRecyclerView(satList);




        }


    // Get SAT Score data from URL and Parse it

    private void jsonrequest() {



        request = new JsonArrayRequest(JSON_SAT_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = null;

                for (int i = 0; i < response.length(); i++) {

                    try {

                        jsonObject = response.getJSONObject(i);
                        SATscore satScore = new SATscore();
                        satScore.setDbn(jsonObject.getString("dbn"));
                        satScore.setSatCriticalReadingAvgScore(jsonObject.getString("sat_critical_reading_avg_score"));
                        satScore.setSatMathAvgScore(jsonObject.getString("sat_math_avg_score"));
                        satScore.setSatWritingAvgScore(jsonObject.getString("sat_writing_avg_score"));
                        satScore.setSchoolName(jsonObject.getString("school_name"));
                        satList.add(satScore);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                setupRecyclerView(satList);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(SATActivity.this);
        requestQueue.add(request);
    }

    private void setupRecyclerView(List<SATscore> satList) {

        myadapter = new RecyclerViewAdapterSAT(this, satList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(myadapter);

    }
}
