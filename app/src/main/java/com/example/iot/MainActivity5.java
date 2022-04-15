package com.example.iot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity5 extends AppCompatActivity {
    private ImageView iv_mic;
    private TextView tv_Speech_to_text;
    private static final int REQUEST_CODE_SPEECH_INPUT = 1;
    OkHttpClient okHttpClient = new OkHttpClient();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        iv_mic = findViewById(R.id.iv_mic);
        tv_Speech_to_text = findViewById(R.id.tv_speech_to_text);

        iv_mic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {


                try {
                    Intent intent
                            = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                            Locale.getDefault());
                    intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak to text");
                    startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT);
                }
                catch (Exception e) {
                    Toast
                            .makeText(MainActivity5.this, " " + e.getMessage(),
                                    Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SPEECH_INPUT) {
            if (resultCode == RESULT_OK && data != null) {
                ArrayList<String> result = data.getStringArrayListExtra(
                        RecognizerIntent.EXTRA_RESULTS);
                tv_Speech_to_text.setText(
                        Objects.requireNonNull(result).get(0));
                String voice[] = result.toArray(new String[result.size()]);
                System.out.println(voice);
                boolean device = voice[0].contains("light");
                boolean status1 = voice[0].contains("on");
                boolean status2 = voice[0].contains("off");
                //System.out.println(device + " " + status);
                if(device && status1)
                {
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
                if(device && status2)
                {
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
            }
        }
    }
}

