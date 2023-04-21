package com.aidl.myutils.network;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.aidl.myutils.R;
import com.aidl.myutils.databinding.ActivityHttpBinding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityHttpBinding activity_net;
    private static final String YOUTUBE_WEB = "http://www.youtube.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity_net  = ActivityHttpBinding.inflate(LayoutInflater.from(this));
        setContentView(activity_net.getRoot());
        activity_net.button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
       if (view.getId() == R.id.button){
           sendRequestWithHttpURLConnection();
       }
    }

    private void sendRequestWithHttpURLConnection(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL(YOUTUBE_WEB);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8 * 1000);
                    connection.setReadTimeout(8 * 1000);
                    InputStream in = connection.getErrorStream();
//                    读取输入流
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuffer response = new StringBuffer();
                    String line;
                    while ((line = reader.readLine()) != null){
                        response.append(line);
                    }
                    assert line != null;
                    showResponse(line.toString());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }finally {
                    if (reader != null){
                        try {
                            reader.close();
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }if (connection != null){
                        connection.disconnect();
                    }
                }
            }
        });
    }   //sendRequestWithHttpURLConnection

    private void showResponse(final String response){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                activity_net.textView.setText(response);
            }
        });
    }
}