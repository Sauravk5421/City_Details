package com.t.citydetails;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.t.citydetails.databinding.ActivityDataInsertBinding;
import com.t.citydetails.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity {

ActivityMainBinding binding;
private RepoViewModel repoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.SOFT_INPUT_MASK_ADJUST);
        getSupportActionBar().hide();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        repoViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.
                getInstance(this.getApplication())).get(RepoViewModel.class);

        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,DataInsertActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        binding.Rv.setLayoutManager(new LinearLayoutManager(this));
        binding.Rv.setHasFixedSize(true);
        RepoAdapter adapter =new RepoAdapter();
        binding.Rv.setAdapter(adapter);

        repoViewModel.getAllGit().observe(this, new Observer<List<RepoActivity>>() {
            @Override
            public void onChanged(List<RepoActivity> repoActivities) {
                adapter.submitList(repoActivities);
            }
        });

        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                "https://gist.githubusercontent.com/dastagirkhan/00a6f6e32425e0944241/raw/",
                null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                Log.d("response", "hello");
                for (int i = 0; i < response.length(); i++) {
                    try {
                        Log.d("app", "response" + response);
                        JSONObject responseObj = response.getJSONObject(i);
                        String name1 = responseObj.getString("name");
                        String state = responseObj.getString("state");
                        String lat = responseObj.getString("lat");
                        String lon = responseObj.getString("lon");
                        RepoActivity name = new RepoActivity(name1, state, lat, lon);
                        repoViewModel.insert(name);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Myapp","Something Wrong");

            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1)
        {
            String city = data.getStringExtra("city");
            String state = data.getStringExtra("state");
            String lat = data.getStringExtra("lat");
            String lon = data.getStringExtra("lon");
            RepoActivity name = new RepoActivity(city,state,lat,lon);
            repoViewModel.insert(name);
            Toast.makeText(this,"City Added",Toast.LENGTH_SHORT).show();

        }
    }

}