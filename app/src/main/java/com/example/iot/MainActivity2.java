package com.example.iot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button on = findViewById(R.id.button4);
        OkHttpClient okHttpClient = new OkHttpClient();
        on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String status = "ON";
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("light", status);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                // put your json here
                RequestBody body = RequestBody.create(JSON, jsonObject.toString());
//                RequestBody formbody = new jsonBody.Builder().add("sample", usr_name).build();
                Request request = new Request.Builder().url("http://iot-home-safety-automation.herokuapp.com/light").post(body).build();
                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), "server down", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }
                        });
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        if (response.body().string().equals("success")) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                }
                            });
                        }
                        else
                        {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Light turned ON successfully", Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    }
                });



                }
            });

        Button off = findViewById(R.id.button5);
        off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String status = "OFF";
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("light", status);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                // put your json here
                RequestBody body = RequestBody.create(JSON, jsonObject.toString());
//                RequestBody formbody = new jsonBody.Builder().add("sample", usr_name).build();
                Request request = new Request.Builder().url("http://iot-home-safety-automation.herokuapp.com/light").post(body).build();
                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), "server down", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }
                        });
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        if (response.body().string().equals("success")) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                }
                            });
                        }
                        else
                        {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Light turned OFF successfully", Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    }
                });
            }
        });
        Button auto = findViewById(R.id.button6);
        auto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String status = "AUTO";
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("light", status);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                // put your json here
                RequestBody body = RequestBody.create(JSON, jsonObject.toString());
//                RequestBody formbody = new jsonBody.Builder().add("sample", usr_name).build();
                Request request = new Request.Builder().url("http://iot-home-safety-automation.herokuapp.com/light").post(body).build();
                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), "server down", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }
                        });
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        if (response.body().string().equals("success")) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                }
                            });
                        }
                        else
                        {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Light set to AUTO successfully", Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    }
                });
            }
        });

    }
}