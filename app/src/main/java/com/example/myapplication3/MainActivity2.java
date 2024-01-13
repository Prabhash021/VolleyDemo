package com.example.myapplication3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity {
    EditText fname, idEmp;
    Button send;
    TextView result;
    ProgressBar loadingPB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        fname = findViewById(R.id.fName);
        idEmp = findViewById(R.id.sName);
        send = findViewById(R.id.btn);
        result = findViewById(R.id.result);
        loadingPB = findViewById(R.id.loadingPB);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fname.getText().toString().isEmpty() && idEmp.getText().toString().isEmpty()){
                    toast("Please prove the details");
                }
                postDat(fname.getText().toString(), idEmp.getText().toString());
            }
        });

    }

    private void postDat(String fName, String IdEmp) {
        String url = "https://reqres.in/api/users";
        loadingPB.setVisibility(View.VISIBLE);
        RequestQueue queue = Volley.newRequestQueue(MainActivity2.this);

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loadingPB.setVisibility(View.GONE);
                        toast("Data added to the API");
                        try {
                            JSONObject jsonObjectRes = new JSONObject(response);
                            String name = jsonObjectRes.getString("name");
                            String id = jsonObjectRes.getString("id");
                            result.setText("Name: "+ name+ "\nID: "+id);
                            toast("inside try");
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                toast("Failed to get response.");
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", fName);
                params.put("id", IdEmp);
                return params;
            }
        };
        queue.add(request);
    }

    private void toast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}