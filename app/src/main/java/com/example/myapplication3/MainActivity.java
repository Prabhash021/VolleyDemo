package com.example.myapplication3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<dataModel> arrData = new ArrayList<>();
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getData();
        recyclerView = findViewById(R.id.recyclerContent);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getData() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String api = "https://jsonplaceholder.typicode.com/albums/1/photos";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, api,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("url", response);
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            Gson gson = new GsonBuilder().create();
                            for(int i=0; i<jsonArray.length(); i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                /*dataModel singleData = new dataModel(
                                        jsonObject.getString("albumId"),
                                        jsonObject.getString("id"),
                                        jsonObject.getString("url"),
                                        jsonObject.getString("title"),
                                        jsonObject.getString("thumbnailUrl")
                                );*/
                                dataModel singleData = gson.fromJson(String.valueOf(jsonObject), dataModel.class);
                                arrData.add(singleData);
                            }
                            recyclerView.setAdapter(new RecyclerDataAdapter(MainActivity.this, arrData));
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("api","notCall"+error.toString());
            }
        });
        queue.add(stringRequest);
    }
}