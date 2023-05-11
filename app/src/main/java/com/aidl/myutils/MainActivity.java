package com.aidl.myutils;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.aidl.myutils.date.DateCalculator;
import com.aidl.myutils.encryption.RandomNumberAndLetter;
import com.aidl.myutils.files.FileUtils;
import com.aidl.myutils.thread.MessageQueue;
import com.aidl.myutils.thread.MessageQueueFIFO;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            DateCalculator.main(null);
        } catch (Exception e) {
            Log.i("xwg","exception:" + e);
            throw new RuntimeException(e);
        }
    }

    private void testJson() {
        try {
            System.out.println("testJson running..");
//            SearchFile.search(TtgActivity.this.getBaseContext(), "device_test.json", "deviceList"); //deviceList
            boolean rs = FileUtils.searchForStringInAssetFile(MainActivity.this.getBaseContext(),"device_test.json", "abc");
            System.out.println("rs : " + rs);
        } catch (IOException e) {
            System.out.println("testJson error:" + e);
            e.printStackTrace();
        }
    }
}