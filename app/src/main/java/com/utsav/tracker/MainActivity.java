package com.utsav.tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
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

public class MainActivity extends AppCompatActivity {
   final private String URL = "https://api.covid19india.org/data.json";
   ListView listView;
   ArrayAdapter arrayAdapter;
   ArrayList<Data> arrayList;
   ProductAdapter productAdapter;

    TextView lastUpdatedTv;
    TextView confirmedTv;
    TextView activeTv;
    TextView recoveredTv;
    TextView deceasedTv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list);

        lastUpdatedTv = findViewById(R.id.lastUpdatedTv);
        confirmedTv = findViewById(R.id.confirmedTv);
        activeTv = findViewById(R.id.activeTv);
        recoveredTv  = findViewById(R.id.recoveredTv);
        deceasedTv = findViewById(R.id.deceasedTv);

        arrayList = new ArrayList<>();
        loadlistdata();

        listView.addHeaderView(LayoutInflater.from(this).inflate(R.layout.list_header,listView,false));

    }

    private void loadlistdata() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Busy Loading Data....");
        progressDialog.show();

        StringRequest stringRequest  = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("statewise");

                    JSONObject object = jsonArray.getJSONObject(0);
                    String time = object.getString("lastupdatedtime");


                    lastUpdatedTv.setText("Last Updated\n " + time);
                    confirmedTv.setText(object.getString("confirmed"));
                    activeTv.setText(object.getString("active"));
                    recoveredTv.setText(object.getString("recovered"));
                    deceasedTv.setText(object.getString("deaths"));


                    for(int i = 0; i < jsonArray.length(); i++)
                    {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        Data data = new Data(jsonObject1.getString("active"),jsonObject1.getString("confirmed"),
                                jsonObject1.getString("state"),jsonObject1.getString("deaths"),jsonObject1.getString("recovered"),
                                jsonObject1.getString("deltaconfirmed"),jsonObject1.getString("deltadeaths"),jsonObject1.getString("deltarecovered"));
                        arrayList.add(data);
                    }
                    productAdapter = new ProductAdapter(getApplicationContext(),arrayList);
                    listView.setAdapter(productAdapter);
                    progressDialog.dismiss();


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
