package com.example.reqresapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    ImageView img;
    TextView quotes, char_name;
    Button generate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quotes = findViewById(R.id.quote);
        char_name = findViewById(R.id.name);
        generate = findViewById(R.id.btn);


        img = findViewById(R.id.imageView);
        Glide.with(this).load("https://cutewallpaper.org/cdn-cgi/mirage/dd19f2d06ebc24f541f142b37b4289ffa7de722a7607e39984c5c6dd4ce8defd/1280/34x/w7zy10f06/984121744.jpg").into(img);

        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                String url = "https://api.gameofthronesquotes.xyz/v1/random";

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONObject jsonObject = response.getJSONObject("character");
                            String quote = response.getString("sentence");
                            String names = jsonObject.getString("name");

                            quotes.setText(quote);
                            char_name.setText(names);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                queue.add(jsonObjectRequest);
            }
        });
        FirebaseMessaging.getInstance().subscribeToTopic("Joffrey")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Done";
                        if (!task.isSuccessful()) {
                            msg = "Failed";
                        }
                    }
                });
    }
}