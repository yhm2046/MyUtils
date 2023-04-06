package com.aidl.myutils.json;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonTest {
    public static void main(String[] args) throws JSONException {
        // 构造一个JSONObject对象
        JSONObject jsonObject = new JSONObject();

        RS485DeviceFunction rs = new RS485DeviceFunction();
        rs.rs485port = 22;
        ArrayList<String> addList = new ArrayList<String>(Arrays.asList("add1", "add2", "add3"));
        rs.knxAddress =  addList;
        rs.rs485DeviceIndex = 1;
        rs.value = "value";
        rs.exp = "exp";
        rs.operation = 0;

        // 添加一些键值对
        jsonObject.put("port", rs.rs485port);

        // 将另一个JSONObject对象作为值添加进去
        JSONObject addressObject = new JSONObject();
        addressObject.put("add1", addList.get(0));
        addressObject.put("add2", addList.get(1));
        addressObject.put("add3", addList.get(2));
        jsonObject.put("add", addressObject);



        // 将JSONObject对象转换成字符串
        String jsonString = jsonObject.toString();
//        System.out.println(jsonString);
        Log.i("xwg","jsonStr:" + jsonString);

        // 解析字符串
        JSONObject newJsonObject = new JSONObject(jsonString);
        int port = newJsonObject.getInt("port");
        JSONObject jo = newJsonObject.getJSONObject("add");
        String s1 = jo.getString("add1");
        String s2 = jo.getString("add2");

        Log.i("xwg","port:" + port);
        Log.i("xwg","s1:" + s1);
        Log.i("xwg","s2:" + s2);
    }
}
