package com.example.shenjack.shitangcustomer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpTest extends AppCompatActivity {

    EditText et_get;
    EditText et_post;
    TextView tv_response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_test);
        et_get = (EditText) findViewById(R.id.et_for_get);
        et_post = (EditText) findViewById(R.id.et_for_post);
        tv_response = (TextView) findViewById(R.id.tv_http_response);
    }

    public void get(View view) {
        String url = et_get.getText().toString();
        try {
            tv_response.setText(performGet(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void post(View view) {
        String url = et_post.getText().toString();
        try {
            tv_response.setText(performPost(url, url));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    OkHttpClient client = new OkHttpClient();

    String performGet(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

//        Response response = client.newCall(request).execute();
//        return response.body().string();
        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");


    String performPost(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

}
