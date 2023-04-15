package com.aidl.myutils;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.aidl.myutils.base.BaseUtils;
import com.aidl.myutils.designpatterns.ProducerConsumerExample;
import com.aidl.myutils.encryption.EncryptionAndDecryption;
import com.aidl.myutils.files.FileUtils;
import com.aidl.myutils.json.JsonTest;
import com.aidl.myutils.list.ListUtils;
import com.aidl.myutils.thread.MyThread;
import com.aidl.myutils.thread.SemaphoreDemo;
import com.aidl.myutils.thread.SynchronizedTest;
import com.aidl.myutils.thread.UnsynchBankTest;

import org.json.JSONException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        testJson();
//        ListUtils.main1(null);
        try {
//            JsonTest.main(null);
//            JsonTest.copyTest();
//            BaseUtils.switchTest(99);
//            BaseUtils.switchTest(59);
//            BaseUtils.switchTest(9);
//            BaseUtils.switchTest(61);
//            BaseUtils.switchTest2(78);
//            SemaphoreDemo.main(null);
//            SynchronizedTest.main(null);
//            MyThread.main(null);
            UnsynchBankTest.main(null); //竞态的同步
//            ProducerConsumerExample.main(null);
//            EncryptionAndDecryption.main(null);
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